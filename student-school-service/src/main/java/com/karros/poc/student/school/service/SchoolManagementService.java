package com.karros.poc.student.school.service;

import java.util.List;

import com.karros.poc.student.school.domain.SchoolInfo;

public interface SchoolManagementService {
	
	public String assignSchool(SchoolInfo schoolInfo);
	
	public String reassignSchool(SchoolInfo schoolInfo);
	
	public List<SchoolInfo> getStudentsSchoolDetails(Long studentId);
	

}
