package com.DaYaSoftware.ScrappaWebVersion.controllers;

import com.DaYaSoftware.ScrappaWebVersion.models.Item;
import com.DaYaSoftware.ScrappaWebVersion.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    List<Item> currentQueryItems;

    @PostMapping("/dashboard/create")
    public String createItem(Item item){
        itemService.saveItem(item);
        System.out.println(itemService.listItems());
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/delete/{id}")
    public String deleteItem(@PathVariable Long id){
        itemService.deleteItemFromRepo(id);
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/find")
    public String findItem(String make, String itemModel, String category, Model model){
        consoleOutForQuery(category);
        if(itemModel == null ||itemModel.equals("")){
            currentQueryItems = itemService.listItemsByMake(make);
            String[] getXforMakeOnly = itemService.getXforMakeOnly(currentQueryItems);
            int[] getYforMakeOnly = itemService.getYforMakeOnly(currentQueryItems, getXforMakeOnly);
            consoleOutForQuery(getXforMakeOnly);
            System.out.println(Arrays.toString(getXforMakeOnly));
            consoleOutForQuery(getYforMakeOnly);
            System.out.println(Arrays.toString(getYforMakeOnly));
            model.addAttribute("makeOnlyX", getXforMakeOnly);
            model.addAttribute("makeOnlyY", getYforMakeOnly);
        }else{
            currentQueryItems = itemService.listItemsByModelMake(make, itemModel);
            String[] makeModelX = {"A", "B", "C", "D", "E", "F"};
            int[] makeModelY = itemService.getYforMakeModel(currentQueryItems, evalCategory(category));

            model.addAttribute("makeModelX", makeModelX);
            model.addAttribute("makeModelY", makeModelY);
        }

//        model.addAttribute("");
//        consoleOutForQuery(currentQueryItems);
        return "redirect:/dashboard";
    }


    public void consoleOutForQuery(Object obj){
        System.out.println("\n***********");
        System.out.println("\n"+ obj);
        System.out.println("\n***********");
    }

    public void consoleOutError(Object obj){
        System.out.println("\n*********** E R R O R ***********");
        System.out.println("\n" + obj);
        System.out.println("\n*********** E R R O R ***********");
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
    private int evalCategory(String category){
        switch (category) {
            case "Price" -> {
                return 1;
            }
            case "Year" -> {
                return 2;
            }
            case "Mileage" -> {
                return 3;
            }
            default -> {
                consoleOutError("Error in the evalCategory method");
                return 0;
            }
        }
    }
    //


}
