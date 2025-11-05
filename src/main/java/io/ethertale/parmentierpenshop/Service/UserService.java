package io.ethertale.parmentierpenshop.Service;

import io.ethertale.parmentierpenshop.Model.Cart;
import io.ethertale.parmentierpenshop.Model.Roles;
import io.ethertale.parmentierpenshop.Model.User;
import io.ethertale.parmentierpenshop.Repo.CartRepo;
import io.ethertale.parmentierpenshop.Repo.UserRepo;
import io.ethertale.parmentierpenshop.Security.AuthenticationDetails;
import io.ethertale.parmentierpenshop.Web.Dto.RegisterUserDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, CartRepo cartRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(RegisterUserDto registerUserDTO){
        //TODO Catch exceptions for user registering (username, password, email)

        User newUser = User.builder()
                .username(registerUserDTO.getUsername())
                .firstName(registerUserDTO.getFirstName())
                .lastName(registerUserDTO.getLastName())
                .email(registerUserDTO.getEmail())
                .password(passwordEncoder.encode(registerUserDTO.getPassword()))
                .role(Roles.USER)
                .orders(new HashSet<>())
                .build();

        Cart newCart = Cart.builder()
                .user(newUser)
                .items(new HashSet<>())
                .totalPrice(BigDecimal.valueOf(0))
                .build();

        userRepo.save(newUser);
        cartRepo.save(newCart);

        newUser.setCart(newCart);
        userRepo.save(newUser);
    }

    public User getUserById(UUID id){
        if (userRepo.findById(id).isEmpty()) {
            throw new UsernameNotFoundException(id.toString());
        }
        return userRepo.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new AuthenticationDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole(), user.getOrders(), user.getCart());
    }
}
