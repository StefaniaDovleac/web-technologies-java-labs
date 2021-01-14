package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.User;
import com.unibuc.demo.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string", uses = UserDTO.class)
public interface UserMapper extends EntityMapper<UserDTO, User> {

    User mapToEntity(UserDTO userDTO);

    UserDTO mapToDTO(User user);
}
