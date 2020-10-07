package com.gourav.expensetracker.services;

import com.gourav.expensetracker.domain.User;
import com.gourav.expensetracker.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email,String password) throws EtAuthException;

     User registerUser(String lastName,String firstName,String email,String password) throws EtAuthException;
}
