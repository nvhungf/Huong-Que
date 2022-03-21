package com.fpoly.huongque.duantotnghiep.utils;

import com.fpoly.huongque.duantotnghiep.constant.Constant;
import com.fpoly.huongque.duantotnghiep.dao.BillDao;
import com.fpoly.huongque.duantotnghiep.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class CreateUtils {

    private static BillDao billDao;

    public CreateUtils(BillDao billDao) {
        CreateUtils.billDao = billDao;
    }

    /**
     *
     * @param time: số lần tạo mã order trong 1 khoảng thời gian
     * @return order_code: String
     */
    public static String createOrderCode(Integer time) {
        if (Constant.TIMEOUT_CREATE.equals(time)) {
            throw new RuntimeException("Tạo mã order thất bại!");
        }
        String code = CreateUtils.createCode();

        Optional<Bill> bill = CreateUtils.billDao.findByOrderCode(code);
        if (bill.isPresent()) {
            return CreateUtils.createOrderCode(time++);
        }
        return code;
    }

    public static String createCode() {
        Random rnd = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Constant.MAX_LENGTH_ORDER_CODE; i++) {
            Character c = Constant.PATTERN_CODE_ORDER.charAt(rnd.nextInt(Constant.PATTERN_CODE_ORDER.length()));
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
