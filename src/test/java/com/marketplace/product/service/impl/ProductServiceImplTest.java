package com.marketplace.product.service.impl;

import com.marketplace.product.controller.request.PostProductRequest;
import com.marketplace.product.domain.mapper.PostProductMapper;
import com.marketplace.product.domain.model.Keyword;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.math.BigDecimal;
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


    @Autowired
    private PostProductMapper postProductMapper;

    private Product productTest1;

    private Product productTest2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        productTest1=Product.builder()
                .productId("1")
                .sku("ASD123")
                .name("bicicleta")
                .description("descripcion")
                .price(new BigDecimal("2000.0"))
                .imgUrl("/url")
                .unitAvailable(10)
                .weight(20.0)
                .category("deporte")
                .brand("mountain bike")
                .amountToReserve(null)
                .amountToCancel(null)
                .build();

        productTest2=Product.builder()
                .productId("2")
                .sku("ASDASD")
                .name("remera")
                .description("descripcion")
                .price(new BigDecimal("1500.0"))
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

    @Test
    public  void createProductTest(){
        //Given
        PostProductRequest postProductRequest=new PostProductRequest();
        postProductRequest.setSku("skuTest");
        postProductRequest.setName("nameTest");
        postProductRequest.setDescription("descTest");
        postProductRequest.setKeywords(null);
        postProductRequest.setPrice(new BigDecimal("1000.0"));
        postProductRequest.setImgUrl("/url");
        postProductRequest.setUnitAvailable(10);
        postProductRequest.setWeight(10.0);
        postProductRequest.setCategory("catTest");
        postProductRequest.setBrand("brandTest");

        Product mockProduct = postProductMapper.apply(postProductRequest);
        //productRepository.save(mockProduct);

        //When
        when(productService.createProduct(any())).thenReturn(ResponseEntity.ok(productRepository.save(mockProduct)));
        //productRepository.save(productTest1);
        //Then
        //assertEquals(productRepository.findBySku(productTest1.getSku()),productTest1);
    }

    @Test
    public void getProductSkuTest(){
        //Give
        Product mockProduct= productRepository.findBySku(productTest1.getSku());
        //When
        when(productService.getProductSku(productTest1.getSku())).thenReturn(mockProduct);
        //Then
        assertEquals(mockProduct,productRepository.findBySku(productTest1.getSku()));
    }

    @Test
    public void  deleteProductTest(){
        //Give
        String sku=productTest1.getSku();
        Product mockProduct= productRepository.findBySku(sku);
        //When
        when(productService.deleteProduct(sku)).thenReturn(new ResponseEntity(HttpStatus.ACCEPTED));
        //Then
        assertNull(productRepository.findBySku(sku));
    }

}