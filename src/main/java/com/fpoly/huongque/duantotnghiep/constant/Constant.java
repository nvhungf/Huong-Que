package com.fpoly.huongque.duantotnghiep.constant;

public class Constant {

    public static class Role {
        public final static String ADMIN = "DIRE";
        public final static String STAFF = "STAF";
        public final static String USER = "USER";
        public final static String[] ALL = new String[] {"DIRE", "STAF", "USER"};
    }

    public static final Integer MAX_LENGTH_ORDER_CODE = 14;
    public static final String PATTERN_CODE_ORDER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final Integer BEGIN_CREATE = 0;
    public static final Integer TIMEOUT_CREATE = 5;
}
