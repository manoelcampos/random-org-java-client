package com.manoelcampos.randomorg;

/**
 * @author Manoel Campos da Silva Filho
 */
record Result(long bitsUsed, long bitsLeft, int requestsLeft, int advisoryDelay, RandomData random) {
}
