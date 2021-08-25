package com.rb.rbassignment.domain;

public enum Role {
    ADMIN("1", "ROLE_ADMIN"),
    USER("2", "ROLE_USER");
    private final String code;
    private final String symbol;

    Role(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }

}