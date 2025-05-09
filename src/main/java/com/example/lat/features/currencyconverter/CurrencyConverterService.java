package com.example.lat.features.currencyconverter;

import com.example.lat.core.handler.exception.ApplicationException;
import com.example.lat.core.handler.exception.ApplicationExceptionReason;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Currency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CurrencyConverterService {
    private CurrencyConverter currencyConverter = new CurrencyConverter();

    public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        return currencyConverter.convert(from, to, amount);
    }

    @Scheduled(fixedRate = 3600000)
    private void fetchRates() {
        log.info("Fetching rates");
        URI url = URI.create("https://open.er-api.com/v6/latest/USD");
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(url)
                            .method("GET", HttpRequest.BodyPublishers.noBody())
                            .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception ex) {
            Thread.currentThread().interrupt();
            throw (ApplicationException)
                    new ApplicationException(ApplicationExceptionReason.FAILED_TO_FETCH_RATES)
                            .initCause(ex);
        }

        currencyConverter = new Gson().fromJson(response.body(), CurrencyConverter.class);
        log.info("Successfully fetched rates");
    }
}
