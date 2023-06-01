package org.manoelcampos.randomorg;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *  @author Manoel Campos da Silva Filho
 */
@Getter @Setter
public class RandomData {
    private int[] data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    private LocalDateTime completionTime;
}
