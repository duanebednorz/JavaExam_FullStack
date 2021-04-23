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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=2, message="First name must be greater than 2 characters")
	@NotEmpty(message="First Name Required.")
	private String first_name;
	
	@Size(min=2, message="Last name must be greater than 2 characters")
	@NotEmpty(message="Last Name Required.")
	private String last_name;
	
	@Size(min=2, message="City must be greater than 2 characters")
	@NotEmpty(message="Last Name Required.")
	private String city;
	@Size(min=2, message="City must be greater than 2 characters")
	@NotNull(message="State Required.")
	private String state;
	
	@Email(message="Email must be valid")
	@NotEmpty(message="Email Required.")
	private String email;
	
	@Size(min=5, message="Password must be greater than 5 characters")
	@NotEmpty(message="Password Required.")
	private String password;
	@Transient
	private String passwordConfirmation;
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
//	***************************************************
	@OneToMany(mappedBy="host", fetch=FetchType.LAZY)
	private List<Event> events; 
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Join> messages; 
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="users_group_message",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name="event_id")
	)
	private List<Event> attending;	
//*********************************************************
	public User() {
	}

	// other getters and setters removed for brevity
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public User(
			@Size(min = 2, message = "First name must be greater than 2 characters") @NotNull(message = "First Name Required.") String first_name,
			@Size(min = 2, message = "Last name must be greater than 2 characters") @NotNull(message = "Last Name Required.") String last_name,
			@Size(min = 2, message = "City must be greater than 2 characters") @NotNull(message = "Last Name Required.") String city,
			@Size(min = 2, message = "City must be greater than 2 characters") @NotNull(message = "State Required.") String state,
			@Email(message = "Email must be valid") @NotNull(message = "Email Required.") String email,
			@Size(min = 5, message = "Password must be greater than 5 characters") String password,
			String passwordConfirmation, Date createdAt, Date updatedAt) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.city = city;
		this.state = state;
		this.email = email;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Event> getAttending() {
		return attending;
	}

	public void setAttending(List<Event> attending) {
		this.attending = attending;
	}

	public List<Join> getMessages() {
		return messages;
	}

	public void setMessages(List<Join> messages) {
		this.messages = messages;
	}
	
	
	
};
