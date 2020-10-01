package com.svc.myproject.web;

import com.svc.myproject.domain.entities.ERole;
import com.svc.myproject.domain.entities.Role;
import com.svc.myproject.domain.entities.User;
import com.svc.myproject.repository.UserRepository;
import com.svc.myproject.web.controllers.AuthController;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private  MockMvc mockMvc;
    @Autowired
    private  UserRepository userRepository;

    private long TEST_USER1_ID, TEST_USER2_ID;
    private String TEST_USER1_NAME = "Ceci", TEST_USER2_NAME = "Peci";
    private String TEST_USER1_PASSWORD = "123123", TEST_USER2_PASSWORD = "111111";
    private ERole TEST_USER1_ROLE = ERole.ROLE_USER, TEST_USER2_ROLE = ERole.ROLE_MODERATOR;


    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();

        User user1 = new User();
        Role role1 = new Role();
        role1.setName(TEST_USER1_ROLE);
        user1.setUsername((TEST_USER1_NAME));
        user1.setPassword(TEST_USER1_PASSWORD);
        user1.setRoles( Set.of(role1));
        user1 = userRepository.save(user1);
        TEST_USER1_ID = user1.getId();

        User user2 = new User();
        Role role2 = new Role();
        role2.setName(TEST_USER2_ROLE);
        user2.setUsername((TEST_USER2_NAME));
        user2.setPassword(TEST_USER2_PASSWORD);
        user2.setRoles( Set.of(role2));
        user2 = userRepository.save(user2);
        TEST_USER2_ID = user2.getId();
    }

    @AfterEach
    public void teatDown(){
        userRepository.deleteAll();
    }

    @Test
    public void testCorrectStatusForUserRegister() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/signup"))
                .andExpect(status().isOk());
    }
}
