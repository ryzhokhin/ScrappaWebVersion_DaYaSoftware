package com.DaYaSoftware.ScrappaWebVersion.repositories;

import com.DaYaSoftware.ScrappaWebVersion.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByMake(String make);

    List<Item> findByModel(String model);



//    Item findById(Long id);
}
