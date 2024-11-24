package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entities.Category;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import com.example.demo.repositories.OrderRepository;

//import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Date;


@Controller
public class OrderController {

    @GetMapping("/bought")
    public String getBoughtList(Model model) {
        // Создаём фиктивные данные
    	
    	
    	Category electronics = new Category(1L, "Electronics");

        // Фиктивные продукты с категорией
        List<Product> products = List.of(
            new Product(1L, "Laptop", 1000.0, electronics),
            new Product(2L, "Mouse", 25.0, electronics),
            new Product(3L, "Phone", 700.0, electronics)
        );

        // Фиктивные заказы
        List<Order> orders = List.of(
            new Order(1L, "John Doe", "123 Main St", new Date(), products),
            new Order(2L, "Jane Smith", "456 Elm St", new Date(), products)
        );

        // Передаём заказы в модель
        model.addAttribute("rder", orders);
        return "bought";  // Возвращаем название Thymeleaf-шаблона
    }

    

   // @PostMapping
    //public Order createOrder(@RequestBody Order order) {
        //return orderRepository.save(order);
    }
