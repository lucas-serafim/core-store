package com.serafim.core_store.service;

import com.serafim.core_store.dto.CreateUserDTO;
import com.serafim.core_store.dto.UserDTO;
import com.serafim.core_store.exception.EmailAlreadyExistException;
import com.serafim.core_store.model.User;
import com.serafim.core_store.model.UserRoleEnum;
import com.serafim.core_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

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

    public UserDTO mapToDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail());
    }
}
