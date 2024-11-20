package com.maplr.test.sugarshack.mapleordersapi.common;

import com.maplr.test.sugarshack.mapleordersapi.product.ProductEntity;
import com.maplr.test.sugarshack.mapleordersapi.testconfig.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.maplr.test.sugarshack.mapleordersapi.testconfig.TestConstant.getSampleProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Sql(value = {"classpath:sql/clean_tables.sql", "classpath:sql/fake_data.sql"})
class CrudServiceIT extends IntegrationTest {

    @Qualifier("productService")
    @Autowired
    CrudService<ProductEntity, Long> crudService;

    @Test
    void should_findAll() {
        List<ProductEntity> productEntities = crudService.findAll();
        assertFalse(productEntities.isEmpty());
    }

    @Test
    void should_findById() {
        ProductEntity productEntity = crudService.findById(1L);
        assertEquals(1L, productEntity.getId());
    }

    @Test
    void should_deleteById() {
        crudService.deleteById(1L);
        List<ProductEntity> productEntities = crudService.findAll();
        assertThat(productEntities).filteredOn(productEntity -> productEntity.getId() == 1L).isEmpty();
    }

    @Test
    void should_save() {
        ProductEntity p = getSampleProduct();
        assertNotNull(crudService.save(p));
    }

    @Test
    void should_update() {
        ProductEntity p = getSampleProduct();
        p.setId(1L);

        ProductEntity updatedP = crudService.update(1L, p);

        assertThat(updatedP)
                .extracting(ProductEntity::getName, ProductEntity::getDescription)
                .containsExactly("sample product", "sample description");
    }
}