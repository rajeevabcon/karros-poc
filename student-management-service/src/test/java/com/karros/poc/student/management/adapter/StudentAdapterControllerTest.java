package com.karros.poc.student.management.adapter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.karros.poc.student.management.resilience.StudentManagementResilience;
import com.karros.poc.student.management.service.StudentManagementService;

import okhttp3.mockwebserver.MockWebServer;

@RunWith(SpringRunner.class)
public class StudentAdapterControllerTest {
	
	private MockWebServer mockWebServer = new MockWebServer();
	
	@Mock
	private StudentManagementService studentManagementService;
	
	@Mock
	private StudentManagementResilience studentManagementResilience;
	
	@InjectMocks
	private StudentAdapterController studentAdapterController;
	
	@Before
	public void setup() throws Exception {
		
	}
	
	
	
	
}
