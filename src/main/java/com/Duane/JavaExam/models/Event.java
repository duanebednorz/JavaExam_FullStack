package com.Duane.JavaExam.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "events") /* sets table in DB */
public class Event {

	@Id /* generates Id for question model in questions table */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotEmpty(message = "Event name cannot be blank")
	@Size(min = 2, message = "Must be 2 characters or more")
	private String event_name;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}
//	***************************************
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User host; 
	
	@OneToMany(mappedBy="event", fetch=FetchType.LAZY)
	private List<Join> messages; 
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="users_group_message",
		joinColumns = @JoinColumn(name="event_id"),
		inverseJoinColumns = @JoinColumn(name="user_id")
	)
	private List<User> attendees; 
	
	
//	*********************************************************************
	public Event() {}

public Event(
		@NotEmpty(message = "Idea cannot be blank") @Size(min = 2, message = "Must be 2 characters or more") String event_name,
		Date createdAt, Date updatedAt, User host, List<Join> messages, List<User> attendees) {
	super();
	this.event_name = event_name;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
	this.host = host;
	this.messages = messages;
	this.attendees = attendees;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getEvent_name() {
	return event_name;
}

public void setEvent_name(String event_name) {
	this.event_name = event_name;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}

public User getHost() {
	return host;
}

public void setHost(User host) {
	this.host = host;
}

public List<Join> getMessages() {
	return messages;
}

public void setMessages(List<Join> messages) {
	this.messages = messages;
}

public List<User> getAttendees() {
	return attendees;
}

public void setAttendees(List<User> attendees) {
	this.attendees = attendees;
}

	

	
	
	
	
};