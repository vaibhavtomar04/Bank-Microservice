package com.example.cards.Service;

import com.example.cards.DTO.CardsDto;

public interface ICardsService {

    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updatecard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);


}
