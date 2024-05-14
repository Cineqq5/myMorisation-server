package pl.com.morten.MyMorisation.dto;

import pl.com.morten.MyMorisation.configuration.CardResult;

public class CardProgressUpdateDto{

    private Long cardId;

    private CardResult cardResult;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public CardResult getCardResult() {
        return cardResult;
    }

    public void setCardResult(CardResult cardResult) {
        this.cardResult = cardResult;
    }
}
