package com.example.DnDProject.Controllers;

import com.example.DnDProject.DTOs.ItemDTO;
import com.example.DnDProject.Services.DataService;
import com.example.DnDProject.Services.DatafillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class ItemController {

    @Autowired
    private DataService dataService;

    @Autowired
    private DatafillService datafillService;

    @GetMapping("/fillDBItem")
    public String createItemForm() {
        return "fillDBItem";
    }

    @GetMapping("/getItem")
    @ResponseBody
    public Map<String, Object> getItem(@RequestParam("id") String id) {
        return dataService.itemInfo(id);
    }

    @GetMapping("/getItems")
    @ResponseBody
    public Map<String, List<Map<String, Object>>> getItems(@RequestParam  Map<String, String> query){
        return dataService.getFilteredSortedItems(query);
    }

    @PostMapping("/itemPush")
    @ResponseBody
    public ResponseEntity<String> itemPush(@RequestBody ItemDTO itemDTO){
        datafillService.saveItem(itemDTO);
        return ResponseEntity.ok("Item received successfully!");
    }


}

