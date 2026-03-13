package com.barriento.domain.port.input;

import com.barriento.domain.model.GamePriceReport;
import com.barriento.domain.model.Platform;

import java.util.List;

public interface GameScraperUseCase {

    List<GamePriceReport> performScrap(int pageNum, Platform platform);
}
