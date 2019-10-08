package com.karros.poc.student.management.adapter;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karros.poc.student.logging.util.LoggingConstants;
import com.karros.poc.student.logging.util.LoggingUtil;
import com.karros.poc.student.management.domain.SchoolInfo;
import com.karros.poc.student.management.domain.Student;
import com.karros.poc.student.management.domain.StudentVehicleInfo;
import com.karros.poc.student.management.ports.StudentServicePort;
import com.karros.poc.student.management.service.StudentManagementService;


@RestController
@RequestMapping(path = "/student")
public class StudentAdapterController implements StudentServicePort{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentAdapterController.class);
	
	
	@Autowired
	private StudentManagementService studentManagementService;

	@Override
	public ResponseEntity<Long> create(String authHeader, Student student) {
		long startTime = System.currentTimeMillis();
    	Long studentId = studentManagementService.create(student);

		LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
				LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
				LoggingConstants.ACTION ,
				LoggingConstants.ATTRIBUTE,
				LoggingConstants.PROCESS_STATUS, LoggingConstants.SUCCESS,
				LoggingConstants.PROCESSING_TIME_SEC, (System.currentTimeMillis()-startTime)/1000));
		return new ResponseEntity<Long>(studentId, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<String> update(String authHeader, Student student, String requestType) {
		String status = studentManagementService.update(student,requestType);
		return new ResponseEntity<String>(status, HttpStatus.OK);

	}


	@Override
	public ResponseEntity<String> delete(String authHeader, long studentId) {
		String status = studentManagementService.delete(studentId);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<SchoolInfo>> fetchSchoolInfo(String authHeader, Long studentId) {
		List<SchoolInfo> schoolInfoList = null;
		schoolInfoList =  studentManagementService.fetchSchoolInfo(studentId);
		return new ResponseEntity<List<SchoolInfo>>(schoolInfoList, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<StudentVehicleInfo>> fetchStudentVehicleReport(String authHeader, Long studentId, Date fromDate, Date toDate) {
		List<StudentVehicleInfo> studentVehicleInfoList = null;
		studentVehicleInfoList =  studentManagementService.fetchStudentsVehicleReport(studentId, fromDate, toDate);
		return new ResponseEntity<List<StudentVehicleInfo>>(studentVehicleInfoList, HttpStatus.OK);
	}



	

}
