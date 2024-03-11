//package com.example.demo.security;
//
//import com.example.demo.Utils;
//import com.example.demo.model.persistence.User;
//import com.example.demo.model.persistence.repositories.UserRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//import static com.example.demo.controllers.CartControllerTest.USERNAME;
//import static com.example.demo.controllers.UserControllerTest.PASSWORD;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class UserDetailsSecretServiceTest {
//    private final UserRepository userRepo = mock(UserRepository.class);
//    private UserDetailsSecretService userDetailsSecretService;
//
//    @Before
//    public void setUp() {
//        userDetailsSecretService = new UserDetailsSecretService(userRepo);
//        Utils.injectObjects(userDetailsSecretService, "applicationUserRepository", userRepo);
//    }
//    @Test
//    public void loadUser() {
//        String username = USERNAME;
//        User user = new User();
//        user.setUsername(username);
//        String password = PASSWORD;
//        user.setPassword(password);
//        user.setId(0L);
//        when(userRepo.findByUsername(username)).thenReturn(user);
//        UserDetails userDetails = userDetailsSecretService.loadUserByUsername(username);
//        assertNotNull(userDetails);
//        Collection<? extends GrantedAuthority> authorityCollection = userDetails.getAuthorities();
//        assertNotNull(authorityCollection);
//        assertEquals(0, authorityCollection.size());
//        assertEquals(username, userDetails.getUsername());
//        assertEquals(password, userDetails.getPassword());
//    }
//}
