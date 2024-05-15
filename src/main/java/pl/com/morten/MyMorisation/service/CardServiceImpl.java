package pl.com.morten.MyMorisation.service;

import org.springframework.stereotype.Service;
import pl.com.morten.MyMorisation.configuration.CardResult;
import pl.com.morten.MyMorisation.dto.CardCreateDto;
import pl.com.morten.MyMorisation.dto.CardDto;
import pl.com.morten.MyMorisation.dto.CardProgressUpdateDto;
import pl.com.morten.MyMorisation.dto.CardsProgressUpdateDto;
import pl.com.morten.MyMorisation.jpa.entity.Cards;
import pl.com.morten.MyMorisation.jpa.repositories.CardsRepository;
import pl.com.morten.MyMorisation.jpa.repositories.UsersRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    final
    CardsRepository cardsRepository;
    final
    UsersRepository usersRepository;

    public void createCard(CardCreateDto cardsCreateDto) throws Exception {
        long currentTime = System.currentTimeMillis();
        if(!doesUserExists(cardsCreateDto.getUserId())) throw new Exception("User not found");
        cardsRepository.save(Cards.builder()
                .userId(cardsCreateDto.getUserId())
                .originalWord(cardsCreateDto.getOriginalWord())
                .translatedWord(cardsCreateDto.getTranslatedWord())
                .progress(1)
                .timeOfNextReview(new Date(currentTime))
                .imageBase64(cardsCreateDto.getImageBase64())
//                .timeOfNextReview(calculateNextDate(new Date(currentTime),1))
                .build());
    }

    public CardServiceImpl(CardsRepository cardsRepository, UsersRepository usersRepository) {
        this.cardsRepository = cardsRepository;
        this.usersRepository = usersRepository;
    }

    public Date calculateNextDate(Date date, int progress){
        int modulo5 = progress%5;
        System.out.println("before " + date.toString());
//        int time = (int) ((modulo5) * pow(100,((double) (progress - modulo5) /5)) * 180);
        int level = (progress - modulo5) /5;
        int time = progress  * 60 * (level * 2 + 3);
        Date newDate = new Date(date.getTime() + time * 1000 * 60);
        System.out.println("New date set to: " + newDate);
        return newDate;
    }



    public List<Cards> getAvailableToReviewCards(long userId) {
        Date currentDate = new Date(System.currentTimeMillis());

        return cardsRepository.findAll()
                .stream()
                .filter((a)-> a.getTimeOfNextReview().before(currentDate) && a.getUserId() == userId)
                .collect(Collectors.toList());
    }


    public List<Cards> getAllCardsForUser(long userId) {
        return cardsRepository.findAll()
                .stream()
                .filter((a)-> a.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public void updateCard(long cardId, CardDto cardDto) {
        Optional<Cards> cards = cardsRepository.findById(cardId);
        if (cards.isPresent()) {
            Cards card = cards.get();
            card.setOriginalWord(cardDto.getOriginalWord());
            card.setTranslatedWord(cardDto.getTranslatedWord());
            card.setImageBase64(cardDto.getImageBase64());
            cardsRepository.save(card);
        }
    }


    public void updateCardsProgress(CardsProgressUpdateDto cardsToUpdate) {
        for(CardProgressUpdateDto cardToUpdate : cardsToUpdate.getCardProgressUpdateDtoList()) {
            Optional<Cards> cards = cardsRepository.findById(cardToUpdate.getCardId());
            if (cards.isPresent()) {
                Cards card = cards.get();
                card.setTimeOfNextReview(calculateNextDate(card.getTimeOfNextReview(), card.getProgress()));
                if (cardToUpdate.getCardResult() == CardResult.SUCCESS) {
                    card.setProgress(card.getProgress() + 1);
                }
//                if (cardToUpdate.getCardResult() == CardResult.FAIL && card.getProgress() > 0) {
//                    card.setProgress(card.getProgress() - 1);
//                }
                cardsRepository.save(card);
            }

        }

    }

    boolean doesUserExists(long userId) {
        return usersRepository.findAll()
                .stream().anyMatch((a) -> a.getId() == userId);
    }

    public void deleteCard(long cardId){
        cardsRepository.deleteById(cardId);
    }


}
