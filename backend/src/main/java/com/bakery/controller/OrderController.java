package com.bakery.controller;

import com.bakery.model.Order;
import com.bakery.model.OrderItem;
import com.bakery.model.Product;
import com.bakery.repository.OrderRepository;
import com.bakery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{email}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable String email) {
        List<Order> orders = orderRepository.findByCustomerEmail(email);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> orderRequest) {
        try {
            Order order = new Order();
            order.setCustomerName((String) orderRequest.get("customerName"));
            order.setCustomerEmail((String) orderRequest.get("customerEmail"));
            order.setCustomerAddress((String) orderRequest.get("customerAddress"));
            order.setCustomerPhone((String) orderRequest.get("customerPhone"));

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> items = (List<Map<String, Object>>) orderRequest.get("items");
            
            double totalAmount = 0.0;

            for (Map<String, Object> itemData : items) {
                Long productId = Long.valueOf(itemData.get("productId").toString());
                Integer quantity = Integer.valueOf(itemData.get("quantity").toString());

                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

                if (product.getStock() < quantity) {
                    return ResponseEntity.badRequest()
                            .body("Insufficient stock for product: " + product.getName());
                }

                OrderItem orderItem = new OrderItem(product, quantity, product.getPrice());
                order.addItem(orderItem);
                totalAmount += product.getPrice() * quantity;

                product.setStock(product.getStock() - quantity);
                productRepository.save(product);
            }

            order.setTotalAmount(totalAmount);
            Order savedOrder = orderRepository.save(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating order: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setStatus(statusUpdate.get("status"));
                    Order updated = orderRepository.save(order);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
