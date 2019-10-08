package com.karros.poc.student.school.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.karros.poc.student.logging.util.LoggingConstants;
import com.karros.poc.student.logging.util.LoggingUtil;
import com.karros.poc.student.school.domain.SchoolInfo;
import com.karros.poc.student.school.port.SchoolManagementRepo;
import com.karros.poc.student.school.sql.SQLFile;

public class SchoolManagementRepoImpl implements SchoolManagementRepo{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolManagementRepoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String assignSchool(SchoolInfo schoolInfo) {
		try
		{
			jdbcTemplate.update(SQLFile.ASSIGN_SCHOOL_FOR_STUDENT, new Object[] {schoolInfo.getStudentId(),
					schoolInfo.getDistictCode(), schoolInfo.getSchoolCode(),
					schoolInfo.getStartDate(), "Y'"});
		}catch(DataAccessException e)
		{
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.ASSIGN_SCHOOL,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.CLASS_Name,"SchoolManagementRepoImpl",
					LoggingConstants.METHOD_Name,"assignSchool",
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.EXCEPTION_MESSAGE,e.getMessage()));
			return LoggingConstants.FAILURE;
		}
		LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
				LoggingConstants.OPERATION_NAME, LoggingConstants.ASSIGN_SCHOOL,
				LoggingConstants.ACTION ,
				LoggingConstants.ATTRIBUTE,
				LoggingConstants.PROCESS_STATUS, LoggingConstants.SCHOOL_ASSIGNED +schoolInfo.getStudentId()));
	
		return LoggingConstants.SUCCESS;
	}

	@Override
	public String reassignSchool(SchoolInfo schoolInfo) {
		int record;
		try
		{
			// Unassign the existing school mapped. Marking Active to N
			record = jdbcTemplate.update(SQLFile.REASSIGN_SCHOOL_FOR_STUDENT, new Object[] {schoolInfo.getEndDate()!=null ? schoolInfo.getEndDate() : new Date(),
					schoolInfo.getStudentId()});
		}catch(DataAccessException e)
		{
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.CLASS_Name,"SchoolManagementRepoImpl",
					LoggingConstants.METHOD_Name,"assignSchool",
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.EXCEPTION_MESSAGE,e.getMessage()));
			return LoggingConstants.FAILURE;
		}
		//Assign a school after unassigning school
		if(record>0)
		{
			assignSchool(schoolInfo);
		}
		else 
		{
			//throw new Exception("No school assigned to the student for the studentId: "+schoolInfo.getStudentId());
		}
		LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
				LoggingConstants.OPERATION_NAME, LoggingConstants.REASSIGN_SCHOOL,
				LoggingConstants.ACTION ,
				LoggingConstants.ATTRIBUTE,
				LoggingConstants.PROCESS_STATUS, LoggingConstants.SCHOOL_ASSIGNED ));
	
		return LoggingConstants.SUCCESS;
	}

	@Override
	public List<SchoolInfo> getStudentsSchoolDetails(Long studentId) {
		
		List<SchoolInfo> schoolAuditInfo= null;
		
		try {
			schoolAuditInfo = jdbcTemplate.query(SQLFile.FETCH_STUDENTS_SCHOOL_AUDIT, new Object[] {studentId}, new RowMapper<SchoolInfo>(){  
			    @Override  
			    public SchoolInfo mapRow(ResultSet rs, int rownumber) throws SQLException {  
			    	SchoolInfo schoolInfo=new SchoolInfo();  
			        schoolInfo.setStudentId(studentId); 
			        schoolInfo.setDistictCode(rs.getString("DISTRICT_CODE"));  
			        schoolInfo.setSchoolCode(rs.getString("SCHOOL_CODE"));
			        schoolInfo.setStartDate(rs.getDate("SCHOOL_START_DATE"));
			        schoolInfo.setEndDate(rs.getDate("SCHOOL_END_DATE"));
			        return schoolInfo;  
			    }  
			    });  
		} catch(DataAccessException e)
		{
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.CLASS_Name,"SchoolManagementRepoImpl",
					LoggingConstants.METHOD_Name,"getStudentsSchoolDetails",
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.EXCEPTION_MESSAGE,e.getMessage()));
		}
		 
		return schoolAuditInfo;
	}

}
