package com.JRTP2.Repositories;

import com.JRTP2.Entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<PostEntity,Integer> {
}
