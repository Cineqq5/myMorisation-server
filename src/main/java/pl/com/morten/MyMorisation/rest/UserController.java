package pl.com.morten.MyMorisation.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.com.morten.MyMorisation.dto.UserCreateDto;
import pl.com.morten.MyMorisation.dto.UserInfoDto;
import pl.com.morten.MyMorisation.dto.UsersReadDto;
import pl.com.morten.MyMorisation.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
//    @PreAuthorize("hasRole('USER')")
    public UserInfoDto createUser(@RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<UsersReadDto> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable("userId") long userId,
                           @RequestBody UserCreateDto updateUserDTO) throws Exception {
        userService.updateUser(userId,updateUserDTO);
    }

    @PutMapping("/{userId}/role/{role}")
    public void updateUserRole(@PathVariable("userId") long userId,
                               @PathVariable("role") String role) throws Exception {
        userService.updateUserRole(userId,role);
    }
    @PostMapping("/verify")
    public UserInfoDto verifyUser(@RequestBody UserCreateDto userCreateDto){
        return userService.verifyUser(userCreateDto);
    }


}
