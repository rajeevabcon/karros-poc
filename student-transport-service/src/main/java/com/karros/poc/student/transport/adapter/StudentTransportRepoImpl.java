package com.karros.poc.student.transport.adapter;

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
import com.karros.poc.student.transport.domain.StudentVehicleInfo;
import com.karros.poc.student.transport.port.StudentTransportRepo;
import com.karros.poc.student.transport.sql.SQLFile;

public class StudentTransportRepoImpl implements StudentTransportRepo{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentTransportRepoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String assignVehicle(StudentVehicleInfo studentVehicleInfo) {
		try
		{
			jdbcTemplate.update(SQLFile.ASSIGN_VEHICLE_FOR_STUDENT, new Object[] {studentVehicleInfo.getStudentId(),
					studentVehicleInfo.getSchoolCode(), studentVehicleInfo.getBusNumber(),
					studentVehicleInfo.getStartDate(), "Y"});
		}catch(DataAccessException e)
		{
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.ASSIGN_VEHICLE,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.METHOD_Name,"assignVehicle",
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.EXCEPTION_MESSAGE,e.getMessage()));
			return LoggingConstants.FAILURE;
		}
		LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
				LoggingConstants.OPERATION_NAME, LoggingConstants.ASSIGN_VEHICLE,
				LoggingConstants.ACTION ,
				LoggingConstants.ATTRIBUTE,
				LoggingConstants.PROCESS_STATUS, LoggingConstants.VEHICLE_ASSIGNED ));
	
		return LoggingConstants.SUCCESS;
	}

	@Override
	public String reAssignVehicle(StudentVehicleInfo studentVehicleInfo) {
		int record;
		try
		{
			// Unassign the existing vehicle mapped. Marking Active to N
			record = jdbcTemplate.update(SQLFile.REASSIGN_VEHICLE_FOR_STUDENT, new Object[] {studentVehicleInfo.getEndDate()!=null ? studentVehicleInfo.getEndDate() : new Date(),
					studentVehicleInfo.getStudentId()});
		}catch(DataAccessException e)
		{
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.REASSIGN_VEHICLE,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.METHOD_Name,"reAssignVehicle",
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.EXCEPTION_MESSAGE,e.getMessage()));
			return LoggingConstants.FAILURE;
		}
		//Assign a new Vehicle after unassigning the vehicle
		if(record>0)
		{
			assignVehicle(studentVehicleInfo);
		}
		else 
		{
			//throw new Exception("No school assigned to the student for the studentId: "+schoolInfo.getStudentId());
		}
		LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
				LoggingConstants.OPERATION_NAME, LoggingConstants.REASSIGN_VEHICLE,
				LoggingConstants.ACTION ,
				LoggingConstants.ATTRIBUTE,
				LoggingConstants.PROCESS_STATUS, LoggingConstants.VEHICLE_ASSIGNED ));
	
		return LoggingConstants.SUCCESS;
		
	}

	@Override
	public String unAssignVehicle(StudentVehicleInfo studentVehicleInfo) {
		try
		{
			// Unassign the existing vehicle mapped. Marking Active to N
			jdbcTemplate.update(SQLFile.REASSIGN_VEHICLE_FOR_STUDENT, new Object[] {studentVehicleInfo.getEndDate()!=null ? studentVehicleInfo.getEndDate() : new Date(),
					studentVehicleInfo.getStudentId()});
		}catch(DataAccessException e)
		{
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.UNASSIGN_VEHICLE,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.METHOD_Name,"reAssignVehicle",
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.EXCEPTION_MESSAGE,e.getMessage()));
			return LoggingConstants.FAILURE;
		}
		
		return LoggingConstants.SUCCESS;
	}

	@Override
	public List<StudentVehicleInfo> fetchStudentVehicle(Long studentIds, Date startdate, Date enddate) {
			List<StudentVehicleInfo> vehicleAuditInfo= null;
		
		try {
			vehicleAuditInfo = jdbcTemplate.query(SQLFile.FETCH_STUDENTS_VEHICLE_AUDIT,new Object[] {studentIds, startdate, enddate}, new RowMapper<StudentVehicleInfo>(){  
			    @Override  
			    public StudentVehicleInfo mapRow(ResultSet rs, int rownumber) throws SQLException {  
			    	StudentVehicleInfo vehicleInfo=new StudentVehicleInfo();  
			        vehicleInfo.setStudentId(studentIds); 
			        vehicleInfo.setSchoolCode(rs.getString("SCHOOL_CODE"));
			        vehicleInfo.setBusNumber(rs.getString("BUS_NUMBER"));
			        vehicleInfo.setStartDate(rs.getDate("BUS_START_DATE"));
			        vehicleInfo.setEndDate(rs.getDate("BUS_END_DATE"));
			        return vehicleInfo;  
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
		 
		return vehicleAuditInfo;
	}

}
