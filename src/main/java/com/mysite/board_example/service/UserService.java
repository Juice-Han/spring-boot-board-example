package com.mysite.board_example.service;

import com.mysite.board_example.dto.CheckIdDuplicationResponse;
import com.mysite.board_example.dto.CreateUserRequest;
import com.mysite.board_example.dto.CreateUserResponse;
import com.mysite.board_example.dto.DeleteUserResponse;
import com.mysite.board_example.entity.User;
import com.mysite.board_example.error.ErrorCode;
import com.mysite.board_example.error.UserDoesntExistException;
import com.mysite.board_example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public DeleteUserResponse deleteUser(Integer userId) {
        Optional<User> op_user = userRepository.findById(userId);
        if(op_user.isEmpty()){
            throw new UserDoesntExistException("user doesnt exist", ErrorCode.USER_DOESNT_EXIST);
        }
        User user = op_user.get();

        userRepository.delete(user);

        return DeleteUserResponse.builder()
                .userId(userId)
                .build();
    }

    public CheckIdDuplicationResponse checkIdDuplication(String checkingId) {
        Optional<User> op_user = userRepository.findUserById(checkingId);
        CheckIdDuplicationResponse checkIdDuplicationResponse = new CheckIdDuplicationResponse();
        checkIdDuplicationResponse.setCheckingId(checkingId);

        checkIdDuplicationResponse.setIsPossible(op_user.isEmpty());
        return checkIdDuplicationResponse;
    }
}
