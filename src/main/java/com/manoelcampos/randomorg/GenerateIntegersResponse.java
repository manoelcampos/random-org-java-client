package com.manoelcampos.randomorg;

/**
 * @author Manoel Campos da Silva Filho
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
record GenerateIntegersResponse(String jsonrpc, int id, Result result, Object error) {
}
