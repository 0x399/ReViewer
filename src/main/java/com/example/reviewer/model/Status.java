package com.example.reviewer.model;

public enum Status {
    PLANNING("Planning"), PLAYING("Playing"), FINISHED("Finished");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    private Status(){};
}
