package pl.com.morten.MyMorisation.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
public class CardDto {


    String originalWord;

    String translatedWord;


    @Column(columnDefinition="TEXT", length = 1000000)
    String imageBase64;
}
