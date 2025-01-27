
package com.crio.grocery.service.impl;

import com.crio.grocery.entity.GroceryItem;
import com.crio.grocery.repository.GroceryItemRepository;
import com.crio.grocery.service.GroceryItemService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Override
    public GroceryItem createGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    @Override
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    @Override
    public GroceryItem getGroceryItemById(Long id) {
        return groceryItemRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Grocery item not found"));
    }

    @Override
    public GroceryItem updateGroceryItem(Long id, GroceryItem groceryItemDetails) {
        GroceryItem groceryItem = getGroceryItemById(id);
        groceryItem.setName(groceryItemDetails.getName());
        groceryItem.setCategory(groceryItemDetails.getCategory());
        groceryItem.setPrice(groceryItemDetails.getPrice());
        groceryItem.setQuantity(groceryItemDetails.getQuantity());
        return groceryItemRepository.save(groceryItem);
    }

    @Override
    public void deleteGroceryItem(Long id) {
        GroceryItem groceryItem = getGroceryItemById(id);
        groceryItemRepository.delete(groceryItem);
    }
}
