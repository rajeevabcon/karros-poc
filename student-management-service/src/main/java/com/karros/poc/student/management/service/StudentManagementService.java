package com.karros.poc.student.management.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.karros.poc.student.management.domain.SchoolInfo;
import com.karros.poc.student.management.domain.Student;
import com.karros.poc.student.management.domain.StudentVehicleInfo;

@Service
public interface StudentManagementService {
	
		public Long create(Student student);
		
	    public String update(Student student, String requestType);
		
	    public String delete(long studentId);
	    
	    public List<SchoolInfo> fetchSchoolInfo(Long studentId);
		
		public List<StudentVehicleInfo> fetchStudentsVehicleReport(Long studentId, Date fromDate, Date toDate);


}
