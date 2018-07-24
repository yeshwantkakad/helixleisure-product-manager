package com.helixleisure.product.productmanager.controller;

import com.helixleisure.product.productmanager.service.ProductService;
import com.helixleisure.product.productmanager.dto.GetProductDto;
import com.helixleisure.product.productmanager.dto.ProductIdDto;
import com.helixleisure.product.productmanager.dto.ProductDto;
import com.helixleisure.product.productmanager.dto.PutProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @PutMapping(value = "/")
    public ResponseEntity createProduct(@RequestBody PutProductDto createProductEvent){
        List<ProductDto> products = createProductEvent.getProducts();
        try{
            List<ProductDto> dtos = productService.createProducts(products);
            return ResponseEntity.ok(dtos);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity getProducts(@RequestBody GetProductDto getProductEvent){
        List<ProductIdDto> voIds = getProductEvent.getProducts();

        try{
            List<ProductDto> dtos = productService.getProducts(voIds);
            return ResponseEntity.ok(dtos);

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }

}
