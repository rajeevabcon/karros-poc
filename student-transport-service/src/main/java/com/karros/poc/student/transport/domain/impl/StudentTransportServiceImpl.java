package com.karros.poc.student.transport.domain.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karros.poc.student.transport.domain.StudentVehicleInfo;
import com.karros.poc.student.transport.port.StudentTransportRepo;
import com.karros.poc.student.transport.service.StudentTransportService;

@Service
public class StudentTransportServiceImpl implements StudentTransportService {
	
	@Autowired
	private StudentTransportRepo studentTransportRepo;

	@Override
	public String assignVehicle(StudentVehicleInfo studentVehicleInfo) {
		return studentTransportRepo.assignVehicle(studentVehicleInfo);
	}

	@Override
	public String reAssignVehicle(StudentVehicleInfo studentVehicleInfo) {
		return studentTransportRepo.reAssignVehicle(studentVehicleInfo);
		
	}

	@Override
	public String unAssignVehicle(StudentVehicleInfo studentVehicleInfo) {
		return studentTransportRepo.unAssignVehicle(studentVehicleInfo);
		
	}

	@Override
	public List<StudentVehicleInfo> fetchStudentVehicle(Long studentIds, Date startdate, Date enddate) {
		return studentTransportRepo.fetchStudentVehicle(studentIds, startdate, enddate);
	}

}
