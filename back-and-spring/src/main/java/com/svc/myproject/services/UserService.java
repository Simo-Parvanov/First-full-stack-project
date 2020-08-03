package com.svc.myproject.services;

import com.svc.myproject.payload.request.SignupRequest;

public interface UserService {
   void createUser(SignupRequest signUpRequest);

   boolean findUserByName(String username);

   boolean findUserByEmail(String email);
}
