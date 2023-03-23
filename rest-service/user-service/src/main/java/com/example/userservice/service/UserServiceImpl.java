package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override // spring security 에서 가장 중요한 메소드임
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username); // 우리 입장에서 username->email 임

        if (userEntity == null) {
            throw new UsernameNotFoundException(username + ": not found!");
        }

        return new User(userEntity.getEmail()
                , userEntity.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>()); // spring framework 에서 제공해주는 User 객체
        // db 값은 이거인데 spring 이 알아서 비교하고 인증처리 해줌
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 데이터값 복사하는 것

        UserEntity userEntity = mapper.map(userDTO, UserEntity.class); // (복사대상, 복사할 곳)
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDTO.getPwd()));

        userRepository.save(userEntity);

        UserDTO returnUserDTO = mapper.map(userEntity, UserDTO.class);

        return returnUserDTO;
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        return null;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return null;
    }

}
