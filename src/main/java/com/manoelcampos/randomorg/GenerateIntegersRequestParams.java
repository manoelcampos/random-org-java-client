package com.manoelcampos.randomorg;

/**
 * @param n           The number of random integers to generate
 * @param min         The minimum value for a generated random int.
 * @param max         The maximum value for a generated random int.
 * @param replacement Enable generation of duplicated integers (true) or not (false).
 * @author Manoel Campos da Silva Filho
 */
record GenerateIntegersRequestParams(int n, int min, int max, boolean replacement, String apiKey)
{
    public static final int MIN_VALUE = -100000000;
    public static final int MAX_VALUE = 100000000;

    GenerateIntegersRequestParams {
        if(n <= 0)
            throw new IllegalArgumentException("n must be greater than 0");

        if(min < GenerateIntegersRequestParams.MIN_VALUE)
            throw new IllegalArgumentException("minValue cannot be smaller than " + MIN_VALUE);

        if(max > GenerateIntegersRequestParams.MAX_VALUE)
            throw new IllegalArgumentException("minValue cannot be higher than " + MAX_VALUE);
    }

    /**
     * Clones the object, copying all fields from the source object,
     * except the {@link #apiKey}, that is set to the informed value.
     *
     * @param apiKey the API key to set in the new object
     */
    GenerateIntegersRequestParams of(final String apiKey){
        return new GenerateIntegersRequestParams(n, min, max, replacement, apiKey);
    }

    /**
     * Instantiates a GenerateIntegersRequestParams that indicates to
     * generate random integers between the [{@link #MIN_VALUE} .. {@link #MAX_VALUE}] interval.
     * Allows generation of duplicated integers.
     *
     * @param n the number of random integers to generate
     */
    GenerateIntegersRequestParams(final int n) {
        this(n, true);
    }

    /**
     * Instantiates a GenerateIntegersRequestParams that indicates to
     * generate random integers between the [{@link #MIN_VALUE} .. {@link #MAX_VALUE}] interval.
     * @param n the number of random integers to generate
     * @param replacement Enable generation of duplicated integers (true) or not (false).
     */
    GenerateIntegersRequestParams(final int n, final boolean replacement) {
        this(n, MIN_VALUE, MAX_VALUE, replacement);
    }

    /**
     * Instantiates a GenerateIntegersRequestParams that indicates to
     * generate random integers between a given [min .. max] interval.
     * Allows generation of duplicated integers.
     *
     * @param n the number of random integers to generate
     * @param min the minimum value for a generated random int
     * @param max the maximum value for a generated random int
     */
    GenerateIntegersRequestParams(final int n, final int min, final int max) {
        this(n, min, max, true);
    }

    GenerateIntegersRequestParams(final int n, final int min, final int max, final boolean replacement){
        this(n, min, max, replacement, "");
    }
}
