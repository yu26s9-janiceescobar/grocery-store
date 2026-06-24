package org.yearup.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yearup.exception.ResourceNotFoundException;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;
import org.yearup.repository.ShoppingCartRepository;
import org.yearup.repository.UserRepository;

import java.util.List;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public UserService(UserRepository userRepository, ShoppingCartRepository shoppingCartRepository)
    {
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public User getUserById(Long userId)
    {
        return userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found: " + userId));
    }

    public User getByUserName(String username)
    {
        return userRepository.findByUsername(username);
    }

    public Long getIdByUsername(String username)
    {
        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new ResourceNotFoundException("User Not Found: " + username);
        }

        return user.getId();
    }

    public boolean exists(String username)
    {
        return userRepository.existsByUsername(username);
    }

    public User create(User user)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        shoppingCartRepository.save(new ShoppingCart(user));
        return userRepository.save(user);
    }
}
