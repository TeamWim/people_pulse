package com.example.test.services;

import com.example.test.models.Product;
import com.example.test.repositories.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;


    public Product saveProduct(Product product) {
        product.setCreated(LocalDateTime.now());
        product.setUpdated(LocalDateTime.now());
        return productRepo.save(product);
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id){
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }


    @Cacheable(value = "all product")
    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product oldProduct = getProductById(id);
        oldProduct.setName(updatedProduct.getName());
        oldProduct.setDescription(updatedProduct.getDescription());
        oldProduct.setPrice(updatedProduct.getPrice());
        oldProduct.setCategory(updatedProduct.getCategory());
        oldProduct.setStock(updatedProduct.getStock());
        oldProduct.setUpdated(LocalDateTime.now());
        return productRepo.save(oldProduct);
    }

    @Cacheable(value = "productsByCategory", key = "#category")
    public List<Product> getProductsByCategory(String category) {
        return productRepo.findByCategory(category);
    }

    public boolean deleteProductById(Long id) {
        if (productRepo.existsById(id)){
            productRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
