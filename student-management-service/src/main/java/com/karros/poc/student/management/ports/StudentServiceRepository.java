package com.karros.poc.student.management.ports;

import org.springframework.stereotype.Repository;

import com.karros.poc.student.management.domain.Student;

@Repository
public interface StudentServiceRepository {
	
	public Long create(Student student);
	
    public String update(Student student);
	
    public String delete(long studentId);

}
