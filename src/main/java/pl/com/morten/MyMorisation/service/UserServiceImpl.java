package pl.com.morten.MyMorisation.service;

import org.springframework.stereotype.Service;
import pl.com.morten.MyMorisation.configuration.UserState;
import pl.com.morten.MyMorisation.dto.UserCreateDto;
import pl.com.morten.MyMorisation.dto.UserInfoDto;
import pl.com.morten.MyMorisation.dto.UsersReadDto;
import pl.com.morten.MyMorisation.jpa.entity.Users;
import pl.com.morten.MyMorisation.jpa.repositories.UsersRepository;
import pl.com.morten.MyMorisation.configuration.UserType;
import pl.com.morten.MyMorisation.jpa.repositories.UsersTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    final
    UsersRepository usersRepository;
    final
    UsersTypeRepository usersTypeRepository;

    public UserServiceImpl(UsersRepository usersRepository, UsersTypeRepository usersTypeRepository) {
        this.usersRepository = usersRepository;
        this.usersTypeRepository = usersTypeRepository;
    }

    public UserInfoDto createUser(UserCreateDto userCreateDto){
        UserInfoDto user = verifyUser(userCreateDto);
        if(user.getUserState() == UserState.ACCESS ||
        user.getUserState() == UserState.EXIST_BUT_INVALID) {return user;}
        Users users = usersRepository.save(Users.builder()
                .username(userCreateDto.getUsername())
                .password(userCreateDto.getPassword())
                .build());
        System.out.println(users.getId());
        usersTypeRepository.save(pl.com.morten.MyMorisation.jpa.entity.UserType.
                builder().userId(users.getId()).userType("USER").build());
        user.setId(users.getId());
        user.setRole("USER");
        return user;
    }
    public List<UsersReadDto> getUsers(){
        return usersRepository
                .findAll()
                .stream()
                .map(user -> new UsersReadDto(user.getId(),
                        user.getUsername(),
                        usersTypeRepository.findAll()
                                .stream()
                                .anyMatch((a) ->
                                        a.getId() == user.getId()
                                                && a.getUserType().equals("ADMIN"))? UserType.ADMIN: UserType.USER))
                .collect(Collectors.toList());
    }
    public void removeUser(long id){
        usersRepository.deleteById(id);
    }
    public void updateUser(long userId,
                           UserCreateDto userCreateDto) throws Exception {
        findUser(userId);
        Users user = usersRepository.findById(userId).get();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        usersRepository.save(user);

    }
    public void updateUserRole(long userId, String role) throws Exception {
        findUser(userId);
        pl.com.morten.MyMorisation.jpa.entity.UserType userType = usersTypeRepository
                .findById(userId).get();
        userType.setUserType(role);
        usersTypeRepository.save(userType);
    }


    public UserInfoDto verifyUser(UserCreateDto userCreateDto) {
        List<Users> users = usersRepository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(userCreateDto.getUsername()))
                .toList();
        UserInfoDto userInfoDto = new UserInfoDto();
        if(users.isEmpty()) {
            userInfoDto.setUserState(UserState.NOT_EXIST);
            return userInfoDto;
        }
        userInfoDto.setUserState(users.get(0)
                .getPassword()
                .equals(userCreateDto.getPassword())?
                UserState.ACCESS : UserState.EXIST_BUT_INVALID);
        long userId = users.get(0).getId();
        System.out.println(userId);
        userInfoDto.setId(userId);
        userInfoDto.setRole(usersTypeRepository
                .findAll()
                .stream()
                .filter(uT -> uT.getUserId() == userId)
                .findFirst()
                .get()
                .getUserType());
        return userInfoDto;
    }

    public void findUser(long userId) throws Exception {
        Optional<Users> users = usersRepository.findById(userId);
        if (users.isEmpty()) {
            throw new Exception("User not found");
        }
    }
}
