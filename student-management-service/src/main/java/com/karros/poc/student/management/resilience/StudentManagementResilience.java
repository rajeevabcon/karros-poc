package com.karros.poc.student.management.resilience;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.karros.poc.student.management.domain.SchoolInfo;
import com.karros.poc.student.management.domain.StudentVehicleInfo;
import com.karros.poc.student.util.AuthHeaderInfo;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class StudentManagementResilience {
	
	private WebClient webClient;
	
	public StudentManagementResilience(WebClient webClient)
	{
		this.webClient = WebClient.create();
	}
	
	@CircuitBreaker(name = "assignSchool", fallbackMethod = "fallbackForAssignSchool")
    @RateLimiter(name = "assignSchool", fallbackMethod = "rateLimiterfallback")
    @Retry(name = "retryService", fallbackMethod = "retryfallback")
    @Bulkhead(name = "bulkheadService", fallbackMethod = "bulkHeadFallback")
	public String assignSchool(SchoolInfo schoolInfo, String requestUrl) {
		String status = (String)handleSchoolAndVehicle(schoolInfo, requestUrl, String.class);
		return status;
	}
	
	@CircuitBreaker(name = "assignSchool", fallbackMethod = "fallbackForAssignSchool")
	public String reassignSchool(SchoolInfo schoolInfo, String requestUrl) {
		String status = (String)handleSchoolAndVehicle(schoolInfo, requestUrl, String.class);
		return status;
	}
	
	@CircuitBreaker(name = "assignVehicle", fallbackMethod = "fallbackForAssignVehicle")
	public String assignVehicle(StudentVehicleInfo vehicleInfo, String requestUrl) {
		String status = (String)handleSchoolAndVehicle(vehicleInfo, requestUrl, String.class);
		return status;
	}
	
	
	@CircuitBreaker(name = "assignVehicle", fallbackMethod = "fallbackForAssignVehicle")
	public String reassignVehicle(StudentVehicleInfo vehicleInfo, String requestUrl) {
		String status = (String)handleSchoolAndVehicle(vehicleInfo, requestUrl, String.class);
		return status;
	}
	
	@CircuitBreaker(name = "assignVehicle", fallbackMethod = "fallbackForAssignVehicle")
	public String unassignVehicle(Long studentId, String requestUrl) {
		return webClient.post().uri(UriBuilder -> UriBuilder.path(requestUrl).
				queryParam("STUDENTID", studentId).build()).retrieve().bodyToMono(String.class).block();
	}
	
	@SuppressWarnings("unchecked")
	@CircuitBreaker(name = "fetchSchool", fallbackMethod = "fallbackForSchoolAudit")
	public List<SchoolInfo> fetchSchoolInfo(Long studentId, String requestUrl) {
		return webClient.post().uri(UriBuilder -> UriBuilder.path(requestUrl).
				queryParam("STUDENTID", studentId).build()).retrieve().bodyToMono(List.class).block();
	}
	
	@SuppressWarnings("unchecked")
	@CircuitBreaker(name = "fetchSchool", fallbackMethod = "fallbackForVehicleAudit")
	public List<StudentVehicleInfo> fetchVehicleInfo(Long studentId, Date fromDate, Date toDate,String requestUrl) {
		return webClient.post().uri(UriBuilder -> UriBuilder.path(requestUrl).
				queryParam("STUDENTID", studentId).
				queryParam("fromDate", fromDate).
				queryParam("toDate", toDate).
				build()).retrieve().bodyToMono(List.class).block();
	}
	
	public List<SchoolInfo> fallbackForSchoolAudit(Long studentId, String requestUrl)
	{
		List<SchoolInfo> schoolInfoList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -20);
		SchoolInfo schoolInfo = new SchoolInfo();
		schoolInfo.setSchoolCode("102");
		schoolInfo.setStudentId(123L);
		schoolInfo.setStartDate(cal.getTime());
		schoolInfo.setEndDate(new Date());
		schoolInfoList.add(schoolInfo);
		return schoolInfoList;
		}
	
	public List<StudentVehicleInfo> fallbackForVehicleAudit(Long studentId,Date fromDate, Date toDate, String requestUrl)
	{
		List<StudentVehicleInfo> vehicleInfoList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -20);
		StudentVehicleInfo vehicleInfo = new StudentVehicleInfo();
		vehicleInfo.setSchoolCode("102");
		vehicleInfo.setStudentId(123L);
		vehicleInfo.setStartDate(cal.getTime());
		vehicleInfoList.add(vehicleInfo);
		return vehicleInfoList;
		}
	public String fallbackForAssignSchool(SchoolInfo schoolInfo,String requestUrl)
	{
		return "Success";
	}
	
	public String fallbackForAssignVehicle(StudentVehicleInfo studentVehicleInfo, String requestUrl)
	{
		return "Success";
	}
	
	public String rateLimiterfallback(SchoolInfo schoolInfo, Throwable t) {
        return "Success";
    }
	
	 public String retryfallback(SchoolInfo schoolInfo, Throwable t) {
	        return "Inside retryfallback method";
	    }
	 
	 public String retryfallback(SchoolInfo schoolInfo, String requestType,Throwable t) {
	        return "Inside retryfallback method";
	    }
	
	public String bulkHeadFallback(SchoolInfo schoolInfo, String requestUrl)
	{
        return "Success";

	}
	
	@SuppressWarnings("unchecked")
	private Object handleSchoolAndVehicle(Object requestObject, String requestUrl, @SuppressWarnings("rawtypes") Class outputObject)
	{
		Object status = webClient.post().uri(requestUrl).
				header(AuthHeaderInfo.KARROS_AUTH_HEADER, "POC-09e4ae3e2554bf7a097d48d2ba10bcfb").
				syncBody(requestObject).retrieve().
				bodyToMono(outputObject).block();
		return status;
	}
}
