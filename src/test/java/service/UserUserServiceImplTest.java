package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserUserServiceImplTest {
    private UserUserServiceImpl userServiceImpl;
    private Repository userRepository;

    @BeforeEach
    public void setUp() {
        userServiceImpl = new UserUserServiceImpl();
        userRepository = Mockito.mock(UserRepository.class);
        UserUserServiceImpl.userRepository = userRepository;
    }

    @Test
    public void createUser_whenUserIsValid_shouldCreateUser() {
        User user = new User("testUser", "127.0.0.1");
        userServiceImpl.create(user);

        Mockito.verify(userRepository).create(user);
    }

    @Test
    public void getUser_whenUserExists_shouldReturnUser() {
        long id = 1L;
        User user = new User("testUser", "127.0.0.1");
        Mockito.when(userRepository.get(id)).thenReturn(Optional.of(user));

        Optional<User> actualUser = userServiceImpl.get(id);

        assertEquals(Optional.of(user), actualUser);
    }

    @Test
    public void getUser_whenUserDoesNotExist_shouldReturnEmptyOptional() {
        long id = 1L;
        Mockito.when(userRepository.get(id)).thenReturn(Optional.empty());

        Optional<User> actualUser = userServiceImpl.get(id);

        assertEquals(Optional.empty(), actualUser);
    }
}

