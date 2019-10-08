package com.karros.poc.student.school.adapter;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karros.poc.student.logging.util.LoggingConstants;
import com.karros.poc.student.school.domain.SchoolInfo;
import com.karros.poc.student.school.port.SchoolManagementPort;
import com.karros.poc.student.school.service.SchoolManagementService;

@RestController
@RequestMapping(path = "/School")
public class SchoolManagementController implements SchoolManagementPort{
	
	private SchoolManagementService schoolManagementService;

	@Override
	public String assignSchool(SchoolInfo schoolInfo) {
		
		schoolManagementService.assignSchool(schoolInfo);
		return LoggingConstants.SUCCESS;
	}

	@Override
	public String reassignSchool(SchoolInfo schoolInfo) {
		schoolManagementService.reassignSchool(schoolInfo);
		return LoggingConstants.SUCCESS;
	}

	@Override
	public List<SchoolInfo> getStudentsSchoolDetails(Long studentId) {
		return schoolManagementService.getStudentsSchoolDetails(studentId);
	}

}
