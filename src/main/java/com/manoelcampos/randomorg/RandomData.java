package com.manoelcampos.randomorg;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @author Manoel Campos da Silva Filho
 */
record RandomData(
    int[] data,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    LocalDateTime completionTime)
{
}
