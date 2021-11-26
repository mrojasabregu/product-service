package com.marketplace.product.service.impl;

import com.marketplace.product.controller.request.PostProductRequest;
import com.marketplace.product.controller.request.PutProductSkuRequest;
import com.marketplace.product.domain.mapper.PostProductMapper;
import com.marketplace.product.domain.model.Product;
import com.marketplace.product.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
public class ProductServiceImplTest {

    @MockBean
    private ProductRepository productRepository;

    @Mock
    private ProductServiceImpl productService;

    @Mock
    private PutProductSkuRequest putProductSkuRequest;

    @Mock
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

    //@Test
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

    //@Test
    public  void createProductTest(){//CONSULTAR
        //Given

        Product mockProduct = postProductMapper.apply(any(PostProductRequest.class));
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
    public void deleteProductTest(){
        //Give
        String sku=productTest1.getSku();
        Product mockProduct= productRepository.findBySku(sku);
        //When
        when(productService.deleteProduct(sku)).thenReturn(new ResponseEntity(HttpStatus.ACCEPTED));
        //Then
        assertNull(productRepository.findBySku(sku));
    }

  //  @Test
    public void putProductSkuTest(){//CONSULTAR
        //Give
        String sku=productTest1.getSku();

        //When
        when(productService.putProductSku(putProductSkuRequest,sku))
                .thenReturn(ResponseEntity.ok(productRepository.save(any(Product.class))));
        //Then

    }

  //  @Test
    public void cancelReserveTest(){//CONSULTAR
        //Give

        //When
        when(productService.cancelReserve(anyList())).thenReturn(anyString());
        //Then

    }

}