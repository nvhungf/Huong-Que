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
import com.fpoly.huongque.duantotnghiep.entity.StatisticByProduct;

public class BaoCaoTienSanPham {
	private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<StatisticByProduct> listBill;
     
    public BaoCaoTienSanPham(List<StatisticByProduct> listBill) {
        this.listBill = listBill;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() throws IOException {
        sheet = workbook.createSheet("Reports");
         
        Row row = sheet.createRow(0);
         
        
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        font.setColor(XSSFFont.COLOR_RED);
        style.setFont(font);
        
        createCell(row, 0, "HƯƠNG QUÊ: BÁO CÁO TỔNG TIỀN SẢN PHẨM BÁN ĐƯỢC", style);   
        
        row = sheet.createRow(5);
        
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        
        createCell(row, 0, "Mã sản phẩm", style);      
        createCell(row, 1, "Tên sản phẩm", style);       
        createCell(row, 2, "Số lượng", style);    
        createCell(row, 3, "Tổng tiền", style);

    
    	
    	

         
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
        String soluong = "" ;
        String total = "" ;
        
        for (StatisticByProduct bill : listBill) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             if(bill.getQuantity() == null) {
            	  soluong = "0";
             }else {
            	  soluong = bill.getQuantity().toString();
             }
             
             if(bill.getTotal() == null) {
           	  total = "0";
            }else {
            	total = bill.getTotal().toString();
            }
            createCell(row, columnCount++, bill.getId_Product().toString(), style);
            createCell(row, columnCount++, bill.getName_Product(), style);
            createCell(row, columnCount++,soluong , style);
            createCell(row, columnCount++, total, style);
  
             
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
