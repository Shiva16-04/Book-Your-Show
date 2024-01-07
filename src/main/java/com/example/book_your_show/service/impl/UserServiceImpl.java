package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.User;
import com.example.book_your_show.entities.UserEmailVerificationCodeDetails;
import com.example.book_your_show.enums.Role;
import com.example.book_your_show.exceptions.InValidEmailVerificationCodeException;
import com.example.book_your_show.exceptions.PasswordReTypePasswordNotMatchException;
import com.example.book_your_show.exceptions.UserAlreadyPresentException;
import com.example.book_your_show.generators.EmailGenerator;
import com.example.book_your_show.generators.UserCodeGenerator;
import com.example.book_your_show.repository.UserEmailVerificationCodeDetailsRepository;
import com.example.book_your_show.repository.UserRepository;
import com.example.book_your_show.requestDTO.UserEmailRequest;
import com.example.book_your_show.requestDTO.UserEmailVerificationCodeRequest;
import com.example.book_your_show.requestDTO.UserRequest;
import com.example.book_your_show.service.MailConfigurationService;
import com.example.book_your_show.service.UserEmailVerificationCodeDetailsService;
import com.example.book_your_show.service.UserService;
import com.example.book_your_show.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.book_your_show.service.impl.MailConfigurationServiceImpl.SENDER_EMAIL;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCodeGenerator userCodeGenerator;
    @Autowired
    private EmailGenerator emailGenerator;
    @Autowired
    private MailConfigurationService mailConfigurationService;
    @Autowired
    private UserEmailVerificationCodeDetailsService userEmailVerificationCodeDetailsService;
    @Autowired
    private UserEmailVerificationCodeDetailsRepository userEmailVerificationCodeDetailsRepository;

    //Method 1: add Details
    public String addUser(UserRequest userRequest)throws Exception{
        Optional<User>optionalUser=userRepository.findByEmailId(userRequest.getEmailId());
        if(optionalUser.isPresent()){
            throw new UserAlreadyPresentException("User already Registered");
        }
        //validating the match of password and retype password
        if(!userRequest.getPassword().equals(userRequest.getReTypePassword())){
            throw new PasswordReTypePasswordNotMatchException("Password and reType password did not match");
        }

        //validating the email of the user
        String codeFromUser=userRequest.getEmailVerificationCode();
        mailValidation(userRequest.getEmailId(), codeFromUser);

        User user= UserTransformer.userRequestToUser(userRequest);
        String code=userCodeGenerator.generate("USR");

        //setting the attributes for user
        user.setCode(code);
        user.setRole(Role.USER);

        User savedUser=userRepository.save(user);

        //sending registration confirmation mail to the user
        String emailBody=emailGenerator.userSuccessfulRegistrationMessageEmailGenerator(user.getName());
        mailConfigurationService.mailSender(SENDER_EMAIL,user.getEmailId(), emailBody, "User Registration Confirmation");

        return "User "+savedUser.getName()+" has been registered successfully";
    }
    public String sendEmailValidationCode(UserEmailRequest userEmailRequest)throws Exception{
        String email=userEmailRequest.getEmail();
        String code=emailGenerator.userEmailValidationCodeGenerator();
        Optional<UserEmailVerificationCodeDetails> optionalUserEmailVerificationCode=userEmailVerificationCodeDetailsService.findUserEmailVerificationCodeByEmailId(email);

        if(optionalUserEmailVerificationCode.isPresent()){
            UserEmailVerificationCodeDetails userEmailVerificationCodeDetailsCode =optionalUserEmailVerificationCode.get();
            userEmailVerificationCodeDetailsCode.setVerificationCode(code);
            userEmailVerificationCodeDetailsRepository.save(userEmailVerificationCodeDetailsCode);
        }else{
            userEmailVerificationCodeDetailsService.addUserEmailVerificationCode(new UserEmailVerificationCodeRequest(email, code));
        }
        mailConfigurationService.mailSender("applicationtesting1604@gmail.com",email, code, "Email Validation Code");
        return "Verification code sent successfully to the mail"+email;
    }
    //below methods are used for internal purposes...not for api calling
    private boolean mailValidation(String email, String code)throws Exception{
        Optional<UserEmailVerificationCodeDetails> optionalUserEmailVerificationCode=userEmailVerificationCodeDetailsService.findUserEmailVerificationCodeByEmailId(email);
        if(optionalUserEmailVerificationCode.isPresent()){
            String userEmailCode=optionalUserEmailVerificationCode.get().getVerificationCode();
            if(userEmailCode.equals(code))return true;
            else throw  new InValidEmailVerificationCodeException("Invalid Code");
        }else{
            throw  new InValidEmailVerificationCodeException("Invalid Code!!!");
        }
    }
}
