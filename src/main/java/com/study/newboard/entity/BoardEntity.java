package com.study.newboard.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.study.newboard.dto.BoardDTO;

import lombok.Getter;
import lombok.Setter;



//DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
 @Id // pk 컬럼 지정. 필수
 @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
 private Long id;

 @Column(length = 20, nullable = false) // 크기 20, not null
 private String boardWriter;

 @Column(length = 255) // 크기 255, null 가능
 private String boardPass;

 @Column(length = 500)
 private String boardTitle;

 @Column(length = 5000)
 private String boardContents;

 @Column
 private int boardHits;

 @Column
 private int fileAttached; // 1 or 0

 @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
 private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

 public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
     BoardEntity boardEntity = new BoardEntity();
     boardEntity.setBoardWriter(boardDTO.getBoardWriter());
     boardEntity.setBoardPass(boardDTO.getBoardPass());
     boardEntity.setBoardTitle(boardDTO.getBoardTitle());
     boardEntity.setBoardContents(boardDTO.getBoardContents());
     boardEntity.setBoardHits(0);
     boardEntity.setFileAttached(0); // 파일 없음.
     return boardEntity;
 }

 public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
     BoardEntity boardEntity = new BoardEntity();
     boardEntity.setId(boardDTO.getId());
     boardEntity.setBoardWriter(boardDTO.getBoardWriter());
     boardEntity.setBoardPass(boardDTO.getBoardPass());
     boardEntity.setBoardTitle(boardDTO.getBoardTitle());
     boardEntity.setBoardContents(boardDTO.getBoardContents());
     boardEntity.setBoardHits(boardDTO.getBoardHits());
     return boardEntity;
 }

 public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
     BoardEntity boardEntity = new BoardEntity();
     boardEntity.setBoardWriter(boardDTO.getBoardWriter());
     boardEntity.setBoardPass(boardDTO.getBoardPass());
     boardEntity.setBoardTitle(boardDTO.getBoardTitle());
     boardEntity.setBoardContents(boardDTO.getBoardContents());
     boardEntity.setBoardHits(0);
     boardEntity.setFileAttached(1); // 파일 있음.
     return boardEntity;
 }
}
