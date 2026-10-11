package com.nestandnote.backend.repository;

import com.nestandnote.backend.entity.EverydayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EverydayListRepository extends JpaRepository<EverydayList, Long> {

    List<EverydayList> findByUserId(Long userId);
}