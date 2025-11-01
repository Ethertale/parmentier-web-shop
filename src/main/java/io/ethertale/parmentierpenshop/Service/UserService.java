package io.ethertale.parmentierpenshop.Service;

import io.ethertale.parmentierpenshop.Model.User;
import io.ethertale.parmentierpenshop.Repo.UserRepo;
import io.ethertale.parmentierpenshop.Security.AuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void registerUser(){

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return new AuthenticationDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole(), user.getOrders(), user.getCart());
    }
}
