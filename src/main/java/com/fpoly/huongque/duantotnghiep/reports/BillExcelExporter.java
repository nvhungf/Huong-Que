package com.fpoly.huongque.duantotnghiep.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fpoly.huongque.duantotnghiep.entity.Bill;

public class BillExcelExporter {
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Bill> listBill;
     
    public BillExcelExporter(List<Bill> listBill) {
        this.listBill = listBill;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() throws IOException {
        sheet = workbook.createSheet("Bills");
         
        Row row = sheet.createRow(0);
         
        
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        font.setColor(XSSFFont.COLOR_RED);
        style.setFont(font);
        
        createCell(row, 0, "HƯƠNG QUÊ: BÁO CÁO ĐƠN HÀNG", style);   
        
        row = sheet.createRow(5);
        
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        
        createCell(row, 0, "Mã đơn hàng", style);      
        createCell(row, 1, "Mã khách hàng", style);       
        createCell(row, 2, "Số điện thoại KH", style);    
        createCell(row, 3, "Email", style);
        createCell(row, 4, "Địa chỉ", style);
        createCell(row, 5, "Nhân viên bán hàng", style);
        createCell(row, 6, "Trị giá đơn hàng", style);
        createCell(row, 7, "Mã giảm giá", style);
        createCell(row, 8, "Mã giao hàng", style);
        createCell(row, 9, "Trạng thái", style);
        createCell(row, 10, "Ngày tạo đơn", style);
        createCell(row, 11, "Ngày hoàn thành", style);
    
    	
    	

         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 6;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Bill bill : listBill) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, bill.getOrder_Code(), style);
            createCell(row, columnCount++, bill.getAccount().getId_Account(), style);
            createCell(row, columnCount++, bill.getAccount().getPhone_Number().toString(), style);
            createCell(row, columnCount++, bill.getAccount().getEmail(), style);
            createCell(row, columnCount++, bill.getAccount().getAddress(), style);
            createCell(row, columnCount++, bill.getDescriptionBill(), style);
            createCell(row, columnCount++, bill.getTotal().toString() , style);
            createCell(row, columnCount++, bill.getIdPolicy(), style);
            createCell(row, columnCount++, bill.getIdShipping(), style);
            createCell(row, columnCount++, bill.getOrderStatus(), style);
            createCell(row, columnCount++, bill.getDateCreate().toString(), style);
            createCell(row, columnCount++, bill.getDateSuccess().toString(), style);
             
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
}
