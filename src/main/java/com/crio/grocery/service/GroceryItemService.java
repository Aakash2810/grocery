package com.crio.grocery.service;

import com.crio.grocery.entity.GroceryItem;
import java.util.List;

public interface GroceryItemService {

    GroceryItem createGroceryItem(GroceryItem groceryItem);

    List<GroceryItem> getAllGroceryItems();

    GroceryItem getGroceryItemById(Long id);

    GroceryItem updateGroceryItem(Long id, GroceryItem groceryItemDetails);

    void deleteGroceryItem(Long id);
}