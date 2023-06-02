package com.manoelcampos.randomorg;

import lombok.Getter;
import lombok.Setter;

/**
 *  @author Manoel Campos da Silva Filho
 */
@Getter @Setter
class GenerateIntegersResponse {
    private String jsonrpc;
    private int id;
    private Result result;
}
