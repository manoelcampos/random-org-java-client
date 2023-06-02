package com.manoelcampos.randomorg;

import lombok.Getter;
import lombok.Setter;

/**
 *  @author Manoel Campos da Silva Filho
 */
@Getter @Setter
public class Result {
    private RandomData random;
    private long bitsUsed;
    private long bitsLeft;
    private int requestsLeft;
    private int advisoryDelay;
}
