package pl.com.morten.MyMorisation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
@Data
public class CardsProgressUpdateDto implements Serializable {
    private List<CardProgressUpdateDto> cardProgressUpdateDtoList;
}
