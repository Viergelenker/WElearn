package com.desj.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Julien on 23.05.16.
 */
@Repository
public interface LearningGroupRepository extends JpaRepository<LearningGroup, Integer> {

}
