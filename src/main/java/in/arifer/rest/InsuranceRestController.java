package in.arifer.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.arifer.bindings.request.SearchRequest;
import in.arifer.bindings.response.PlanResponse;
import in.arifer.reports.ExcelReportGenerator;
import in.arifer.reports.PdfReportGenerator;
import in.arifer.service.InsurancePlanService;

@RestController
public class InsuranceRestController {

	@Autowired
	private InsurancePlanService service;

	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception {
		response.setContentType("/application/pdf");
		DateFormat dateFormaterr = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormaterr.format(new Date());

		String headerKey = "Content-Disposition ";
		String headerValue = "attachment;filename=plans_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> plans = service.searchPlans(null);
		PdfReportGenerator pdfReport = new PdfReportGenerator();
		pdfReport.expoerPdf(plans, response);
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("/application/octet-stream");
		DateFormat dateFormaterr = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormaterr.format(new Date());

		String headerKey = "Content-Disposition ";
		String headerValue = "attachment;filename=plans_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<PlanResponse> plans = service.searchPlans(null);
		ExcelReportGenerator excelReport = new ExcelReportGenerator();
		excelReport.export(plans, response);
	}

	@PostMapping("/plans")
	public ResponseEntity<List<PlanResponse>> searchPlan(@RequestBody SearchRequest request) {
		List<PlanResponse> searchPlans = service.searchPlans(request);
		return new ResponseEntity<List<PlanResponse>>(searchPlans, HttpStatus.OK);
	}

	@GetMapping("/planname")
	public ResponseEntity<List<String>> getPlanResponse() {
		List<String> planNames = service.getUniquePlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}

	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getplanResponse() {
		List<String> planStatues = service.getUniquePlanStatues();
		return new ResponseEntity<>(planStatues, HttpStatus.OK);
	}

}
