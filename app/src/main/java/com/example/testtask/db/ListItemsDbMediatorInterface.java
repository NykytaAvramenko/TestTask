package com.example.testtask.db;



import com.example.testtask.model.ListItemReadableInterface;

import java.util.List;


public interface ListItemsDbMediatorInterface {

    List<ListItemReadableInterface> getAllListItems();

    void addToDB(ListItemReadableInterface listItem);
}
