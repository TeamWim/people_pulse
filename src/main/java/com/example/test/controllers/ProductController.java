package com.example.test.controllers;

import com.example.test.models.Product;
import com.example.test.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;


    /**
     * Get all products with pagination support.
     * @param pageable the pagination details
     * @return a paginated list of products
     */
    @Operation(summary = "Get all products", description = "Fetch a paginated list of all products")
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProduct(pageable));
    }

    /**
     * Get a specific product by its ID.
     * @param id the ID of the product
     * @return the product details
     */
    @Operation(summary = "Get product by ID", description = "Fetch a product by its unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Get products by category.
     * @param category the product category
     * @return a list of products in the given category
     */
    @Operation(summary = "Get products by category", description = "Fetch products by their category")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    /**
     * Create a new product.
     * @param product the product data to be created
     * @return the created product
     */
    @Operation(summary = "Create a new product", description = "Add a new product to the database")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product product1 = productService.saveProduct(product);
        return ResponseEntity.ok(product1);
    }

    /**
     * Update an existing product.
     * @param id the ID of the product to update
     * @param product the new product data
     * @return the updated product
     */
    @Operation(summary = "Update product", description = "Update the details of an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    /**
     * Delete a product by ID.
     * @param id the ID of the product to delete
     * @return boolean indicating success of deletion
     */
    @Operation(summary = "Delete product", description = "Delete an existing product by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long id) {
        return  ResponseEntity.ok(productService.deleteProductById(id));
    }

}
