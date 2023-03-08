package com.example.knowledgekombat.service.serviceImp;

import com.example.knowledgekombat.model.Course;
import com.example.knowledgekombat.model.User;
import com.example.knowledgekombat.repository.UserRepository;
import com.example.knowledgekombat.security.UserPrincipal;
import com.example.knowledgekombat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUserImage(Long id, byte[] image){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        User author = userRepository.findById(userPrincipal.getId()).orElseThrow(
                () -> new UsernameNotFoundException("Unauthorized admin"));

        User user = userRepository.findById(id).get();
        user.setImageUrl(image);
        userRepository.save(user);
    }
}
