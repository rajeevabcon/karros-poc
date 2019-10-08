package com.karros.poc.student.school.domain.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.karros.poc.student.school.domain.SchoolAuditReport;
import com.karros.poc.student.school.domain.SchoolInfo;
import com.karros.poc.student.school.port.SchoolManagementRepo;
import com.karros.poc.student.school.service.SchoolManagementService;

public class SchoolManagementServiceImpl implements SchoolManagementService{
	
	@Autowired
	private SchoolManagementRepo schoolManagementRepo;

	@Override
	public String assignSchool(SchoolInfo schoolInfo) {
		
		return schoolManagementRepo.assignSchool(schoolInfo);
	}

	@Override
	public String reassignSchool(SchoolInfo schoolInfo) {
		return schoolManagementRepo.reassignSchool(schoolInfo);
	}

	@Override
	public List<SchoolInfo> getStudentsSchoolDetails(Long studentId) {
		return schoolManagementRepo.getStudentsSchoolDetails(studentId);
	}

}
