package com.example.TestForms.model;

public enum Type {
    SINGLE_ANSWER("Single Answer"),
    MULTIPLE_ANSWERS("Multiple Answers");

    private final String type;

    public String getType() {
        return type;
    }

    Type(String type) {
        this.type = type;
    }
}
