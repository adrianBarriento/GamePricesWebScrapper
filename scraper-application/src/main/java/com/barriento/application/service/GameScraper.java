package com.barriento.application.service;

import com.barriento.domain.model.GamePriceReport;
import com.barriento.domain.model.Platform;
import com.barriento.domain.port.output.GamePriceReportProvider;
import com.barriento.domain.port.output.GamePriceReportPublisher;
import com.barriento.domain.port.input.GameScraperUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GameScraper implements GameScraperUseCase {
    private final GamePriceReportProvider gamePriceReportProvider;
    private final GamePriceReportPublisher gamePriceReportPublisher;

    @Override
    public List<GamePriceReport> performScrap(int pageNum, Platform platform) {
        return gamePriceReportProvider.fetchGamesPage(pageNum, platform);
    }
}
