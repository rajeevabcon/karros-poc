package com.karros.poc.student.transport.port;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.karros.poc.student.transport.domain.StudentVehicleInfo;


public interface StudentTransportPort {
	
	@PostMapping("/assign")
    public String assignVehicle(@RequestBody StudentVehicleInfo studentVehicleInfo);
	
	@PostMapping("/reassign")
    public String reAssignVehicle(@RequestBody StudentVehicleInfo studentVehicleInfo);
	
	@PostMapping("/unassign")
    public String unAssignVehicle(@RequestBody StudentVehicleInfo studentVehicleInfo);
	
	@GetMapping("/fetchVehicleInfo")
	public List<StudentVehicleInfo> fetchStudentVehicle(@RequestParam Long studentIds, @RequestParam("startdate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startdate,
			@RequestParam("enddate") @DateTimeFormat(pattern="yyyy-MM-dd") Date enddate);
	
}
