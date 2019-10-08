package com.karros.poc.student.school.domain;

import java.util.List;

public class SchoolAuditReport {
	
	private List<SchoolInfo >schoolInfo;

	/**
	 * @return the schoolInfo
	 */
	public List<SchoolInfo> getSchoolInfo() {
		return schoolInfo;
	}

	/**
	 * @param schoolInfo the schoolInfo to set
	 */
	public void setSchoolInfo(List<SchoolInfo> schoolInfo) {
		this.schoolInfo = schoolInfo;
	}
	

}
