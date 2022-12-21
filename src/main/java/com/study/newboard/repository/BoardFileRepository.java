package com.study.newboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.newboard.entity.BoardFileEntity;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}

