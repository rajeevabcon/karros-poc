package com.karros.poc.student.school.port;

import java.util.List;

import com.karros.poc.student.school.domain.SchoolInfo;

public interface SchoolManagementRepo {
	
	public String assignSchool(SchoolInfo schoolInfo);
	
	public String reassignSchool(SchoolInfo schoolInfo);
	
	public List<SchoolInfo> getStudentsSchoolDetails(Long studentId);

}
