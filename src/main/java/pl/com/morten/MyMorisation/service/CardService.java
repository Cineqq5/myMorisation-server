package pl.com.morten.MyMorisation.service;

import pl.com.morten.MyMorisation.dto.CardCreateDto;
import pl.com.morten.MyMorisation.dto.CardDto;
import pl.com.morten.MyMorisation.dto.CardsProgressUpdateDto;
import pl.com.morten.MyMorisation.jpa.entity.Cards;

import java.util.Date;
import java.util.List;

public interface CardService {
    Date calculateNextDate(Date date, int progress);
    void createCard(CardCreateDto cardsCreateDto) throws Exception;

    List<Cards> getAvailableToReviewCards(long userId);


    List<Cards> getAllCardsForUser(long userId);

    void updateCard(long cardId, CardDto cardDto);
    void updateCardsProgress(CardsProgressUpdateDto cardToUpdate);

    void deleteCard(long cardId);
}
