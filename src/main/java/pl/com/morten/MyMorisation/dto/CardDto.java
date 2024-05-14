package pl.com.morten.MyMorisation.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
public class CardDto {


    String originalWord;

    String translatedWord;
}
