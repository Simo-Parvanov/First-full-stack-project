package com.svc.myproject.services;

import com.svc.myproject.domain.models.services.UserServiceModel;
import com.svc.myproject.payload.request.SignupRequest;

import java.util.List;

public interface UserService {
   void createUser(SignupRequest signUpRequest);

   boolean findUserByName(String username);

   boolean findUserByEmail(String email);
   
   List<UserServiceModel> roleUpdate(String username, String method, String roleName);
}
