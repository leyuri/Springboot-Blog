package com.yuri.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yuri.blog.model.Board;
import com.yuri.blog.model.User;


public interface BoardRepository extends JpaRepository<Board, Integer>{

}