package com.Duane.JavaExam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Duane.JavaExam.models.Join;

@Repository
public interface MessageRepository extends CrudRepository<Join, Long> {

}
