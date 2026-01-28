package com.JRTP2.Services;

import com.JRTP2.BindingClasses.LoginForm;
import com.JRTP2.BindingClasses.SignupForm;
import com.JRTP2.Entity.UserEntity;
import com.JRTP2.Repositories.UserRepo;
import com.JRTP2.Utils.EmailUtils;
import com.JRTP2.Utils.PwdUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailUtils emailUtils;

    @Override
    public boolean signUp(SignupForm form) {
        UserEntity user = userRepo.findByEmail(form.getEmail());
        if(user != null){
            return false;
        }

        //Todo: Copy the data from binding class to obj
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(form,entity);

        //Todo: Generate Random pwd
//        String tempPwd = PwdUtils.generateRandomPassword();
//        entity.setPassword(tempPwd);

        //Todo: Set AccStatus as Locked
        entity.setAccStatus("LOCKED");

        //Todo: Insert Record
        userRepo.save(entity);

        //Todo: Send email to unlock the Acc
//        String to = form.getEmail();
//        String subject = "Unlock your account | AshokIT";
//        StringBuffer body = new StringBuffer(" ");
//        body.append("<h1>Use below temporary password to Unlock your account</h1>");
//        body.append("Temorary pwd : " + tempPwd);
//        body.append("<br>");
//        body.append("<a href=\"http://localhost:8080/unlock?email="+to+"\">Click here to Unlock your account</a>");
//        emailUtils.sendEmail(to, subject, body.toString());
        return true;
    }

    @Override
    public boolean login(LoginForm form) {

        Optional<UserEntity> entity = Optional.ofNullable(userRepo.findByEmail(form.getEmail()));

        // user not found
        if (entity.isEmpty()) {
            return false;
        }

        UserEntity user = entity.get();

        // compare password
        if (user.getPassword().equals(form.getPassword())) {
            return true;
        }

        return false;
    }
}
