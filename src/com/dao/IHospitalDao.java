package com.dao;

import java.sql.SQLException;
import java.time.LocalDate;
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

	boolean scheduleAppointment(int pId, int dId, LocalDate appointmentDate, String description) throws SQLException;

	boolean updateAppointment(int aId, LocalDate appointmentDate) throws SQLException;

	
	
}
