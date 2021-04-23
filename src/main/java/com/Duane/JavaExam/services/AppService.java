package com.Duane.JavaExam.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.Duane.JavaExam.models.Event;
import com.Duane.JavaExam.models.Join;
import com.Duane.JavaExam.models.User;
import com.Duane.JavaExam.repositories.EventRepository;
import com.Duane.JavaExam.repositories.MessageRepository;
import com.Duane.JavaExam.repositories.UserRepository;

@Service
public class AppService {

	private final UserRepository userRepo;
	private final EventRepository eventRepo;
	private final MessageRepository messageRepo;

	public AppService(UserRepository userRepo, EventRepository eventRepo, MessageRepository messageRepo) {
		this.userRepo = userRepo;
		this.eventRepo = eventRepo;
		this.messageRepo = messageRepo;
	}

//	    User create **********************************************
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed); /* set user object password */
		user.setEmail(user.getEmail().toLowerCase());
		return userRepo.save(user); /* creates new user object */
	}

//		find by email
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

//	 Authenticate user registration  
	public boolean authenticateUser(String email, String password) { // authenticate user
		User user = userRepo.findByEmail(email); // first find the user by email
		if (user == null) { // if we can't find it by email, return false
			return false;
		} else {
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true; // if the passwords match, return true, else, return false
			} else {
				return false;
			}
		}
	}

//	    End of Login Registration
//***********************************************************************************	
//	    User begin**********************************
//	    Standard Queries
//	    **************find all users
	public User allUsers() {
		return (User) userRepo.findAll();
	}

//	    find user by id    
	public User findUserById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

//	    delete
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

//	    User End ***********************************
//	    Event begin*********************************
//		***********find all Events
	public List<Event> allEvents() {
		return eventRepo.findAll();
	}

	public Event createEvent(Event toCreate) {
		return this.eventRepo.save(toCreate);
	}

//	    find Event by Id
	public Event findEById(Long id) {
		return (Event) eventRepo.findById(id).orElse(null);
	}

	// delete
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}

////		Special Query*******************************
//	public List<Event> findByState(String state) {
//		return this.eventRepo.findByState(state);
//	}
////
//////		Special Query**********************************
//	public List<Event> findOutOfState(String st) {
//		return this.eventRepo.findByEvent_StateNot(st);
//	}
	
	public List<Event> findByattendeesNotContaining(User u) {
		return this.eventRepo.findByattendeesNotContaining(u);
	}
	
	public List<Event> searchBy(String search) {
		return eventRepo.findByhostContainsAllIgnoreCase(search);
	}

//	    Event End **************************************
//	    Message begin***********************************
//		************find all messages
	public Join allMessages() {
		return (Join) messageRepo.findAll();
	}

	// create and update
	public Join saveMessage(Join message) {
		return messageRepo.save(message);
	}

//	    find Message by Id
	public Join findMById(Long id) {
		return (Join) messageRepo.findById(id).orElse(null);
	}

	// delete
	public void deleteMessage(Long id) {
		messageRepo.deleteById(id);
	}
//	    Message End ************************************

	public Join createJoin(Join msg) {
		return this.messageRepo.save(msg);
	}
	
	public User leaveJoin(User user) {
		return this.userRepo.save(user);
	}
	
	

	
};

