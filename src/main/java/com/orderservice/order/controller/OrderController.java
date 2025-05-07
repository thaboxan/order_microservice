package com.orderservice.order.controller;

import com.orderservice.order.model.Order;
import com.orderservice.order.model.Order.OrderStatus;
import com.orderservice.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Management", description = "APIs for managing orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
        summary = "Create a new order",
        description = "Creates a new order with the provided details"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Order created successfully",
        content = @Content(schema = @Schema(implementation = Order.class))
    )
    @PostMapping
    public ResponseEntity<Order> createOrder(
        @Parameter(description = "Order details", required = true) 
        @RequestBody Order order
    ) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @Operation(
        summary = "Get an order by ID",
        description = "Retrieves an order by its ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Order found",
        content = @Content(schema = @Schema(implementation = Order.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Order not found"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(
        @Parameter(description = "Order ID", required = true)
        @PathVariable Long id
    ) {
        return orderService.getOrder(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
        summary = "Get all orders",
        description = "Retrieves a list of all orders"
    )
    @ApiResponse(
        responseCode = "200",
        description = "List of orders retrieved successfully",
        content = @Content(schema = @Schema(implementation = Order.class))
    )
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @Operation(
        summary = "Update order status",
        description = "Updates the status of an existing order"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Order status updated successfully",
        content = @Content(schema = @Schema(implementation = Order.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Order not found"
    )
    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
        @Parameter(description = "Order ID", required = true)
        @PathVariable Long id,
        @Parameter(description = "New order status", required = true)
        @RequestParam OrderStatus status
    ) {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    @Operation(
        summary = "Delete an order",
        description = "Deletes an order by its ID"
    )
    @ApiResponse(
        responseCode = "204",
        description = "Order deleted successfully"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Order not found"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
        @Parameter(description = "Order ID", required = true)
        @PathVariable Long id
    ) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}