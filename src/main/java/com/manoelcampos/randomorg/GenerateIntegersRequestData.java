package com.manoelcampos.randomorg;

/**
 * @author Manoel Campos da Silva Filho
 */
record GenerateIntegersRequestData(int id, String jsonrpc, String method, GenerateIntegersRequestParams params)
{
    public static final String API_PATH = "https://api.random.org/json-rpc/4/invoke";

    public GenerateIntegersRequestData(GenerateIntegersRequestParams params, final String apiKey) {
        this(params.of(apiKey));
    }

    public GenerateIntegersRequestData(GenerateIntegersRequestParams params) {
        this(42, RandomOrgClient.JSONRPC_VERSION, "generateIntegers", params);
    }
}
