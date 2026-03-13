package com.barriento.domain.port.output;

import com.barriento.domain.model.GamePriceReport;
import com.barriento.domain.model.Platform;

import java.util.List;

public interface GamePriceReportProvider {

    List<GamePriceReport> fetchGamesPage(int pageNum, Platform platform);
}
