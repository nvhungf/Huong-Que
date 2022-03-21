package com.fpoly.huongque.duantotnghiep.constant;

public enum OrderEnum {

    CANCEL(0), // huy don hang
    WAIT_APPROVE(1), // cho xac nhan
    APPROVE(2), // xac nhan
    DELIVERING(3), // dang giao hang
    DONE(4);

    private Integer value;

    OrderEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }
}
