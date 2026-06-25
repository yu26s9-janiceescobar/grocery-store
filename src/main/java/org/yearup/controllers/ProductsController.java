package org.yearup.controllers;

import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Product;
import org.yearup.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductsController
{
    private final ProductService productService;

    public ProductsController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> search(@RequestParam(name="cat", required = false) Long categoryId,
                                @RequestParam(name="minPrice", required = false) BigDecimal minPrice,
                                @RequestParam(name="maxPrice", required = false) BigDecimal maxPrice,
                                @RequestParam(name="subCategory", required = false) String subCategory)
    {
        return productService.search(categoryId, minPrice, maxPrice, subCategory);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable Long id)
    {
        return productService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product)
    {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product)
    {
        return productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id)
    {
        productService.delete(id);
    }
}
