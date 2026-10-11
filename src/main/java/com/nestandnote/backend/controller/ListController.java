package com.nestandnote.backend.controller;

import com.nestandnote.backend.entity.EverydayList;
import com.nestandnote.backend.entity.ListItem;
import com.nestandnote.backend.service.ListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ListController {

    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping("/lists")
    public List<EverydayList> getLists(@RequestParam("userId") Long userId) {
        return listService.getLists(userId);
    }

    @PostMapping("/lists")
    public EverydayList createList(@RequestBody EverydayList list) {
        return listService.createList(list);
    }

    @DeleteMapping("/lists/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable("id") Long id) {
        listService.deleteList(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lists/{listId}/items")
    public List<ListItem> getItems(@PathVariable("listId") Long listId) {
        return listService.getItems(listId);
    }

    @PostMapping("/lists/{listId}/items")
    public ListItem addItem(@PathVariable("listId") Long listId, @RequestBody ListItem item) {
        return listService.addItem(listId, item);
    }

    @PutMapping("/list-items/{id}/toggle")
    public ListItem toggleItem(@PathVariable("id") Long id) {
        return listService.toggleItem(id);
    }

    @DeleteMapping("/list-items/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
        listService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}