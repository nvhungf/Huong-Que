package com.fpoly.huongque.duantotnghiep.system.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.huongque.duantotnghiep.entity.Profit;
import com.fpoly.huongque.duantotnghiep.entity.SalesReport;
import com.fpoly.huongque.duantotnghiep.entity.StatisticByProduct;
import com.fpoly.huongque.duantotnghiep.reports.BaoCaoTienSanPham;
import com.fpoly.huongque.duantotnghiep.service.ProductService;
import com.fpoly.huongque.duantotnghiep.service.ReportService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/report")
public class SystemReportController {
	@Autowired
	ReportService reportService;

	// báo cáo số lượng và số tiền bán ra của sản phẩm
	@GetMapping("/statistic-by-product")
	public ResponseEntity<List<StatisticByProduct>> getStatisticByProduct() {
		List<StatisticByProduct> statisticByProducts = reportService.getStatisticByProduct();
		return new ResponseEntity<>(statisticByProducts, HttpStatus.OK);
	}
	
	@GetMapping("/statistic-by-product/excel")
	public void getStatisticByProductExcel(HttpServletResponse response) throws IOException {
		
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new java.util.Date());		
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=statistic-by-product_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
        
		List<StatisticByProduct> statisticByProducts = reportService.getStatisticByProduct();
		BaoCaoTienSanPham baoCaoTienSanPham = new BaoCaoTienSanPham(statisticByProducts);
		
		baoCaoTienSanPham.export(response);
	}

	// báo cáo số lượng đơn hàng và tổng doanh thu
	@GetMapping("/sales-report")
	public ResponseEntity<List<SalesReport>> getSalesReport() {
		List<SalesReport> statisticByProducts = reportService.getSalesReport();
		return new ResponseEntity<>(statisticByProducts, HttpStatus.OK);
	}

	// báo cáo số lượng đơn hàng và tổng doanh thu ngày hiện tại
	@GetMapping("/sales-by-date")
	public ResponseEntity<SalesReport> getSalesReportByDate() {
		SalesReport getSalesReportByDate = reportService.getSalesReportByDate();
		return new ResponseEntity<>(getSalesReportByDate, HttpStatus.OK);
	}

	// báo cáo số lượng đơn hàng và tổng doanh thu tháng hiện tại
	@GetMapping("/sales-by-month")
	public ResponseEntity<SalesReport> getSalesReportByMonth() {
		SalesReport getSalesReportByMonth = reportService.getSalesReportByMonth();
		return new ResponseEntity<>(getSalesReportByMonth, HttpStatus.OK);
	}

	// báo cáo lợi nhuận ngày hôm nay
	@GetMapping("/profit-by-date")
	public ResponseEntity<Profit> getProfitByDate() {
		Profit profit = reportService.getProfitByDate();
		return new ResponseEntity<>(profit, HttpStatus.OK);
	}
	
	// báo cáo lợi nhuận tháng  nay
	@GetMapping("/profit-by-month")
	public ResponseEntity<Profit> getProfitByMonth() {
		Profit profit = reportService.getProfitByMonth();
		return new ResponseEntity<>(profit, HttpStatus.OK);
	}
	
	// báo cáo lợi nhuận các ngày
		@GetMapping("/profit-date")
		public ResponseEntity<List<Profit> > getProfitDate() {
			List<Profit>  profit = reportService.getProfitDate();
			return new ResponseEntity<>(profit, HttpStatus.OK);
		}

}
