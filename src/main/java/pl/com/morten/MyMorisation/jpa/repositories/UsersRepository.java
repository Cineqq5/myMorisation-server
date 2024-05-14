package pl.com.morten.MyMorisation.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.morten.MyMorisation.jpa.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
