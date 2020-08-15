package inventory.validate;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import inventory.model.Users;
import inventory.service.UserService;
import inventory.util.HashingPassword;

@Component
public class LoginValidate implements Validator{

	@Autowired
	private UserService userService;
	
	public boolean supports(Class<?> clazz) {
		// Chi chophep User su dung
		return clazz == Users.class;
	}

	//Target la user tu72 form gui len
	public void validate(Object target, Errors errors) {
		Users user = (Users) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "msg.required");
		if(!StringUtils.isEmpty(user.getUsername()) && !StringUtils.isEmpty(user.getPassword())) {
			List<Users> users = userService.findByProperty("username", user.getUsername());
			if(user!=null && !users.isEmpty()) {
				if(!users.get(0).getPassword().equals(HashingPassword.encrypt(user.getPassword()))) {
					errors.rejectValue("password", "msg.wrong.password");
				}
			}else {
				errors.rejectValue("username", "msg.wrong.username");
			}
		}
		
	}

}
