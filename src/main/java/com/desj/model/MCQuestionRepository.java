package com.desj.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Desi on 6/16/2016.
 */
@Repository
public interface MCQuestionRepository extends JpaRepository<MCQuestion,Integer> {
}
