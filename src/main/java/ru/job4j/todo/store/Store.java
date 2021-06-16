package ru.job4j.todo.store;

import ru.job4j.todo.model.Item;

import java.util.List;

public interface Store {

    List<Item> findAll();
    List<Item> findAllCurrentTask();
    void create (Item item);
    Item findById(Integer id);
    void update (Item item);

}
