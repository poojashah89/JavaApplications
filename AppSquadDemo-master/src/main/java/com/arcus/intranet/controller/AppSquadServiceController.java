package com.arcus.intranet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsquad.db.PatientsJDBCTemplate;
import com.appsquad.model.Patients;
import com.arcus.intranet.ArcusIntranetServiceApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

//@CrossOrigin
@RestController
@ImportResource("classpath:spring.xml")
public class AppSquadServiceController {
	
	@Autowired
	ApplicationContext context;

	private static final Logger log = LoggerFactory.getLogger(ArcusIntranetServiceApplication.class);
 
	private PatientsJDBCTemplate patientJDBCTemplate;
	private String table = "appsquad";
	
    @RequestMapping(value = "/patients",method = RequestMethod.GET)
    public String getPatients(@RequestParam(value = "patientNumber",required = false,defaultValue = "0") Integer patientid){
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	
    	try {
    		patientJDBCTemplate = (PatientsJDBCTemplate) context.getBean("patientJDBCTemplate");
    		List<Patients> instances = patientJDBCTemplate.listInstances(table);
			return ow.writeValueAsString(instances);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
    	
    }

    @RequestMapping(value = "/patients",method = RequestMethod.POST)
    public String getPatients(@RequestBody Patients patient){
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	
    	try {
    		patientJDBCTemplate = (PatientsJDBCTemplate) context.getBean("patientJDBCTemplate");	
    		patientJDBCTemplate.createInstances(table, patient);
    		return ow.writeValueAsString("Patient Record created");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error inserting ";
		}
    	
    }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username",required = false) String username, @RequestParam(value = "password",required = false) String password){
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		try {
			
			patientJDBCTemplate = (PatientsJDBCTemplate) context.getBean("patientJDBCTemplate");
			patientJDBCTemplate.createTable(table);
			
			return ow.writeValueAsString("success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		 
	}
			
	public static void handleResponse(HttpServletResponse response, String message) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(message);
		response.flushBuffer();
	}
}
