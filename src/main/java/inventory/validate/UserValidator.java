package inventory.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import inventory.model.Users;
import inventory.service.UserService;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == Users.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Users user = (Users) target;
		ValidationUtils.rejectIfEmpty(errors, "username", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "msg.required");
		if(user.getId()==null) {
			ValidationUtils.rejectIfEmpty(errors, "name", "msg.required");
		}
		List<Users> users = userService.findByProperty("username", user.getUsername());
		if (users != null && !users.isEmpty()) {
			errors.rejectValue("username", "msg.username.exist");
		}
	}

}
