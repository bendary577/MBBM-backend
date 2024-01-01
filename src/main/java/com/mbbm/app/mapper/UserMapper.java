package com.mbbm.app.mapper;

import com.mbbm.app.model.base.User;
import com.mbbm.app.record.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDTO entityToDTO(User user);

    List<UserDTO> entityToDTO(Iterable<User> project);

    User dtoToEntity(UserDTO project);

    List<User> dtoToEntity(Iterable<UserDTO> projects);
}
