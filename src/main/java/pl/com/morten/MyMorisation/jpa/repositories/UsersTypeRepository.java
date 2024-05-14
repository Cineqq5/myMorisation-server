package pl.com.morten.MyMorisation.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.morten.MyMorisation.jpa.entity.UserType;

public interface UsersTypeRepository extends JpaRepository<UserType, Long> {

}
