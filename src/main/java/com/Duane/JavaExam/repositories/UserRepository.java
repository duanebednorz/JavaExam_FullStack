package com.Duane.JavaExam.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Duane.JavaExam.models.Event;
import com.Duane.JavaExam.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByEmail(String email);
	Long countByEvents(Event event);
}
