package com.fpoly.huongque.duantotnghiep.constant;

public enum Role {

    ADMIN("DIRE"),
    USER("USER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
