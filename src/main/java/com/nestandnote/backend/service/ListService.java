package com.nestandnote.backend.service;

import com.nestandnote.backend.entity.EverydayList;
import com.nestandnote.backend.entity.ListItem;
import com.nestandnote.backend.repository.EverydayListRepository;
import com.nestandnote.backend.repository.ListItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListService {

    private final EverydayListRepository listRepo;
    private final ListItemRepository itemRepo;

    public ListService(EverydayListRepository listRepo, ListItemRepository itemRepo) {
        this.listRepo = listRepo;
        this.itemRepo = itemRepo;
    }

    public List<EverydayList> getLists(Long userId) {
        return listRepo.findByUserId(userId);
    }

    public EverydayList createList(EverydayList list) {
        if (list.getName() == null || list.getName().isBlank()) {
            throw new IllegalArgumentException("List name cannot be empty");
        }
        list.setId(null);
        return listRepo.save(list);
    }

    @Transactional
    public void deleteList(Long id) {
        itemRepo.deleteByListId(id);
        listRepo.deleteById(id);
    }

    public List<ListItem> getItems(Long listId) {
        return itemRepo.findByListId(listId);
    }

    public ListItem addItem(Long listId, ListItem item) {
        if (item.getText() == null || item.getText().isBlank()) {
            throw new IllegalArgumentException("Task cannot be empty");
        }
        item.setId(null);
        item.setListId(listId);
        item.setDone(false);
        return itemRepo.save(item);
    }

    public ListItem toggleItem(Long id) {
        ListItem item = itemRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        item.setDone(!item.isDone());
        return itemRepo.save(item);
    }

    public void deleteItem(Long id) {
        itemRepo.deleteById(id);
    }
}