package com.maplr.test.sugarshack.mapleordersapi.cart;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemDto;
import com.maplr.test.sugarshack.mapleordersapi.testconfig.IntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CartControllerIT extends IntegrationTest {

    private static final String BASE_PATH = "/maple-orders/public/api/cart";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_getCartItemsByCartId() throws Exception {
        MvcResult res = mockMvc.perform(get(BASE_PATH + "/1"))
                               .andExpect(status().isOk())
                               .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = res.getResponse().getContentAsString();
        List<CartItemDto> cartItems = objectMapper.readValue(json, new TypeReference<List<CartItemDto>>() {});

        assertEquals(1, cartItems.size());
    }

    @Disabled("temp disable")
    @Test
    @Sql("classpath:sql/clean_tables.sql")
    void should_createCart() throws Exception {

        CreateCartRequest request = CreateCartRequest.builder()
                                                     .customerId(1L)
                                                     .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post(BASE_PATH + "/create")
                       .content(jsonRequest)
                       .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(status().isOk());
    }

    @Disabled("temp disable")
    @Test
    @Sql("classpath:sql/clean_tables.sql")
    void should_not_createCart_whenUserIsNotFound() throws Exception {
        Long id = 99L; // Customer id not present in flyway scripts init
        CreateCartRequest request = CreateCartRequest.builder()
                                                     .customerId(id)
                                                     .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post(BASE_PATH + "/create")
                       .content(jsonRequest)
                       .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(status().is4xxClientError());
    }

    @Test
    void should_addToCart() throws Exception {
        mockMvc.perform(put(BASE_PATH + "/1")
                .param("productId", "1")
                .param("userId", "1")
                .param("qty", "1")
        ).andExpect(status().isAccepted());
    }

    @Test
    void should_removeFromCart() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/1")
                       .param("productId", "1"))
               .andExpect(status().isAccepted());
    }

    // TODO : fix the test when ran with other
    @Test
    @Disabled("The check does not run when test class")
    void should_changeQty_whenQuantityUpperThanZero() throws Exception {
        mockMvc.perform(patch(BASE_PATH + "/2")
                .param("productId", "2")
                .param("newQty", "2")
        ).andExpect(status().isAccepted());
    }

    @Test
    void should_changeQty_whenQuantityLowerThanOrEqualZero() throws Exception {
        mockMvc.perform(patch(BASE_PATH + "/1")
                .param("productId", "1")
                .param("newQty", "0")
        ).andExpect(status().isAccepted());
    }
}