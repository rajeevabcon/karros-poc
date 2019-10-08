package com.karros.poc.student.logging.util;

/**
 * This interface has list of module names & user actions
 * 
 * @author shashidhar_banda
 *
 */
public interface LoggingConstants {
	
	String MODULE_NAME = "MODULE_NAME";
	String OPERATION_NAME ="OPERATION_NAME";
	String ACTION = "ACTION";
	String ATTRIBUTE = "ATTRIBUTE";
	String PROCESS_STATUS = "STATUS";
	String PROCESSING_TIME_SEC = "PROCESSING_TIME_SEC";	
	String MESSAGE = "MESSAGE";	

	String NULL_VALUE = "";
	String METHOD_Name = "METHOD_NAME";
	
	String CLASS_Name = "CLASS_NAME";

	String COMMON_MODULE_NAME = "STUDENT_MANAGEMENT_SERVICE";
	String SUCCESS = "SUCCESS";
	String FAILURE = "FAILURE";
	
	String STUDENT_CREATED = "Created Student Id =";
	String STUDENT_UPDATED = "Updated the Student details for Id =";
	String STUDENT_DELETED = "Deleted the Student details for Id =";

	
	String SCHOOL_ASSIGNED = "School Assigned for Student ID = ";
	String VEHICLE_ASSIGNED = "Vehicle Assigned for Student ID = ";



	
    String CREATE_STUDENT_INFO = "CREATE_STUDENT";
    String UPDATE_STUDENT_INFO = "UPDATE_STUDENT";
    String DELETE_STUDENT_INFO = "DELETE_STUDENT";
    
    String ASSIGN_SCHOOL = "ASSIGN_SCHOOL";
    String REASSIGN_SCHOOL ="REASSIGN_SCHOOL";
    
    
    String ASSIGN_VEHICLE = "ASSIGN_VEHICLE";
    String REASSIGN_VEHICLE ="REASSIGN_VEHICLE";
    String UNASSIGN_VEHICLE ="UNASSIGN_VEHICLE";


    String ASSIGN_SCHHOL_OPERATION = "ASSIGN_SCHOOL";
    String REASSIGN_SCHHOL_OPERATION = "REASSIGN_SCHOOL";
    String FETCH_SCHOOL_AUDIT = "FETCH_SCHOOL_AUDIT_REPORT";

    
    
    String EXCEPTION_MESSAGE = "Exception Message";

    
}
