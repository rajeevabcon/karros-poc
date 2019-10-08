package com.karros.poc.student.transport.domain;

import java.util.List;

public class StudentVehicleAuditReport {
	
	private List<StudentVehicleInfo> studentVehicleReport;

	/**
	 * @return the studentVehicleReport
	 */
	public List<StudentVehicleInfo> getStudentVehicleReport() {
		return studentVehicleReport;
	}

	/**
	 * @param studentVehicleReport the studentVehicleReport to set
	 */
	public void setStudentVehicleReport(List<StudentVehicleInfo> studentVehicleReport) {
		this.studentVehicleReport = studentVehicleReport;
	}

}
