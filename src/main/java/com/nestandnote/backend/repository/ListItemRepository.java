package com.nestandnote.backend.repository;

import com.nestandnote.backend.entity.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {

    List<ListItem> findByListId(Long listId);

    void deleteByListId(Long listId);
}