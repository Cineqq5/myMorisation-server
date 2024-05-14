package pl.com.morten.MyMorisation.service;

import pl.com.morten.MyMorisation.dto.UserCreateDto;
import pl.com.morten.MyMorisation.dto.UserInfoDto;
import pl.com.morten.MyMorisation.dto.UsersReadDto;

import java.util.List;

public interface UserService {

    UserInfoDto createUser(UserCreateDto userCreateDto);
    List<UsersReadDto> getUsers();
    void removeUser(long id);
    void updateUser(long userId,
                    UserCreateDto userCreateDto) throws Exception;
    void updateUserRole(long userId, String role) throws Exception;
    UserInfoDto verifyUser(UserCreateDto userCreateDto);
}
