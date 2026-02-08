package org.obaro.portfolio.musicshow.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    private int status;
    private String error;
    private List<String> messages;
    private LocalDateTime timestamp;

    public ApiError(int status, String error, List<String> messages) {
        this.status = status;
        this.error = error;
        this.messages = messages;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
