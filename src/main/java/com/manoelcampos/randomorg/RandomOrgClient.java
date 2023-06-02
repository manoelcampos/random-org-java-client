package com.manoelcampos.randomorg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Client to <a href="https://random.org">https://random.org</a> service.
 * You need to create an account at the website and then an API key.
 * Create a .env file inside your project root directory
 * and insert the line below:
 *
 * <p>
 * RANDOM_ORG_API_KEY=you-api-key
 * </p>
 *
 * or create an environment variable with the name shown above.
 *
 * @author Manoel Campos da Silva Filho
 * @see <a href="https://api.random.org/json-rpc/4/basic">API Docs</a>
 */
public class RandomOrgClient {
    public static final String APIKEY_ENV_VAR_NAME = "RANDOM_ORG_API_KEY";
    public static final String JSONRPC_VERSION = "2.0";
    private final String API_KEY;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    /**
     * Instantiates an object for sending requests to the random.org service
     * to generate real random values.
     * Reads the API Key from an environment variable with the name
     * as indicated by {@link #APIKEY_ENV_VAR_NAME}.
     */
    public RandomOrgClient() {
        this(Dotenv.load().get(APIKEY_ENV_VAR_NAME));
    }

    /**
     * Instantiates an object for sending requests to the random.org service
     * to generate real random values.
     */
    public RandomOrgClient(final String apiKey) {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        API_KEY = apiKey;
        client = HttpClient.newBuilder().build();
    }

    /**
     * Generate n real random integers between [{@link GenerateIntegersRequestParams#MIN_VALUE} .. {@link GenerateIntegersRequestParams#MAX_VALUE}].
     * Allows generation of duplicated integers.
     * @param n number of random integers to generate
     * @return an array containing the random integers
     */
    public int[] generateIntegers(final int n) {
        return generateIntegers(new GenerateIntegersRequestParams(n));
    }

    /**
     * Generate n real random integers between [{@link GenerateIntegersRequestParams#MIN_VALUE} .. {@link GenerateIntegersRequestParams#MAX_VALUE}]
     * <b>that don't repeat (there will be no duplicated numbers).</b>
     *
     * @param n number of random integers to generate
     * @return an array containing the random integers
     */
    public int[] generateNonDuplicatedIntegers(final int n) {
        return generateIntegers(new GenerateIntegersRequestParams(n, false));
    }

    /**
     * Generate n real random integers between [minValue .. maxValue].
     * Allows generation of duplicated integers.
     *
     * @param n number of random integers to generate
     * @param minValue the minimum value for a generated random int
     * @param maxValue the maximum value for a generated random int
     * @return an array containing the random integers
     */
    public int[] generateIntegers(final int n, final int minValue, final int maxValue) {
        return generateIntegers(new GenerateIntegersRequestParams(n, minValue, maxValue));
    }

    /**
     * Generate n real random integers between [minValue .. maxValue]
     * <b>that don't repeat (there will be no duplicated numbers).</b>
     *
     * @param n number of random integers to generate
     * @param minValue the minimum value for a generated random int
     * @param maxValue the maximum value for a generated random int
     * @return an array containing the random integers
     */
    public int[] generateNonDuplicatedIntegers(final int n, final int minValue, final int maxValue) {
        return generateIntegers(new GenerateIntegersRequestParams(n, minValue, maxValue, false));
    }

    private int[] generateIntegers(final GenerateIntegersRequestParams params) {
        final var data = new GenerateIntegersRequestData(params.setApiKey(API_KEY));
        try {
            final var json = objectMapper.writeValueAsString(data);
            final var req =
                    HttpRequest
                            .newBuilder(new URI(GenerateIntegersRequestData.API_PATH))
                            .header("content-type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(json))
                            .build();

            final String res = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
            final var generateIntegersResponse = objectMapper.readValue(res, GenerateIntegersResponse.class);
            return generateIntegersResponse.getResult().getRandom().getData();
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        final var randomService = new RandomOrgClient();
        final int n = 4;
        final int minValue = 1;
        final int maxValue = 10;
        System.out.printf(
                "Generating %d real random integers from [%d ..%d] using %s%n",
                n, minValue, maxValue, randomService.getClass().getSimpleName());
        for (final int number : randomService.generateIntegers(n, minValue, maxValue)) {
            System.out.println(number);
        }
    }
}
