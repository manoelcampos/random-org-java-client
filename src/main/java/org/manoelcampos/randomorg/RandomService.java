package org.manoelcampos.randomorg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Client to htts://random.org service.
 * Inspired by {@link java.util.random.RandomGenerator} interface.
 * @author Manoel Campos da Silva Filho
 */
public class RandomService {
    private static final int MIN_VALUE = -100000000;
    private static final int MAX_VALUE =  100000000;
    private static final boolean ENABLE_DUPLICATES = true;
    private final String API_KEY;
    private static final String API_PATH = "https://api.random.org/json-rpc/4/invoke";
    private static final String JSON_REQ_TEMPLATE =
            """
            {
                "jsonrpc": "2.0",
                "method": "generateIntegers",
                "params": {
                    "apiKey": "%s",
                    "n": %d,
                    "min": %d,
                    "max": %d,
                    "replacement": %s
                },
                "id": 42
            }
            """;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public RandomService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        final var dotenv = Dotenv.load();
        API_KEY = dotenv.get("RANDOM_ORG_API_KEY");
        client = HttpClient.newBuilder().build();
    }

    public IntStream ints(final long streamSize) {
        final var json = JSON_REQ_TEMPLATE.formatted(API_KEY, streamSize, MIN_VALUE, MAX_VALUE, ENABLE_DUPLICATES);
        try {
            final var req =
                    HttpRequest
                            .newBuilder(new URI(API_PATH))
                            .header("content-type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(json))
                            .build();

            final String res = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
            final var generateIntegersResponse = objectMapper.readValue(res, GenerateIntegersResponse.class);
            return Arrays.stream(generateIntegersResponse.getResult().getRandom().getData());
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        final var randomService = new RandomService();
        randomService.ints(4).forEach(System.out::println);
    }
}
