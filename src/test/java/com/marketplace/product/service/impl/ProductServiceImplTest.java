package com.marketplace.product.service.impl;
<<<<<<< HEAD
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

=======

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
>>>>>>> 0fe5e169ac075b4763ca965f728112685c63b3e2

@SpringBootTest
class ProductServiceImplTest {

<<<<<<< HEAD
    @MockBean
    private ProductRepository productRepository;

    @Mock
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getProductSku() {
=======
    private

    @Test
    void getProducts() {
>>>>>>> 0fe5e169ac075b4763ca965f728112685c63b3e2
    }
}