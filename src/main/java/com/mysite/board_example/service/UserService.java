package com.mysite.board_example.service;

import com.mysite.board_example.dto.CreateUserRequest;
import com.mysite.board_example.dto.CreateUserResponse;
import com.mysite.board_example.entity.User;
import com.mysite.board_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {

        String password = createUserRequest.getPassword();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = User.builder()
                .id(createUserRequest.getId())
                .password(hashedPassword)
                .name(createUserRequest.getName())
                .build();

        User savedUser = userRepository.save(user);

        return CreateUserResponse.builder()
                .userId(savedUser.getUserId())
                .build();
    }
}
