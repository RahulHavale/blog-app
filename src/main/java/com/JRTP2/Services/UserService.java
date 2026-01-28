package com.JRTP2.Services;

import com.JRTP2.BindingClasses.LoginForm;
import com.JRTP2.BindingClasses.SignupForm;

public interface UserService {

    public boolean signUp(SignupForm form);
    public boolean login(LoginForm form);
}
