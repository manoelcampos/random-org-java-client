package org.manoelcampos.randomorg;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class RandomData {
    private int[] data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'")
    private LocalDateTime completionTime;

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }
}
