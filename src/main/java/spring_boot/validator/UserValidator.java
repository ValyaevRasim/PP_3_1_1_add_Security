package spring_boot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import spring_boot.entity.User;
import spring_boot.service.UserService;

/**
 * validator for (@link spring_boot.validator.User) class
 * implements (@link Validator) interface
 * https://www.youtube.com/watch?v=iivY8B5A0Tk
 */

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if ((user.getUsername().length() < 5) || (user.getUsername().length() > 32)) {
            errors.rejectValue("username","Size.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","Required");
        if ((user.getPassword().length() >5)){
            errors.rejectValue("password","Size.userForm.password");
        }
        if(!user.getPassword().equals(user.getPasswordConfirm())){
            errors.rejectValue("passwordConfirm","Different.userForm.password");
        }
    }
}
