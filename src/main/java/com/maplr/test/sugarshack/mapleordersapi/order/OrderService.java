package com.maplr.test.sugarshack.mapleordersapi.order;

import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.cart.item.CartItemRepository;
import com.maplr.test.sugarshack.mapleordersapi.common.CrudService;
import com.maplr.test.sugarshack.mapleordersapi.customer.CustomerEntity;
import com.maplr.test.sugarshack.mapleordersapi.customer.CustomerService;
import com.maplr.test.sugarshack.mapleordersapi.order.item.OrderItemEntity;
import com.maplr.test.sugarshack.mapleordersapi.order.item.OrderItemMapper;
import com.maplr.test.sugarshack.mapleordersapi.pricing.PriceCalculatorService;
import com.maplr.test.sugarshack.mapleordersapi.product.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderService extends CrudService<OrderEntity, Long> {

    private final OrderMapper mapper;
    private final OrderItemMapper itemMapper;
    private final OrderRepository repository;
    private final CartItemRepository cartItemRepository;
    private final CustomerService customerService;
    private final PriceCalculatorService priceCalculator;
    private final ProductService productService;

    @Autowired
    protected OrderService(
            OrderRepository repository,
            OrderMapper mapper,
            CartItemRepository cartItemRepository,
            CustomerService customerService,
            OrderItemMapper itemMapper,
            PriceCalculatorService priceCalculator,
            ProductService productService
    ) {
        super(repository);
        this.repository = repository;
        this.mapper = mapper;
        this.customerService = customerService;
        this.cartItemRepository = cartItemRepository;
        this.itemMapper = itemMapper;
        this.priceCalculator = priceCalculator;
        this.productService = productService;
    }

    public List<OrderDto> getOrderBy(Long customerId) {
        List<OrderEntity> entities = this.repository.getOrderEntitiesByCustomerEntityId(customerId);
        return mapper.entitiesToDtos(entities);
    }

    @Transactional
    public OrderValidationResponseDto placeOrder(Long customerId, Long cartId) {
        List<CartItemEntity> cartItemEntities = this.cartItemRepository.findAllByCartEntityIdOrderByProductEntityName(cartId);

        List<String> errors = validateOrder(cartItemEntities);

        if (errors.isEmpty()) {
            createOrder(customerId, cartItemEntities, priceCalculator.getTotalPrice(cartItemEntities));
            // update products stock
            cartItemEntities.forEach(cartEntity -> productService.updateProductStock(cartEntity.getQuantity(), cartEntity.getProductEntity().getId()));
        }

        return OrderValidationResponseDto.builder()
                                         .isOrderValid(errors.isEmpty())
                                         .errors(errors)
                                         .build();
    }

    private List<String> validateOrder(@NotNull List<CartItemEntity> items) {

        List<String> errors = new ArrayList<>();

        if (priceCalculator.getTotalPrice(items) <= 100) {
            errors.add("Lâche-toi lousse!");
        }
        Integer numberOfProducts = items.stream().reduce(0, (sum, e) -> sum + e.getQuantity(), Integer::sum);

        if (numberOfProducts <= 5) {
            errors.add("Tant qu'à y être, prends-en donc une couple d'cannes!");
        }

        return errors;
    }


    protected void createOrder(Long customerId, List<CartItemEntity> cartItemEntities, Float totalPrice) {
        Set<OrderItemEntity> orderItemEntities = itemMapper.cartItemsToOderItemEntities(cartItemEntities);
        CustomerEntity customerEntity = this.customerService.findById(customerId);
        OrderEntity orderEntity = new OrderEntity(customerEntity, 1, totalPrice, orderItemEntities);
        repository.save(orderEntity);
    }


}
