package pl.com.morten.MyMorisation.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
public class CardCreateDto {

    Long userId;

    String originalWord;

    String translatedWord;

    String imageBase64;
}
