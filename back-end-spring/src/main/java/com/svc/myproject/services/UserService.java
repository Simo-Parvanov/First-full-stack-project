package com.svc.myproject.services;

import com.svc.myproject.domain.entities.User;
import com.svc.myproject.domain.models.services.UpdateRoleServiceModel;
import com.svc.myproject.domain.models.services.UserServiceModel;
import com.svc.myproject.payload.request.SignupRequest;

import java.util.List;

public interface UserService {

   UserServiceModel createUser(SignupRequest signUpRequest);

   boolean findUserByName(String username);

   boolean findUserByEmail(String email);
   
   List<UserServiceModel> roleUpdate(UpdateRoleServiceModel updateRoleServiceModel);

   List<UserServiceModel> allUsers();

   List<UserServiceModel> deleteUser(String username);

   User getUserByUsername(String username);


}
