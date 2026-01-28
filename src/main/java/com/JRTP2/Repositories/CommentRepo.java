package com.JRTP2.Repositories;

import com.JRTP2.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<CommentEntity,Integer> {
}
