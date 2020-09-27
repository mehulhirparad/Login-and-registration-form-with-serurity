package com.login.registration.controller;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.login.registration.entity.User;
import com.login.registration.service.UserService;
import com.login.registration.user.RegisterUser;

@Controller
@RequestMapping("/registeration")
public class RegistrationController {
	
    @Autowired
    private UserService userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	//  Create the class object before display on the site
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("registerUser", new RegisterUser());		
		return "registration-page";
	}
	// After getting user data it will check for validation is it valid or not ?
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("registerUser") RegisterUser RegisterUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		// Logger use for print msg for cross varification of data out put
		String userName = RegisterUser.getUserName();		
		logger.info("Processing registration form for: " + userName);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		// check the user name if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
        	theModel.addAttribute("registerUser", new RegisterUser());
			theModel.addAttribute("registrationError", "User name already exists.");
			logger.warning("User name already exists.");
        	return "registration-form";
        }
     // create user account   
        System.out.println("mobole form account" + RegisterUser.getMobile());
        userService.save(RegisterUser);        
        logger.info("Successfully created user: " + userName);        
        return "registration-confirmation";		
	}
}
