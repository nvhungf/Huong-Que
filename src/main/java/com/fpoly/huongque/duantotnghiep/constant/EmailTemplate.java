package com.fpoly.huongque.duantotnghiep.constant;

public enum EmailTemplate {

    VERIFY_ACCOUNT_TEMPLATE("verify-account.ftl"),
    FORGET_PASSWORD("email-forgot.ftl"),
    FORGET_PASSWORD_OTP("email-forgot2.ftl"),
    ORDER_NOTICE("order-notice.ftl"),
    APPROVE_ORDER("approve-order.ftl"),
    CANCEL_ORDER("cancel-order.ftl"),
    DELIVERED_ORDER("delivered-order.ftl");

    private String template;

    EmailTemplate(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }
}
