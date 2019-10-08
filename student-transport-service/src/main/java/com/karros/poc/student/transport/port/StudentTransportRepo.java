package com.karros.poc.student.transport.port;

import java.util.Date;
import java.util.List;

import com.karros.poc.student.transport.domain.StudentVehicleInfo;

public interface StudentTransportRepo {
	
	public String assignVehicle(StudentVehicleInfo studentVehicleInfo);
	
    public String reAssignVehicle(StudentVehicleInfo studentVehicleInfo);
	
    public String unAssignVehicle(StudentVehicleInfo studentVehicleInfo);
	
	public List<StudentVehicleInfo> fetchStudentVehicle( Long studentIds, Date startdate, Date enddate);

}
