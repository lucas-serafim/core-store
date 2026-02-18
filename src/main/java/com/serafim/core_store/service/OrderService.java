package com.serafim.core_store.service;

import com.serafim.core_store.dto.CreateOrderDTO;
import com.serafim.core_store.dto.OrderDTO;
import com.serafim.core_store.dto.CreateOrderItemsDTO;
import com.serafim.core_store.dto.OrderItemsDTO;
import com.serafim.core_store.exception.OrderStatusException;
import com.serafim.core_store.exception.OrderSizeLimitException;
import com.serafim.core_store.exception.OrderNotFoundException;
import com.serafim.core_store.exception.ProductNotFoundException;
import com.serafim.core_store.model.Order;
import com.serafim.core_store.model.OrderItem;
import com.serafim.core_store.model.OrderStatusEnum;
import com.serafim.core_store.model.Product;
import com.serafim.core_store.repository.OrderItemRepository;
import com.serafim.core_store.repository.OrderRepository;
import com.serafim.core_store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository itemRepository;

    @Transactional
    public OrderDTO create(CreateOrderDTO dto) {

        if (dto.items().isEmpty()) {
            throw new OrderSizeLimitException("Order must have at least 1 item.");
        }

        if (dto.items().size() > 10) {
            throw new OrderSizeLimitException("Order size limit exceeded. Max order items: 10");
        }

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

    @Transactional
    public OrderDTO removeItem(UUID orderId, UUID orderItemId) {
        Optional<Order> orderOptional = this.orderRepository.findById(orderId);

        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException();
        }

        Order order = orderOptional.get();

        if (order.getStatus() != OrderStatusEnum.PENDING) {
            throw new OrderStatusException("you cannot remove an item in current order status. status: "
                    + order.getStatus());
        }

        order.removeOrderItemById(orderItemId);
        itemRepository.deleteById(orderItemId);

        orderRepository.save(order);

        return this.mapToDTO(order);
    }

    @Transactional
    public OrderDTO cancel(UUID orderId) {
        Optional<Order> orderOptional = this.orderRepository.findById(orderId);

        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException();
        }

        Order order = orderOptional.get();

        if (order.getStatus() != OrderStatusEnum.PENDING) {
            throw new OrderStatusException("you cannot cancel this order in current status. status: "
                    + order.getStatus());
        }

        order.cancel();

        List<OrderItem> orderItems = order.getOrderItems();

        for (OrderItem item : orderItems) {
            int quantity = item.getQuantity();
            item.getProduct().increaseQuantity(quantity);
        }

        this.orderRepository.save(order);

        return this.mapToDTO(order);
    }

    public OrderDTO findById(UUID orderId) {
        Optional<Order> orderOptional = this.orderRepository.findById(orderId);

        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException();
        }

        Order order = orderOptional.get();

        return this.mapToDTO(order);
    }

    // TODO: API to filter orders by user

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
