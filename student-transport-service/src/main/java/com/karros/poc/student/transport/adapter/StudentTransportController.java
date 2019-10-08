package com.karros.poc.student.transport.adapter;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karros.poc.student.logging.util.LoggingConstants;
import com.karros.poc.student.transport.domain.StudentVehicleAuditReport;
import com.karros.poc.student.transport.domain.StudentVehicleInfo;
import com.karros.poc.student.transport.port.StudentTransportPort;
import com.karros.poc.student.transport.service.StudentTransportService;

@RestController
@RequestMapping(path = "/vehicle")
public class StudentTransportController implements StudentTransportPort{
	
	@Autowired
	private StudentTransportService studentTransportService;

	@Override
	public String assignVehicle(StudentVehicleInfo studentVehicleInfo) {
		return studentTransportService.assignVehicle(studentVehicleInfo);
		 
		
	}

	@Override
	public String reAssignVehicle(StudentVehicleInfo studentVehicleInfo) {
		return studentTransportService.reAssignVehicle(studentVehicleInfo);
	}

	@Override
	public String unAssignVehicle(StudentVehicleInfo studentVehicleInfo) {
		return studentTransportService.unAssignVehicle(studentVehicleInfo);
	}

	@Override
	public List<StudentVehicleInfo> fetchStudentVehicle(Long studentIds, Date startdate, Date enddate) {
		
		return studentTransportService.fetchStudentVehicle(studentIds, startdate, enddate);
	}

	
	

}
