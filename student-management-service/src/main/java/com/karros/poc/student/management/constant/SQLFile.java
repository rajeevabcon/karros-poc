package com.karros.poc.student.management.constant;

public interface SQLFile {
	
	String CREATE_STUDENT = "insert into STUDENT_RECORD (FIRST_NAME, LAST_NAME, GRADE, ACTIVE_FLAG) values (?,?,?,?)";
	String UPDATE_STUDENT = "update STUDENT set FIRST_NAME=?, LAST_NAME=?, GRADE = ? where STUDENT_ID = ?";
	String DELETE_STUDENT = "update STUDENT set ACTIVE_FLAG = 'N' where STUDENT_ID = ?";
}
