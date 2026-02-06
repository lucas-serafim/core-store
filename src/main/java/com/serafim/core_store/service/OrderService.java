package com.serafim.core_store.service;

import com.serafim.core_store.dto.CreateOrderDTO;
import com.serafim.core_store.dto.OrderDTO;
import com.serafim.core_store.dto.CreateOrderItemsDTO;
import com.serafim.core_store.dto.OrderItemsDTO;
import com.serafim.core_store.exception.ProductNotFoundException;
import com.serafim.core_store.model.Order;
import com.serafim.core_store.model.OrderItem;
import com.serafim.core_store.model.OrderStatusEnum;
import com.serafim.core_store.model.Product;
import com.serafim.core_store.repository.OrderRepository;
import com.serafim.core_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public OrderDTO create(CreateOrderDTO dto) {
        List<UUID> uuids = dto.items().stream().map(CreateOrderItemsDTO::id).toList();

        List<Product> products = productRepository.findAllById(uuids);

        Map<UUID, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, p -> p));

        int totalOrder = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        for (CreateOrderItemsDTO item : dto.items()) {
            Product product = productMap.get(item.id());

            if (product == null) {
                throw new ProductNotFoundException("Product not found. ID: " + item.id());
            }

            int totalItem = product.getPrice() * item.quantity();

            orderItems.add(new OrderItem(
                    item.quantity(),
                    product.getPrice(),
                    product
            ));

            product.decreaseQuantity(item.quantity());
            productList.add(product);

            totalOrder += totalItem;
        }

        Order order = new Order(
                totalOrder,
                OrderStatusEnum.PENDING
        );

        order.setOrderItems(orderItems);

        orderRepository.save(order);
        productRepository.saveAll(productList);

        return mapToDTO(order);
    }

    private OrderDTO mapToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getOrderItems().stream().map(i -> new OrderItemsDTO(
                        i.getId(),
                        i.getQuantity(),
                        i.getPriceAtPurchase()
                )).toList()
        );
    }
}
