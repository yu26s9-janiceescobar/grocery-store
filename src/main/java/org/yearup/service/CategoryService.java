package org.yearup.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.yearup.exception.ResourceNotFoundException;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService
{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();

    }

    public Category getById(Long categoryId)
    {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category not Found: " + categoryId));
    }
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Long categoryId, Category category)
    {
      return categoryRepository.findById(categoryId).map(existing -> {
              existing.setDescription(category.getDescription());
              existing.setName(category.getName());
              return categoryRepository.save(existing);
      }).orElseThrow(()-> new ResourceNotFoundException("Category Not Found: " + categoryId));

    }

    public void delete(Long categoryId)
    {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category Not Found: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }
}
