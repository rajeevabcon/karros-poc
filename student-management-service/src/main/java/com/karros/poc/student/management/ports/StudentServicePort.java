package com.karros.poc.student.management.ports;



import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.karros.poc.student.management.domain.SchoolInfo;
import com.karros.poc.student.management.domain.Student;
import com.karros.poc.student.management.domain.StudentVehicleInfo;
import com.karros.poc.student.util.AuthHeaderInfo;
import com.karros.student.validations.StudentValidationGroup.Create;
import com.karros.student.validations.StudentValidationGroup.Update;


public interface StudentServicePort {
	
	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('PARENT_USER')")
    public ResponseEntity<Long> create(@RequestHeader(name = AuthHeaderInfo.KARROS_AUTH_HEADER, required = true) String authHeader,
    		@Validated(Create.class) @RequestBody Student student);
	
	@PostMapping(path = "/update/{requestType}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<String> update(@RequestHeader(name = AuthHeaderInfo.KARROS_AUTH_HEADER, required = true) String authHeader,
    		@Validated(Update.class) @RequestBody Student student,@PathVariable("requestType") final String requestType);
	
	@PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('PARENT_USER')")
    public ResponseEntity<String> delete(@RequestHeader(name = AuthHeaderInfo.KARROS_AUTH_HEADER, required = true) String authHeader, 
    		@PathVariable("studentId") long studentId);
	
	@GetMapping(path = "/fetchSchoolReport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
	public ResponseEntity<List<SchoolInfo>> fetchSchoolInfo(@RequestHeader(name = AuthHeaderInfo.KARROS_AUTH_HEADER, required = true) String authHeader,
			@PathVariable("studentId") Long studentId);
	
	@GetMapping(path = "/fetchVehicleReport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
	public ResponseEntity<List<StudentVehicleInfo>> fetchStudentVehicleReport(@RequestHeader(name = AuthHeaderInfo.KARROS_AUTH_HEADER, required = true) String authHeader,
			@PathVariable("studentId") Long studentId, @PathVariable("fromDate") Date fromDate, @PathVariable("toDate") Date toDate);

}
