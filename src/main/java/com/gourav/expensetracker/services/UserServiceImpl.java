package com.gourav.expensetracker.services;

import com.gourav.expensetracker.domain.User;
import com.gourav.expensetracker.exceptions.EtAuthException;
import com.gourav.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if(email!=null) email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String lastName, String firstName, String email, String password) throws EtAuthException {
        Pattern pattern=Pattern.compile("^(.+)@(.+)$");
        if(email!=null) email=email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("invalid email format");
        Integer count=userRepository.getCountByEmail(email);
        if(count>0)
            throw  new EtAuthException("Email already in use");
        Integer userId=userRepository.create(firstName,lastName,email,password);
        return userRepository.findById(userId);
    }
}
