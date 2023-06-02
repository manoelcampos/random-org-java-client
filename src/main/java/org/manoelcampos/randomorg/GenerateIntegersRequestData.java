package org.manoelcampos.randomorg;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *  @author Manoel Campos da Silva Filho
 */
@Getter @Setter @AllArgsConstructor
class GenerateIntegersRequestData {
    public static final String API_PATH = "https://api.random.org/json-rpc/4/invoke";
    private final int id = 42;
    private final String jsonrpc = RandomOrgService.JSONRPC_VERSION;
    private final String method = "generateIntegers";
    private final GenerateIntegersRequestParams params;
}
