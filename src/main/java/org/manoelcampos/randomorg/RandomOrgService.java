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
 * @author Manoel Campos da Silva Filho
 * @see <a href="https://api.random.org/json-rpc/4/basic">API Docs</a>
 */
public class RandomOrgService {
    public static final int MIN_VALUE = -100000000;
    public static final int MAX_VALUE =  100000000;
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

    public RandomOrgService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        final var dotenv = Dotenv.load();
        API_KEY = dotenv.get("RANDOM_ORG_API_KEY");
        client = HttpClient.newBuilder().build();
    }

    /**
     * Generate n real random integers between [{@link #MIN_VALUE} .. {@link #MAX_VALUE}].
     * Allows generation of duplicated integers.
     * @param n number of random integers to generate
     * @return a {@link IntStream} containing the random integers
     */
    public IntStream generateIntegers(final long n) {
        return generateIntegers(n, MIN_VALUE, MAX_VALUE);
    }

    /**
     * Generate n real random integers between [{@link #MIN_VALUE} .. {@link #MAX_VALUE}]
     * <b>that don't repeat (there will be no duplicated numbers).</b>
     *
     * @param n number of random integers to generate
     * @return a {@link IntStream} containing the random integers
     */
    public IntStream generateNonDuplicatedIntegers(final long n) {
        return generateIntegers(n, MIN_VALUE, MAX_VALUE, false);
    }

    /**
     * Generate n real random integers between [minValue .. maxValue].
     * Allows generation of duplicated integers.
     *
     * @param n number of random integers to generate
     * @param minValue the minimum value for a generated random int
     * @param maxValue the maximum value for a generated random int
     * @return a {@link IntStream} containing the random integers
     */
    public IntStream generateIntegers(final long n, final double minValue, final double maxValue) {
        return generateIntegers(n, minValue, maxValue, true);
    }

    /**
     * Generate n real random integers between [minValue .. maxValue]
     * <b>that don't repeat (there will be no duplicated numbers).</b>
     *
     * @param n number of random integers to generate
     * @param minValue the minimum value for a generated random int
     * @param maxValue the maximum value for a generated random int
     * @return a {@link IntStream} containing the random integers
     */
    public IntStream generateNonDuplicatedIntegers(final long n, final double minValue, final double maxValue) {
        return generateIntegers(n, minValue, maxValue, false);
    }

    private IntStream generateIntegers(final long n, final double minValue, final double maxValue, final boolean enableDuplicates) {
        if(minValue < MIN_VALUE)
            throw new IllegalArgumentException("minValue cannot be smaller than " + MIN_VALUE);

        if(maxValue > MAX_VALUE)
            throw new IllegalArgumentException("minValue cannot be higher than " + MAX_VALUE);

        final var json = JSON_REQ_TEMPLATE.formatted(API_KEY, n, minValue, maxValue, enableDuplicates);
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
        final var randomService = new RandomOrgService();
        randomService.generateIntegers(4).forEach(System.out::println);
    }
}
