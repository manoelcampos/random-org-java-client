package com.manoelcampos.randomorg;

/**
 * An interface to be implemented by Random Number Generators.
 *
 * @author Manoel Campos da Silva Filho
 */
public interface RandomNumberGenerator {
    /**
     * Generate n real random integers between [{@link GenerateIntegersRequestParams#MIN_VALUE} .. {@link GenerateIntegersRequestParams#MAX_VALUE}].
     * Allows generation of duplicated integers.
     * @param n total of random integers to generate
     * @return an array containing the random integers
     */
    int[] generateIntegers(int n);

    /**
     * Generate n real random integers between [{@link GenerateIntegersRequestParams#MIN_VALUE} .. {@link GenerateIntegersRequestParams#MAX_VALUE}]
     * <b>that don't repeat (there will be no duplicated numbers).</b>
     *
     * @param n total of random integers to generate
     * @return an array containing the random integers
     */
    int[] generateNonDuplicatedIntegers(int n);

    /**
     * Generate n real random integers between [minValue .. maxValue].
     * Allows generation of duplicated integers.
     *
     * @param n total of random integers to generate
     * @param minValue the minimum value for a generated random int
     * @param maxValue the maximum value for a generated random int
     * @return an array containing the random integers
     */
    int[] generateIntegers(int n, int minValue, int maxValue);

    /**
     * Generate n real random integers between [minValue .. maxValue]
     * <b>that don't repeat (there will be no duplicated numbers).</b>
     *
     * @param n total of random integers to generate
     * @param minValue the minimum value for a generated random int
     * @param maxValue the maximum value for a generated random int
     * @return an array containing the random integers
     */
    int[] generateNonDuplicatedIntegers(int n, int minValue, int maxValue);
}
