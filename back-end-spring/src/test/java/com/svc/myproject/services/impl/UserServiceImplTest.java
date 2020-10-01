package com.svc.myproject.services.impl;

import com.svc.myproject.base.TestBase;
import com.svc.myproject.domain.entities.ERole;
import com.svc.myproject.domain.entities.Role;
import com.svc.myproject.domain.models.services.UserServiceModel;
import com.svc.myproject.payload.request.SignupRequest;
import com.svc.myproject.repository.UserRepository;
import com.svc.myproject.services.RoleService;
import com.svc.myproject.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserServiceImplTest extends TestBase{
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;

    ModelMapper mapper;
    UserService userService;

    @BeforeEach
    public void map() {
        this.mapper = new ModelMapper();
        userRepository.deleteAll();
        userService = new UserServiceImpl(userRepository, passwordEncoder, roleService, mapper);
    }

//    @MockBean
//    UserRepository userRepository;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    ModelMapper mapper;

//    User testUser;
//    Role testRole;
//    UserRepository mockedUserRepository;
//    BCryptPasswordEncoder passwordEncoder;
//    ModelMapper mapper;
//    RoleService roleService;

    //    @BeforeEach
    public void init() {
//       testRole = new Role(){{
//           setId(1);
//            setName(ERole.ROLE_USER);
//        }};
//       testUser = new User(){{
//            setId((long) 1);
//           setUsername("Ivan1");
//           setEmail("ivan@abv.bg");
//           setRoles(Set.of(testRole));
//        }};
//        mockedUserRepository = Mockito.mock(UserRepository.class);
    }

    @Test
    void userService_createUserWithCorrectValues_ReturnCorrectResult() {
        SignupRequest toBeSaved = new SignupRequest();

        toBeSaved.setEmail("ivan@abv.bg");
        toBeSaved.setUsername("ivan123");
        toBeSaved.setPassword("123123");

        UserServiceModel actual = mapper.map(userService.createUser(toBeSaved), UserServiceModel.class);
        UserServiceModel expected = mapper.map(userRepository.findAll().get(0), UserServiceModel.class);

        Assert.assertEquals(actual.getUsername(), expected.getUsername());
        Assert.assertEquals(actual.getEmail(), expected.getEmail());
        Assert.assertEquals(actual.getId(), expected.getId());
    }

    @Test()
    void userService_createUserWithExistingUsername_ThrowException() {
        SignupRequest toBeSaved1 = new SignupRequest();

        toBeSaved1.setEmail("ivan@abv.bg");
        toBeSaved1.setUsername("ivan123");
        toBeSaved1.setPassword("123123");

        SignupRequest toBeSaved2 = new SignupRequest();

        toBeSaved2.setEmail("ivan2@abv.bg");
        toBeSaved2.setUsername("ivan123");
        toBeSaved2.setPassword("123123");

        mapper.map(userService.createUser(toBeSaved1), UserServiceModel.class);
        assertThrows(
                IllegalArgumentException.class,
                () -> mapper.map(userService.createUser(toBeSaved2), UserServiceModel.class));

    }

    @Test()
    void userService_createUserWithExistingEmail_ThrowException() {
        SignupRequest toBeSaved1 = new SignupRequest();

        toBeSaved1.setEmail("ivan@abv.bg");
        toBeSaved1.setUsername("ivan123");
        toBeSaved1.setPassword("123123");

        SignupRequest toBeSaved2 = new SignupRequest();

        toBeSaved2.setEmail("ivan@abv.bg");
        toBeSaved2.setUsername("Emo1111");
        toBeSaved2.setPassword("123123");

        mapper.map(userService.createUser(toBeSaved1), UserServiceModel.class);
        assertThrows(
                IllegalArgumentException.class,
                () -> mapper.map(userService.createUser(toBeSaved2), UserServiceModel.class));

    }

    @Test()
    void userService_createUserWithIncorrectValues_ThrowException() {
        SignupRequest toBeSaved = new SignupRequest();

        toBeSaved.setEmail("i");
        toBeSaved.setUsername("ivan123");
        toBeSaved.setPassword(null);

        assertThrows(
                IllegalArgumentException.class,
                () -> mapper.map(userService.createUser(toBeSaved), UserServiceModel.class));

    }

//    @Test
//    void findUserByName() {
//    }
//
//    @Test
//    void findUserByEmail() {
//    }
//
//    @Test
//    void roleUpdate() {
//    }
//
//    @Test
//    void allUsers() {
//    }
//
//    @Test
//    void deleteUser() {
//    }
}