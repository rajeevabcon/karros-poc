package com.karros.poc.student.management.adapter;

import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.karros.poc.student.logging.util.LoggingConstants;
import com.karros.poc.student.logging.util.LoggingUtil;
import com.karros.poc.student.management.constant.SQLFile;
import com.karros.poc.student.management.domain.Student;
import com.karros.poc.student.management.ports.StudentServiceRepository;

@Repository
public class StudentServiceRepoImpl implements StudentServiceRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceRepoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long create(Student student) {
		Long studentId = 0L;
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
	            PreparedStatement ps = connection.prepareStatement(SQLFile.CREATE_STUDENT,new String[]{"STUDENT_ID"});
	            ps.setString(1, student.getFirstName());
	            ps.setString(2, student.getLastName());
	            ps.setString(3, student.getGrade());
	            ps.setString(4, "Y");
	            return ps;
	        }, keyHolder);
			studentId = (long) keyHolder.getKey();
		}catch (DataAccessException e) {
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.CLASS_Name,"StudentServiceRepoImpl",
					LoggingConstants.METHOD_Name,"Create",
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.EXCEPTION_MESSAGE,e.getMessage()));
			return studentId;
		}
		
		LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
				LoggingConstants.OPERATION_NAME, LoggingConstants.CREATE_STUDENT_INFO,
				LoggingConstants.ACTION ,
				LoggingConstants.ATTRIBUTE,
				LoggingConstants.PROCESS_STATUS, LoggingConstants.STUDENT_CREATED + studentId));
        return studentId;
	}

	@Override
	public String update(Student student) {
		try
		{
			jdbcTemplate.update(SQLFile.UPDATE_STUDENT, new Object[] {student.getFirstName(),
					student.getLastName(), student.getGrade()});
		} catch (DataAccessException e) {
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.UPDATE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.CLASS_Name,"StudentServiceRepoImpl",
					LoggingConstants.METHOD_Name,"Update",
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.EXCEPTION_MESSAGE,e.getMessage()));
			return LoggingConstants.FAILURE;
		}
		
		LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
				LoggingConstants.OPERATION_NAME, LoggingConstants.UPDATE_STUDENT_INFO,
				LoggingConstants.ACTION ,
				LoggingConstants.ATTRIBUTE,
				LoggingConstants.PROCESS_STATUS, LoggingConstants.STUDENT_UPDATED + student.getStudentId()));
		return LoggingConstants.SUCCESS;
	}

	@Override
	public String delete(long studentId) {
		try
		{
			//Doing soft delete
			jdbcTemplate.update(SQLFile.DELETE_STUDENT, new Object[] {studentId});
		} catch (DataAccessException e) {
			LOGGER.error(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
					LoggingConstants.OPERATION_NAME, LoggingConstants.DELETE_STUDENT_INFO,
					LoggingConstants.ACTION ,
					LoggingConstants.ATTRIBUTE,
					LoggingConstants.CLASS_Name,"StudentServiceRepoImpl",
					LoggingConstants.METHOD_Name,"Delete",
					LoggingConstants.PROCESS_STATUS, LoggingConstants.FAILURE,
					LoggingConstants.EXCEPTION_MESSAGE,e.getMessage()));
			return LoggingConstants.FAILURE;
		}
		
		LOGGER.info(LoggingUtil.printKeyValueLog(LoggingConstants.MODULE_NAME, LoggingConstants.COMMON_MODULE_NAME,
				LoggingConstants.OPERATION_NAME, LoggingConstants.DELETE_STUDENT_INFO,
				LoggingConstants.ACTION ,
				LoggingConstants.ATTRIBUTE,
				LoggingConstants.PROCESS_STATUS, LoggingConstants.STUDENT_DELETED + studentId));
		return LoggingConstants.SUCCESS;
	}

}
