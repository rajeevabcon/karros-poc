package com.karros.student.validations;

import javax.validation.groups.Default;

public class StudentValidationGroup {
	
	private StudentValidationGroup()
	{
		
	}
	public interface Create extends Default{}
	public interface Update extends Default{}
	public interface Delete extends Default{}

}
