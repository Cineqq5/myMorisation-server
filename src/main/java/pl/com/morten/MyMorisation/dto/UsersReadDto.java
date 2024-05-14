package pl.com.morten.MyMorisation.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import pl.com.morten.MyMorisation.configuration.UserType;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
public class UsersReadDto {

    Long id;
    String username;
    UserType userType;


}
