package com.karros.poc.student.management.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import com.karros.student.validations.StudentValidationGroup.Create;
import com.karros.student.validations.StudentValidationGroup.Delete;
import com.karros.student.validations.StudentValidationGroup.Update;


public class StudentVehicleInfo {
	
	@Null(groups = Create.class)
	@NotNull(groups = {Update.class,Delete.class})
	private Long studentId;
	
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Bus Number accepts only Alphanumeric characters")
	private String busNumber;
	
	private Date startDate;
	
	private Date endDate;
	
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "schoolcode accept only alphanumeric characters")
	private String schoolCode;

	/**
	 * @return the studentId
	 */
	public Long getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the busNumber
	 */
	public String getBusNumber() {
		return busNumber;
	}

	/**
	 * @param busNumber the busNumber to set
	 */
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the schoolCode
	 */
	public String getSchoolCode() {
		return schoolCode;
	}

	/**
	 * @param schoolCode the schoolCode to set
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

}
