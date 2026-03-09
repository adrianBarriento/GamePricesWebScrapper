package com.barriento.config;

import com.barriento.application.service.GameScraper;
import com.barriento.domain.port.output.GamePriceReportProvider;
import com.barriento.domain.port.output.GamePriceReportPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public GameScraper gameScraper(
            GamePriceReportProvider gamePriceReportProvider,
            GamePriceReportPublisher gamePriceReportPublisher
    ) {
        return new GameScraper(gamePriceReportProvider, gamePriceReportPublisher);
    }
}
