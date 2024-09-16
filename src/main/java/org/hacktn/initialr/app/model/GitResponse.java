package org.hacktn.initialr.app.model;

public class GitResponse {
    private String status;
    private String message;

    public GitResponse() {
    }

    public GitResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}