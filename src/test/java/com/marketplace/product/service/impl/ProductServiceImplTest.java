package com.marketplace.product.service.impl;

import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ProductServiceImplTest {

    @MockBean
    private ProductRepository productRepository;

    @Mock
    private ProductServiceImpl productService;

    private Product productTest1;

    private Product productTest2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        productTest1=Product.builder()
                .productId(1L)
                .sku("ASD123")
                .name("bicicleta")
                .description("descripcion")
                .price(2000.00)
                .imgUrl("/url")
                .unitAvailable(10)
                .weight(20.0)
                .category("deporte")
                .brand("mountain bike")
                .amountToReserve(null)
                .amountToCancel(null)
                .build();

        productTest2=Product.builder()
                .productId(2L)
                .sku("ASDASD")
                .name("remera")
                .description("descripcion")
                .price(200.00)
                .imgUrl("/url")
                .unitAvailable(15)
                .weight(2.0)
                .category("deporte")
                .brand("adidas")
                .amountToReserve(null)
                .amountToCancel(null)
                .build();

    }

    @AfterEach
    public void tearDown(){
        productTest1 = null;
        productTest2 = null;
    }

    @Test
    public void getProductsTest() {
        //Given
        List<Product> productList= new ArrayList<>();
        productList.add(productTest1);
        productList.add(productTest2);

        //When
        when(productService.getProducts()).thenReturn(productList);

        //Then
        assertEquals(productList,productService.getProducts());
        assertNotNull(productService.getProducts());




    }
}