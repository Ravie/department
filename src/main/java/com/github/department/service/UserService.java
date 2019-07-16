package com.github.department.service;

import com.github.department.entity.Role;
import com.github.department.entity.User;
import com.github.department.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null)
            return false;

        user.setActive(true);
        user.setAccessLevel(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepo.save(user);

        sendActivationCode(user);

        return true;
    }

    private void sendActivationCode(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Привет, %s\n" +
                            "Для активации аккаунта перейдите по ссылке:\n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(), user.getActivationCode()
            );
            mailSender.send(user.getEmail(), "Активация аккаунта", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        userRepo.save(user);

        return true;
    }

    public List<User> loadAllUsers() {
        return userRepo.findAll();
    }

    public void updateUserSettings(User curUser, User user, String username, Map<String, String> form) throws ServletException {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getAccessLevel().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getAccessLevel().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        if (user.getId().equals(curUser.getId())) {
            httpServletRequest.logout();
        }
    }

    public void updateProfile(User user, String newPassword, String newEmail) {
        String userEmail = user.getEmail();

        boolean isEmailUpdated = (newEmail != null && !newEmail.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(newEmail));

        if (isEmailUpdated) {
            user.setEmail(newEmail);

            if (!StringUtils.isEmpty(newEmail)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (!StringUtils.isEmpty(newPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        userRepo.save(user);

        if (isEmailUpdated) {
            sendActivationCode(user);
        }
    }
}
