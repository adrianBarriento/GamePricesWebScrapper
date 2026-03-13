package com.barriento.infrastructure.adapter.input.rest;

import com.barriento.domain.model.Platform;
import com.barriento.domain.port.input.GameScraperUseCase;
import com.barriento.infrastructure.adapter.input.rest.api.ScrapingControllerApi;
import com.barriento.infrastructure.adapter.input.rest.model.PlatformDto;
import com.barriento.infrastructure.adapter.input.rest.model.ScrapePageRequestDto;
import com.barriento.infrastructure.adapter.input.rest.model.ScrapeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class GamePriceReportRestController implements ScrapingControllerApi {

    private final GameScraperUseCase gameScraperUseCase;

    @Override
    public ResponseEntity<ScrapeResponseDto> scrapePost(ScrapePageRequestDto scrapePageRequestDto) {

        final PlatformDto platformDto = scrapePageRequestDto.getPlatform();
        final Platform platform = Objects.nonNull(platformDto) ? Platform.valueOf(platformDto.name()) : null;

        gameScraperUseCase.performScrap(scrapePageRequestDto.getPage(), platform);
        return ScrapingControllerApi.super.scrapePost(scrapePageRequestDto);
    }
}
