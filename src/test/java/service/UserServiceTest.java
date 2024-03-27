package service;

import com.javarush.by.vdavdov.entity.User;
import com.javarush.by.vdavdov.repository.Repository;
import com.javarush.by.vdavdov.repository.UserRepository;
import com.javarush.by.vdavdov.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    private UserService userService;
    private Repository userRepository;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        userRepository = Mockito.mock(UserRepository.class);
        UserService.userRepository = userRepository;
    }

    @Test
    public void createUser_whenUserIsValid_shouldCreateUser() {
        User user = new User("testUser", "127.0.0.1");
        userService.create(user);

        Mockito.verify(userRepository).create(user);
    }

    @Test
    public void getUser_whenUserExists_shouldReturnUser() {
        long id = 1L;
        User user = new User("testUser", "127.0.0.1");
        Mockito.when(userRepository.get(id)).thenReturn(Optional.of(user));

        Optional<User> actualUser = userService.get(id);

        assertEquals(Optional.of(user), actualUser);
    }

    @Test
    public void getUser_whenUserDoesNotExist_shouldReturnEmptyOptional() {
        long id = 1L;
        Mockito.when(userRepository.get(id)).thenReturn(Optional.empty());

        Optional<User> actualUser = userService.get(id);

        assertEquals(Optional.empty(), actualUser);
    }
}

