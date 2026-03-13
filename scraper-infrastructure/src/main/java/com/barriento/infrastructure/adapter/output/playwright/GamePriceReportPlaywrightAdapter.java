package com.barriento.infrastructure.adapter.output.playwright;

import com.barriento.domain.model.GamePriceReport;
import com.barriento.domain.model.Offer;
import com.barriento.domain.model.Platform;
import com.barriento.domain.port.output.GamePriceReportProvider;
import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class GamePriceReportPlaywrightAdapter implements GamePriceReportProvider {

    private static final String BASE_URL = "https://www.clavecd.es/juegos/?pagenum=";

    @Override
    public List<GamePriceReport> fetchGamesPage(int pageNum, Platform platform) {
        final var gameTitle = "elden ring";
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage();

            // Usamos la URL que me has proporcionado
            String url = "https://www.clavecd.es/productos/?search_name=" + gameTitle.replace(" ", "+");
            page.navigate(url);

            // Esperamos a que aparezcan los resultados (usando la clase del precio como ancla)
            page.waitForSelector(".price-skew");

            List<Offer> offers = new ArrayList<>();

            // 1. Localizamos todas las "tarjetas" de juegos
            // Usamos una clase que parece común a los contenedores según tu imagen
            var gameCards = page.locator("div.grid-rows-\\[auto_1fr_auto\\]");

            for (int i = 0; i < gameCards.count(); i++) {
                Locator card = gameCards.nth(i);

                // 2. Extraer Título (el <p> que mencionaste)
                String title = card.locator("p.text-md.svelte-1jbfuqa").first().innerText();

                // Filtro básico: Solo si el título contiene lo que buscamos
                if (title.toLowerCase().contains(gameTitle.toLowerCase())) {

                    // 3. Extraer Precio (el <span> dentro del <a> con clase price-skew)
                    String priceText = card.locator(".price-skew span").innerText();

                    // 4. Extraer Link
                    String shopLink = card.locator("a.price-skew").getAttribute("href");

                    // Limpiamos el precio (ej: "33,29€" -> 33.29)
                    Double priceValue = Double.parseDouble(
                            priceText.replaceAll("[^0-9,]", "").replace(",", ".")
                    );

                    // En este nivel de búsqueda, la "tienda" suele ser la mejor oferta
                    offers.add(new Offer("Oferta Principal", shopLink, priceValue, null));
                }
            }

            browser.close();
            return Collections.singletonList(new GamePriceReport(gameTitle, platform, LocalDateTime.now(), offers));
        } catch (Exception e) {
            throw new RuntimeException("Error al scrapear: " + e.getMessage());
        }
    }

    private Double parsePrice(String priceStr) {
        try {
            return Double.parseDouble(priceStr.replaceAll("[^0-9,]", "").replace(",", "."));
        } catch (Exception e) {
            return 0.0;
        }
    }
}
