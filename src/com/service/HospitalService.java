package com.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.dao.IHospitalDao;
import com.dao.IHospitalDaoImpl;
import com.exception.AppointmentIdException;
import com.exception.DoctorIdException;
import com.exception.PatientNumberNotFoundException;
import com.model.Appointment;

public class HospitalService {

	IHospitalDao iHospitalDao=new IHospitalDaoImpl(); // polymorphic object
	public Appointment getAppointmentById(int aId) throws SQLException, AppointmentIdException {
		return iHospitalDao.getAppointmentById(aId);
	}
	public List<Appointment> getAppointmentsForPatients(int pId) throws SQLException, PatientNumberNotFoundException {
		
		return iHospitalDao.getAppointmentsForPatients(pId);
	}
	public List<Appointment> getAppointmentsForDoctors(int dId) throws SQLException, DoctorIdException {
		return iHospitalDao.getAppointmentsForDoctors(dId);
	}
	public boolean cancelAppointment(int aId) throws SQLException {
		return iHospitalDao.cancelAppointment(aId);
	}
	public boolean scheduleAppointment(int pId, int dId, LocalDate appointmentDate, String description) throws SQLException {
		return iHospitalDao.scheduleAppointment(pId,dId,appointmentDate,description);
	}
	public boolean updateAppointment(int aId, LocalDate appointmentDate) throws SQLException {
		return iHospitalDao.updateAppointment(aId,appointmentDate);
	}

}
