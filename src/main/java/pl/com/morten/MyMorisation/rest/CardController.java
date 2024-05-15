package pl.com.morten.MyMorisation.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.com.morten.MyMorisation.service.CardService;
import pl.com.morten.MyMorisation.dto.CardCreateDto;
import pl.com.morten.MyMorisation.dto.CardDto;
import pl.com.morten.MyMorisation.dto.CardsProgressUpdateDto;
import pl.com.morten.MyMorisation.jpa.entity.Cards;

import java.util.List;

@RestController
@RequestMapping("/cards")
@Tag(name = "Card")
public class CardController {

    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public void createCard(@RequestBody CardCreateDto cardsCreateDto) throws Exception {
        cardService.createCard(cardsCreateDto);
    }

    @GetMapping("/available/user/{userId}")
    public List<Cards> getAvailableToReviewCards(@PathVariable("userId") long userId) {

        return cardService.getAvailableToReviewCards(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Cards> getAllCardsForUser(@PathVariable("userId") long userId) {
        return cardService.getAllCardsForUser(userId);
    }

    @PutMapping("/{cardId}")
    public void updateCard(@PathVariable("cardId") long cardId,
            @RequestBody CardDto cardDto) {
        cardService.updateCard(cardId, cardDto);
    }

    @PostMapping("/progress-update")
    public void updateCardProgress(@RequestBody CardsProgressUpdateDto cardToUpdate) {
        cardService.updateCardsProgress(cardToUpdate);
    }

    @DeleteMapping("/{cardId}")
    public void deleteCard(@PathVariable("cardId") long cardId) {
        cardService.deleteCard(cardId);
    }

    @GetMapping("/user/{userId}/count")
    public int getAllCardsCountForUser(@PathVariable("userId") long userId) {
        return cardService.getAvailableCardCount(userId);
    }


}
