package com.desj.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Julien on 09.06.16.
 */
@Repository
public interface GroupPostRepository extends JpaRepository<GroupPost, Integer> {

}
