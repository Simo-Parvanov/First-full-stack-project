package com.svc.myproject.services.impl;

import com.svc.myproject.base.TestBase;
import com.svc.myproject.domain.models.services.UpdateRoleServiceModel;
import com.svc.myproject.domain.models.services.UserServiceModel;
import com.svc.myproject.payload.request.SignupRequest;
import com.svc.myproject.repository.UserRepository;
import com.svc.myproject.services.RoleService;
import com.svc.myproject.services.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

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
    @Test
    void userService_roleUpdateWithOptionUpdateRole_ReturnUserWithAddedRole() {
        SignupRequest admin = new SignupRequest();
        admin.setEmail("emo@abv.bg");
        admin.setUsername("emo1123");
        admin.setPassword("123123");

        SignupRequest user = new SignupRequest();
        user.setEmail("ivan@abv.bg");
        user.setUsername("ivan123");
        user.setPassword("123123");

        userService.createUser(admin);
        userService.createUser(user);


        UpdateRoleServiceModel update = new UpdateRoleServiceModel();
        update.setUsername(user.getUsername());
        update.setMethod("update");
        List<UserServiceModel> actual =  userService.roleUpdate(update);
        List<UserServiceModel> expected = userService.allUsers().stream().map(u -> {
            return mapper.map(u, UserServiceModel.class);
        }).collect(Collectors.toList());
        Assert.assertEquals(actual.get(1).getRoles().size(), expected.get(1).getRoles().size());
        Assert.assertEquals(actual.get(1).getRoles().size(), 2);

    }

    @Test
    void userService_roleUpdateWithOptionDeleteRole_ReturnUserWithOneRole() {
        SignupRequest admin = new SignupRequest();
        admin.setEmail("emo@abv.bg");
        admin.setUsername("emo1123");
        admin.setPassword("123123");

        SignupRequest user = new SignupRequest();
        user.setEmail("ivan@abv.bg");
        user.setUsername("ivan123");
        user.setPassword("123123");

        userService.createUser(admin);
        userService.createUser(user);


        UpdateRoleServiceModel update = new UpdateRoleServiceModel();
        update.setUsername(user.getUsername());
        update.setMethod("delete");
        List<UserServiceModel> actual =  userService.roleUpdate(update);
        List<UserServiceModel> expected = userService.allUsers().stream().map(u -> {
            return mapper.map(u, UserServiceModel.class);
        }).collect(Collectors.toList());
        Assert.assertEquals(actual.get(1).getRoles().size(), expected.get(1).getRoles().size());
        Assert.assertEquals(actual.get(1).getRoles().size(), 1);

    }

    @Test
    void userService_roleUpdateWithInvalidUsername_ReturnNull() {
        SignupRequest admin = new SignupRequest();
        admin.setEmail("emo@abv.bg");
        admin.setUsername("emo1123");
        admin.setPassword("123123");

        SignupRequest user = new SignupRequest();
        user.setEmail("ivan@abv.bg");
        user.setUsername("ivan123");
        user.setPassword("123123");

        userService.createUser(admin);
        userService.createUser(user);


        UpdateRoleServiceModel update = new UpdateRoleServiceModel();
        update.setUsername("Kiro");
        update.setMethod("update");
        List<UserServiceModel> actual =  userService.roleUpdate(update);
        Assert.assertNull(actual);

    }

    @Test
    void userService_allUsers_returnAllUsers() {
        SignupRequest admin = new SignupRequest();
        admin.setEmail("emo@abv.bg");
        admin.setUsername("emo1123");
        admin.setPassword("123123");

        SignupRequest user = new SignupRequest();
        user.setEmail("ivan@abv.bg");
        user.setUsername("ivan123");
        user.setPassword("123123");

        userService.createUser(admin);
        userService.createUser(user);

        List<UserServiceModel> actual =  userService.allUsers();
        List<UserServiceModel> expected = userRepository.findAll().stream().map(u -> {
            return mapper.map(u, UserServiceModel.class);
        }).collect(Collectors.toList());
        Assert.assertEquals(actual.size(), expected.size());

    }

    @Test
    void userService_deleteUserWithValidUsername_returnAllUserReducedSize() {
        SignupRequest admin = new SignupRequest();
        admin.setEmail("emo@abv.bg");
        admin.setUsername("emo1123");
        admin.setPassword("123123");

        SignupRequest user = new SignupRequest();
        user.setEmail("ivan@abv.bg");
        user.setUsername("ivan123");
        user.setPassword("123123");

        userService.createUser(admin);
        userService.createUser(user);

        List<UserServiceModel> actual =  userService.deleteUser(user.getUsername());
        List<UserServiceModel> expected = userRepository.findAll().stream().map(u -> {
            return mapper.map(u, UserServiceModel.class);
        }).collect(Collectors.toList());
        Assert.assertEquals(actual.size(), expected.size());
        Assert.assertEquals(actual.size(), 1);
    }
    @Test

    void userService_deleteUserWithInvalidUsername_returnNull() {
        SignupRequest admin = new SignupRequest();
        admin.setEmail("emo@abv.bg");
        admin.setUsername("emo1123");
        admin.setPassword("123123");

        SignupRequest user = new SignupRequest();
        user.setEmail("ivan@abv.bg");
        user.setUsername("ivan123");
        user.setPassword("123123");

        userService.createUser(admin);
        userService.createUser(user);

        List<UserServiceModel> actual =  userService.deleteUser("Kiro");
        Assert.assertNull(actual);
    }
}
