package com.desj.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sabrina on 14.06.2016.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
