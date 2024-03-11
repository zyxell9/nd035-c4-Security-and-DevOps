package com.example.demo.controllers;

import com.example.demo.Utils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.controllers.CartControllerTest.USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    private OrderController orderController;
    private final UserRepository userRepo = mock(UserRepository.class);
    private final OrderRepository orderRepo = mock(OrderRepository.class);

    @Before
    public void setUp() {
        orderController = new OrderController();
        Utils.injectObjects(orderController, "userRepository", userRepo);
        Utils.injectObjects(orderController, "orderRepository", orderRepo);
    }
    @Test
    public void getOrdersByUser() {
        String username = USERNAME;
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        user.setId(0L);
        Item item = TestUtils.getItem0();
        Cart cart = new Cart();
        cart.setId(0L);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setUser(user);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("2.00"));
        user.setCart(cart);
        when(userRepo.findByUsername(username)).thenReturn(user);
        orderController.submit(username);
        ResponseEntity<List<UserOrder>> responseEntity = orderController.getOrdersForUser(username);
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<UserOrder> userOrders = responseEntity.getBody();
        assertNotNull(userOrders);
        assertEquals(0, userOrders.size());
    }
    @Test
    public void getOrdersByNullUser() {
        String username = USERNAME;
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        user.setId(0L);
        Item item = TestUtils.getItem0();
        Cart cart = new Cart();
        cart.setId(0L);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setUser(user);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("2.00"));
        user.setCart(cart);
        when(userRepo.findByUsername(username)).thenReturn(null);
        orderController.submit(username);
        ResponseEntity<List<UserOrder>> responseEntity = orderController.getOrdersForUser(username);
        assertNotNull(responseEntity);
        assertEquals(404, responseEntity.getStatusCodeValue());
    }
    @Test
    public void submit() {
        String username = USERNAME;
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        user.setId(0L);
        Item item = TestUtils.getItem0();
        Cart cart = new Cart();
        cart.setId(0L);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setUser(user);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("2.00"));
        user.setCart(cart);
        when(userRepo.findByUsername(username)).thenReturn(user);
        ResponseEntity<UserOrder> response =  orderController.submit(username);
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        UserOrder retrievedUserOrder = response.getBody();
        assertNotNull(retrievedUserOrder);
        assertNotNull(retrievedUserOrder.getUser());
        assertNotNull(retrievedUserOrder.getItems());
        assertNotNull(retrievedUserOrder.getTotal());
    }
    @Test
    public void submitNullUser() {
        String username = USERNAME;
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");
        user.setId(0L);
        Item item = TestUtils.getItem0();
        Cart cart = new Cart();
        cart.setId(0L);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item);
        cart.setUser(user);
        cart.setItems(itemList);
        cart.setTotal(new BigDecimal("2.99"));
        user.setCart(cart);
        when(userRepo.findByUsername(username)).thenReturn(null);
        ResponseEntity<UserOrder> response =  orderController.submit(username);
        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
    }

}
