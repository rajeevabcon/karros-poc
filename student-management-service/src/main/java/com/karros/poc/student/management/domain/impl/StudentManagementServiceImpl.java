package com.karros.poc.student.management.domain.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.karros.poc.student.logging.util.LoggingConstants;
import com.karros.poc.student.logging.util.LoggingUtil;
import com.karros.poc.student.logging.util.MessageConstants;
import com.karros.poc.student.management.domain.SchoolInfo;
import com.karros.poc.student.management.domain.Student;
import com.karros.poc.student.management.domain.StudentVehicleInfo;
import com.karros.poc.student.management.ports.StudentServiceRepository;
import com.karros.poc.student.management.resilience.StudentManagementResilience;
import com.karros.poc.student.management.service.StudentManagementService;

@Service
public class StudentManagementServiceImpl implements StudentManagementService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentManagementServiceImpl.class);
	
	private StudentServiceRepository studentServiceRepository;
		
	@Autowired
	private StudentManagementResilience studentManagementResilience;
	
	@Value("${student.school.assign}")
	private String assignSchoolUrl;
	
	@Value("${student.school.reassign}")
	private String reassignSchoolUrl;
	
	@Value("${student.vehicle.assign}")
	private String assignVehicleUrl;
	
	@Value("${student.vehicle.reassign}")
	private String reassignVehicleUrl;
	
	@Value("${student.vehicle.unassign}")
	private String unassignVehicleUrl;
	
	@Value("${student.school.info}")
	private String schoolReportUrl;
	
	@Value("${student.vehicle.info}")
	private String vehicleReportUrl;
	
	public StudentManagementServiceImpl(StudentManagementResilience studentManagementResilience, StudentServiceRepository studentServiceRepository)
	{
		this.studentManagementResilience = studentManagementResilience;
		this.studentServiceRepository = studentServiceRepository;
	}

	@Override
	public Long create(Student student) {
		Long studentId = 0L;
		try {
			studentId = studentServiceRepository.create(student);
			LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.PROCESS_STATUS, LoggingConstants.SUCCESS,
					LoggingConstants.MESSAGE, LoggingConstants.STUDENT_CREATED));
			//If student information created successfully, then assign school
			if(studentId>0)
			{
				String status = "";
				student.getSchoolInfo().setStudentId(studentId);
				try
				{
					status = studentManagementResilience.assignSchool(student.getSchoolInfo(), assignSchoolUrl);

				}catch(Exception e)
				{
					LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
							LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
							LoggingConstants.ACTION ,
							LoggingConstants.ATTRIBUTE,
							LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
							LoggingConstants.MESSAGE, e.getMessage()));
				}
				//After assigning school, assign respective vehicle.
				if(LoggingConstants.SUCCESS.equalsIgnoreCase(status))
				{
					student.getStudentVehicleInfo().setStudentId(studentId);
					studentManagementResilience.assignVehicle(student.getStudentVehicleInfo(), assignSchoolUrl);

				}
			}

		}catch(Exception e)
		{
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.MESSAGE, e.getMessage()));
		}
		return studentId;
	}


	@Override
	public String update(Student student, String requestType) {
		switch(requestType) {
		case MessageConstants.STUDENT_CORE_UPDATE :
			LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.MESSAGE, "Update Student Core Info"));
			studentServiceRepository.update(student);
			break;
		case MessageConstants.STUDENT_SCHOOL_UPDATE :
			LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.MESSAGE, "ReAssign the Student School"));
			studentManagementResilience.reassignSchool(student.getSchoolInfo(),reassignSchoolUrl);
			break;
		case MessageConstants.STUDENT_VEHICLE_UPDATE :
			LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.MESSAGE, "ReAssign the Student's Vehicle"));
			studentManagementResilience.reassignVehicle(student.getStudentVehicleInfo(), assignSchoolUrl);
		case MessageConstants.STUDENT_UPDATE_ALL :
			studentServiceRepository.update(student);
			studentManagementResilience.reassignSchool(student.getSchoolInfo(),reassignSchoolUrl);
			studentManagementResilience.reassignVehicle(student.getStudentVehicleInfo(), assignSchoolUrl);
			break;
		}
		
		return LoggingConstants.SUCCESS;
	}

	@Override
	public String delete(long studentId) {
		studentServiceRepository.delete(studentId);
		//UnAssign the Student's vehicle which is actively assigned.
		studentManagementResilience.unassignVehicle(studentId,unassignVehicleUrl);
		return LoggingConstants.SUCCESS;
		
	}

	@Override
	public List<SchoolInfo> fetchSchoolInfo(Long studentId) {
		
		return studentManagementResilience.fetchSchoolInfo(studentId, schoolReportUrl);
	}

	@Override
	public List<StudentVehicleInfo> fetchStudentsVehicleReport(Long studentId, Date fromDate, Date toDate) {
		return studentManagementResilience.fetchVehicleInfo(studentId, fromDate, toDate, vehicleReportUrl);
	}

}
