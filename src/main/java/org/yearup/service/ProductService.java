package org.yearup.service;


import org.springframework.stereotype.Service;
import org.yearup.exception.ResourceNotFoundException;
import org.yearup.models.Product;
import org.yearup.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService
{
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> search(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, String subCategory)
    {
        List<Product> products = categoryId != null
                ? productRepository.findByCategoryId(categoryId)
                : productRepository.findAll();

        return products.stream()
                       .filter(p -> minPrice == null || p.getPrice().compareTo(minPrice) >= 0)
                       .filter(p -> maxPrice == null || p.getPrice().compareTo(maxPrice) <= 0)
                       .filter(p -> subCategory == null || subCategory.equalsIgnoreCase(p.getSubCategory()))
                       .toList();
    }

    public List<Product> listProductsByCategoryId(Long categoryId)
    {
        return productRepository.findByCategoryId(categoryId);
    }

    public Product getById(Long productId)
    {
        return productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product Not Found: " + productId));
    }

    public Product create(Product product)
    {
        return productRepository.save(product);
    }

    public Product update(Long productId, Product product)
    {
        return productRepository.findById(productId).map(existing -> {
            existing.setName(product.getName());
            existing.setPrice(product.getPrice());
            existing.setCategoryId(product.getCategoryId());
            existing.setDescription(product.getDescription());
            existing.setSubCategory(product.getSubCategory());
            existing.setFeatured(product.isFeatured());
            existing.setImageUrl(product.getImageUrl());
            return productRepository.save(existing);
        }).orElseThrow(()-> new ResourceNotFoundException("Product Not Found: " + productId));
    }

    public void delete(Long productId)
    {
        if (!productRepository.existsById(productId)){
            throw new ResourceNotFoundException("Product Not Found: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
