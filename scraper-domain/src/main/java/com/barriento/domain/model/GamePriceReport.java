package com.barriento.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class GamePriceReport {
    private String gameTitle;
    private Platform platform;
    private LocalDateTime scrapedAt;
    private List<Offer> offers;

    public Optional<Offer> getBestOffer() {
        return offers.stream()
                .min(Comparator.comparing(Offer::getPrice));
    }
}
