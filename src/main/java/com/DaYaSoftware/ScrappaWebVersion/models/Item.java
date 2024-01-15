package com.DaYaSoftware.ScrappaWebVersion.models;


import com.DaYaSoftware.ScrappaWebVersion.models.enums.Contract;
import com.DaYaSoftware.ScrappaWebVersion.models.enums.ItemCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SecondaryTable(name = "item_category", pkJoinColumns = @PrimaryKeyJoinColumn(name = "item_id"))
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "scrapVersion")
    private int scrapVersion;
    @Column(name = "price") // WAS FLOAT BEFORE
    private int price;
    @Column(name = "year")
    private int year;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "description")
    private String description;
    @Column(name = "location")
    private String location;
    @Column(name = "miles")
    private int miles;

//  SECONDARY TABLE
    @Column(name = "Category_1", table = "item_category")
    @Enumerated(EnumType.STRING)
//    Set<ItemCategory> itemCategorySet_1 = new HashSet<>();
    ItemCategory itemCategory_1;
    @Column(name = "Category_2", table = "item_category")
    @Enumerated(EnumType.STRING)
    ItemCategory itemCategory_2;
    @Column(name = "Category_3", table = "item_category")
    @Enumerated(EnumType.STRING)
    ItemCategory itemCategory_3;

//    **** TBD ****
//    private Set<FilterCategory> categories = new HashSet<>();

//    @ElementCollection(targetClass = )
//    private Set<Contract> contracts = new HashSet<>();

}
