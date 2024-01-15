package com.DaYaSoftware.ScrappaWebVersion.services;

import com.DaYaSoftware.ScrappaWebVersion.models.Item;
import com.DaYaSoftware.ScrappaWebVersion.models.enums.ItemCategory;
import com.DaYaSoftware.ScrappaWebVersion.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> listItems(){
        return itemRepository.findAll();

    }

    public boolean saveItem(Item item){
        try{
            System.out.println("*********** THE CODE HAS GOT INTO THE ITEM SERVICE FILE: CREATE METHOD ***********");
            item.setItemCategory_1(ItemCategory.A);
            item.setItemCategory_2(ItemCategory.B);
            item.setItemCategory_3(ItemCategory.C);
            itemRepository.save(item);
        }catch (Exception e){
            log.info("Error in the ITEM SERVICE FILE: \n" + e);
            return false;
        }
        return true;
    }

    public boolean deleteItemFromRepo(Long id){
        Item itemToDelete = itemRepository.getReferenceById(id);
        itemRepository.delete(itemToDelete);
        System.out.println("*********** THE CODE HAS GOT INTO THE ITEM SERVICE FILE: DELETE METHOD ***********");
        return true;
    }

    public List<Item> listItemsByMake(String make){
        return itemRepository.findByMake(make);
    }

    public List<Item> listItemsByModel(String model){
        return itemRepository.findByModel(model);
    }

    public List<Item> listItemsByModelMake(String make, String model){
        List<Item> filteredItems = listItemsByMake(make);
        filteredItems.removeIf(item -> !item.getModel().equals(model));
        return filteredItems;
    }

    public String[] getXforMakeOnly(List<Item> itemList){
        return itemList.stream().map(Item::getModel).distinct().toArray(String[]::new);
//        itemList.str.stream().distinct()
    }

    public int[] getYforMakeOnly(List<Item> itemList, String[] modelArr){
        int[] returningCounter = new int[modelArr.length];
        for(int i = 0; i<modelArr.length;i++){
            int finalI = i;
            returningCounter[i] = itemList.stream().filter(item -> item.getModel().equals(modelArr[finalI])).toList().size();
        }
        return returningCounter;
    }


    public int[] getYforMakeModel(List<Item> itemList, int catType){
        String[] str = {"A", "B", "C", "D", "E", "F"};
        int[] returningCounter = new int[str.length];
        switch (catType) {
            case 1 -> {
                for (int i = 0; i < str.length; i++) {
                    int finalI = i;
                    returningCounter[i] = itemList.stream().filter(item -> item.getItemCategory_1().toString().equals(str[finalI])).toList().size();
                }
                return returningCounter;
            }
            case 2 -> {
                for (int i = 0; i < str.length; i++) {
                    int finalI = i;
                    returningCounter[i] = itemList.stream().filter(item -> item.getItemCategory_2().toString().equals(str[finalI])).toList().size();
                }
                return returningCounter;
            }
            case 3 -> {
                for (int i = 0; i < str.length; i++) {
                    int finalI = i;
                    returningCounter[i] = itemList.stream().filter(item -> item.getItemCategory_3().toString().equals(str[finalI])).toList().size();
                }
                return returningCounter;
            }
            default -> {
                return null;
            }
        }
    }



//    public ItemCategory getItemCategory(Item itemToFind, int catType){
//        switch (catType) {
//            case 1 -> {
//                return itemToFind.getItemCategory_1();
//            }
//            case 2 -> {
//                return itemToFind.getItemCategory_2();
//            }
//            case 3 -> {
//                return itemToFind.getItemCategory_3();
//            }
//            default -> System.out.println("********** THE CATEGORY TYPE PROVIDED IS INCORRECT **********");
//        }
//        return null;
//    }

}
