package com.example.orders.controller;

import com.example.orders.data.CreateOrderInputDTO;
import com.example.orders.models.Order;
import com.example.orders.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderController orderController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUP(){
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateOrder() throws Exception {

        CreateOrderInputDTO createOrderInputDTO = new CreateOrderInputDTO("Вода дистилированная","Закончился на складе товар",
                "Минск", "100");

    String orderJson = objectMapper.writeValueAsString(createOrderInputDTO);

        mockMvc.perform(post("/api/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson))
            .andExpect(status().isCreated()).andReturn();
}


    @Test
    public void testGetAll() throws Exception {
        Order order = new Order(1,"Вода", "Нет на складе", "Беларусь", "Завод", "NEW");
        Order order2 = new Order(2,"Машина", "Нет на складе", "Беларусь", "Завод", "NEW");

        Mockito.when(orderRepository.getAll()).thenReturn(List.of(order,order2));
        mockMvc.perform(get("/api/order/all")).andExpect(status().isOk());

    }
}
