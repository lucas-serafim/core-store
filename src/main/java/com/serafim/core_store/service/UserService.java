package com.serafim.core_store.service;

import com.serafim.core_store.dto.CreateUserDTO;
import com.serafim.core_store.dto.UserDTO;
import com.serafim.core_store.dto.UserLoginDTO;
import com.serafim.core_store.dto.UserLoginResponseDTO;
import com.serafim.core_store.exception.EmailAlreadyExistException;
import com.serafim.core_store.model.User;
import com.serafim.core_store.model.UserRoleEnum;
import com.serafim.core_store.repository.UserRepository;
import com.serafim.core_store.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public UserDTO create(CreateUserDTO dto) {
        if (this.repository.findByEmail(dto.email()) != null) {
            throw new EmailAlreadyExistException("Email already exists. " + dto.email());
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User user = new User(
                dto.name(),
                dto.email(),
                encryptedPassword,
                UserRoleEnum.CUSTOMER
        );

        this.repository.save(user);

        return this.mapToDTO(user);
    }

    public UserLoginResponseDTO login(UserLoginDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return new UserLoginResponseDTO(token);
    }

    public UserDTO mapToDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail());
    }
}
