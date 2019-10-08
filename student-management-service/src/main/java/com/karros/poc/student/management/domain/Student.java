package com.karros.poc.student.management.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import com.karros.poc.student.logging.util.MessageConstants;
import com.karros.student.validations.StudentValidationGroup.Create;
import com.karros.student.validations.StudentValidationGroup.Delete;
import com.karros.student.validations.StudentValidationGroup.Update;

public class Student {
	
	@Null(groups = Create.class)
	@NotNull(groups = {Update.class,Delete.class}, message = MessageConstants.STUDENT_ID_NULL)
	private Long studentId;

	@NotNull(message = "First Name cannot be null")
	@Pattern(regexp = "^[a-zA-Z0-9-\"' ]+$", message = MessageConstants.FIRST_NAME+MessageConstants.FIRST_NAME_PATTERN_MSG)
	private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z0-9-\"' ]+$", message = MessageConstants.LAST_NAME+MessageConstants.FIRST_NAME_PATTERN_MSG)
	private String lastName;
	
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Grade accepts only Alphanumeric characters")
	private String Grade;
	
	@NotNull(groups = Create.class , message = MessageConstants.SCHOOL_INFO_NULL)
	private SchoolInfo schoolInfo;
	
	@NotNull(groups = Create.class, message = MessageConstants.VEHICLE_INFO_NULL)
	private StudentVehicleInfo studentVehicleInfo;
	
	
	

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the studentId
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the grade
	 */
	public String getGrade() {
		return Grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		Grade = grade;
	}

	/**
	 * @return the schoolInfo
	 */
	public SchoolInfo getSchoolInfo() {
		return schoolInfo;
	}

	/**
	 * @param schoolInfo the schoolInfo to set
	 */
	public void setSchoolInfo(SchoolInfo schoolInfo) {
		this.schoolInfo = schoolInfo;
	}

	/**
	 * @return the studentVehicleInfo
	 */
	public StudentVehicleInfo getStudentVehicleInfo() {
		return studentVehicleInfo;
	}

	/**
	 * @param studentVehicleInfo the studentVehicleInfo to set
	 */
	public void setStudentVehicleInfo(StudentVehicleInfo studentVehicleInfo) {
		this.studentVehicleInfo = studentVehicleInfo;
	}
}
