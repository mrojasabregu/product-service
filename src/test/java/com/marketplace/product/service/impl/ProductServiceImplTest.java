package com.marketplace.product.service.impl;

import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductServiceImplTest {


    @MockBean
    private ProductRepository productRepository;

    @Mock
    private ProductServiceImpl productService;

    private Product product;
    /*
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    */

    @Test
    void getProductSku() {
    }

    private

    @Test
    void getProducts() {

    }
}