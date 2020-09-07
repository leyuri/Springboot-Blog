package com.yuri.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuri.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
