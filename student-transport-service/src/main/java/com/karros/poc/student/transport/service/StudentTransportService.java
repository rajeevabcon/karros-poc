package com.karros.poc.student.transport.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.karros.poc.student.transport.domain.StudentVehicleInfo;

@Service
public interface StudentTransportService {
	
    public String assignVehicle(StudentVehicleInfo studentVehicleInfo);
	
    public String reAssignVehicle(StudentVehicleInfo studentVehicleInfo);
	
    public String unAssignVehicle(StudentVehicleInfo studentVehicleInfo);
	
	public List<StudentVehicleInfo> fetchStudentVehicle( Long studentIds, Date startdate, Date enddate);
}
