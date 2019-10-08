package com.karros.poc.student.school.port;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.karros.poc.student.school.domain.SchoolInfo;

public interface SchoolManagementPort {
	
	@PostMapping("/assign")
	public String assignSchool(SchoolInfo schoolInfo);
	
	@PostMapping("/reassign")
	public String reassignSchool(SchoolInfo schoolInfo);
	
	@GetMapping("/fetchSchoolStudied")
	public List<SchoolInfo> getStudentsSchoolDetails(Long studentId);

}
