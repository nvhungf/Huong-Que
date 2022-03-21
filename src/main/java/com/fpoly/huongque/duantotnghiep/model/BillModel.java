package com.fpoly.huongque.duantotnghiep.model;

import com.fpoly.huongque.duantotnghiep.entity.Account;
import com.fpoly.huongque.duantotnghiep.entity.BillDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillModel {

    private Integer idBill;
    private String order_Code;
    private String phoneReceiver;
    private String emailReceiver;
    private Date dateCreate = new Date();
    private Date dateSuccess = new Date();
    private String descriptionBill;
    private Double total;
    private Double transportFee;
    private Integer idVoucher;
    private Integer idShipping;
    private Integer orderStatus;
    private Integer idPolicy;
    private Account account;
    private List<BillDetail> billDetail;
}
