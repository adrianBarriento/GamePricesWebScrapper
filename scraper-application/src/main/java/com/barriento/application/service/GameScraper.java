package com.barriento.application.service;

import com.barriento.domain.port.output.GamePriceReportProvider;
import com.barriento.domain.port.output.GamePriceReportPublisher;
import com.barriento.domain.port.input.GameScraperUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameScraper implements GameScraperUseCase {
    private final GamePriceReportProvider gamePriceReportProvider;
    private final GamePriceReportPublisher gamePriceReportPublisher;

    @Override
    public void performScrap() {

    }
}
