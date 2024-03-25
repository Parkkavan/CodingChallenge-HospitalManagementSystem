package com.test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.exception.AppointmentIdException;
import com.exception.DoctorIdException;
import com.exception.PatientNumberNotFoundException;
import com.model.Appointment;
import com.service.HospitalService;

public class HospitalServiceTest {

	HospitalService hospitalService=new HospitalService();
	
	@Test
	public void getAppointmentById()
	{	
		/* Use case 1 : valid Id*/
		
		int id=7;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate appointmentDate=LocalDate.parse("2024-03-27", formatter);
        
        Appointment expected=new Appointment(7,5,5,appointmentDate,"Severe Headache");
		
		try {
			Assert.assertEquals(expected, hospitalService.getAppointmentById(id));
		} catch (SQLException | AppointmentIdException e) {}
		
		
		/* Use case 2 : Invalid Id*/
		id=35;
		try {
			Assert.assertEquals(expected, hospitalService.getAppointmentById(id));
		} catch (SQLException | AppointmentIdException e) {
			Assert.assertEquals("Invalid appointment Id".toLowerCase(), e.getMessage().toLowerCase());
		}
	}
	
	@Test
	public void getAppointmentsForPatientsTest()
	{
		/* Use case 1 : valid Id*/
		List<Appointment> list=new ArrayList<>();
		int id=3;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate appointmentDate=LocalDate.parse("2024-03-23", formatter);
        
        Appointment expected=new Appointment(3,3,3,appointmentDate,"High BP");
        
        list.add(expected);
        
        try {
			Assert.assertEquals(list, hospitalService.getAppointmentsForPatients(id));
		} catch (SQLException | PatientNumberNotFoundException e) {}
        
        /* Use case 2 : Invalid Id*/
        id=34;
        try {
			Assert.assertEquals(list, hospitalService.getAppointmentsForPatients(id));
		} catch (SQLException | PatientNumberNotFoundException e) {
			Assert.assertEquals("Invaid patient Id".toLowerCase(), e.getMessage().toLowerCase());
		}
	}
	
	@Test
	public void getAppointmentsForDoctors()
	{
		/* Use case 1 : valid Id*/
		List<Appointment> list=new ArrayList<>();
		int id=2;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate appointmentDate=LocalDate.parse("2024-03-22", formatter);
        
        Appointment expected=new Appointment(2,2,2,appointmentDate,"Weak immunity");
        list.add(expected);
        try {
			Assert.assertEquals(list,hospitalService.getAppointmentsForDoctors(id));
		} catch (SQLException | DoctorIdException e) { }
        
        /* Use case 2 : Invalid Id*/
        id=23;
        try {
			Assert.assertEquals(list,hospitalService.getAppointmentsForDoctors(id));
		} catch (SQLException | DoctorIdException e) {
			Assert.assertEquals("Invaid doctor Id".toLowerCase(), e.getMessage().toLowerCase());
		}
	}
}
