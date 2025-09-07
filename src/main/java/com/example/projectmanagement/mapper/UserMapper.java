package com.example.projectmanagement.mapper;

import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.model.Program;

public class UserMapper {

    public static UserDto toDTO(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setProgram(user.getProgram());
        dto.setProjectTitle(user.getProjectTitle());
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);

      /*  if (user.getProgram() != null) {
            dto.setProgramId(user.getProgram().getId());
            dto.setProgramName(user.getProgram().getName());
        }


       */
        return dto;
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setProgram(dto.getProgram());
        user.setProjectTitle(dto.getProjectTitle());

        if (dto.getRole() != null) {
            try {
                User.Role roleEnum = User.Role.valueOf(dto.getRole().toUpperCase());
                user.setRole(roleEnum);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid role: " + dto.getRole());
            }
        }

       /* if (dto.getProgramId() != null) {
            Program program = new Program();
            program.setId(dto.getProgramId());
            user.setProgram(program);
        }


        */
        return user;
    }
}
