package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.AppointmentIdException;
import com.exception.DoctorIdException;
import com.exception.PatientNumberNotFoundException;
import com.model.Appointment;

public interface IHospitalDao {

	Appointment getAppointmentById(int aId) throws SQLException, AppointmentIdException;

	List<Appointment> getAppointmentsForPatients(int pId) throws SQLException, PatientNumberNotFoundException;

	List<Appointment> getAppointmentsForDoctors(int dId) throws SQLException, DoctorIdException;

	boolean cancelAppointment(int aId) throws SQLException;

	
	
}
