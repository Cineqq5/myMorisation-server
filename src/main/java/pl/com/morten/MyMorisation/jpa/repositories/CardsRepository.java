package pl.com.morten.MyMorisation.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.morten.MyMorisation.jpa.entity.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long> {
}
