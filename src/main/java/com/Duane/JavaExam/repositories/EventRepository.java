package com.Duane.JavaExam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Duane.JavaExam.models.Event;
import com.Duane.JavaExam.models.User;



@Repository
public interface EventRepository extends CrudRepository<Event, Long>{
	List<Event> findAll();
//	List<Event> findByState(String state); 
//	List<Event> findByEvent_StateNot(String st); 
	List<Event> findByattendeesNotContaining(User u);
	List<Event> findByhostContainsAllIgnoreCase(String search);
}
