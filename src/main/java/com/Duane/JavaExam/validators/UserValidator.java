package com.Duane.JavaExam.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.Duane.JavaExam.models.User;
import com.Duane.JavaExam.services.AppService;

@Component
public class UserValidator implements Validator {
	
	private final AppService appServ;
	

	public UserValidator(AppService appServ) {
		super();
		this.appServ = appServ;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		
		if(!user.getPasswordConfirmation().equals(user.getPassword())){
		
			 errors.rejectValue("passwordConfirmation", "Match");
		}
		
		
		if(this.appServ.findByEmail(user.getEmail().toLowerCase()) != null) {
			errors.rejectValue("email", "dupEmail");
		}
	}
	
};
