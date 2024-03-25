package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.exception.AppointmentIdException;
import com.exception.DoctorIdException;
import com.exception.PatientNumberNotFoundException;
import com.model.Appointment;
import com.util.DBUtil;

public class IHospitalDaoImpl implements IHospitalDao{

	@Override
	public Appointment getAppointmentById(int aId) throws SQLException, AppointmentIdException {
		Connection conn=DBUtil.getDBConn();
		
		String sql="select * from appointment where appointment_id=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, aId);
		
		ResultSet rst=pstmt.executeQuery();
		
		if(rst.next())
		{
			int appointmentId=rst.getInt("appointment_id");
			int patientId=rst.getInt("patient_id");
			int doctorId=rst.getInt("doctor_id");
			LocalDate appointmentDate=rst.getDate("appointment_date").toLocalDate();
			String description=rst.getString("description");
			
			Appointment appointment=new Appointment(appointmentId,patientId,doctorId,appointmentDate,description);
			return appointment;
		}
		
		DBUtil.dbClose();
		throw new AppointmentIdException("Invalid appointment Id");
	}
	@Override
	public List<Appointment> getAppointmentsForPatients(int pId) throws SQLException, PatientNumberNotFoundException {
		Connection conn=DBUtil.getDBConn();
		List<Appointment> list=new ArrayList<>();
		String sql="select * from appointment where patient_id=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, pId);
		
		ResultSet rst=pstmt.executeQuery();
		while(rst.next())
		{
			int appointmentId=rst.getInt("appointment_id");
			int patientId=rst.getInt("patient_id");
			int doctorId=rst.getInt("doctor_id");
			LocalDate appointmentDate=rst.getDate("appointment_date").toLocalDate();
			String description=rst.getString("description");
			
			Appointment appointment=new Appointment(appointmentId,patientId,doctorId,appointmentDate,description);
			list.add(appointment);
		}
		DBUtil.dbClose();
		if(list.isEmpty())
		{
			throw new PatientNumberNotFoundException("Invaid patient Id");
		}
		return list;
	}
	@Override
	public List<Appointment> getAppointmentsForDoctors(int dId) throws SQLException, DoctorIdException {
		Connection conn=DBUtil.getDBConn();
		List<Appointment> list=new ArrayList<>();
		String sql="select * from appointment where patient_id=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, dId);
		
		ResultSet rst=pstmt.executeQuery();
		while(rst.next())
		{
			int appointmentId=rst.getInt("appointment_id");
			int patientId=rst.getInt("patient_id");
			int doctorId=rst.getInt("doctor_id");
			LocalDate appointmentDate=rst.getDate("appointment_date").toLocalDate();
			String description=rst.getString("description");
			
			Appointment appointment=new Appointment(appointmentId,patientId,doctorId,appointmentDate,description);
			list.add(appointment);
		}
		DBUtil.dbClose();
		if(list.isEmpty())
		{
			throw new DoctorIdException("Invaid doctor Id");
		}
		return list;

	}
	
	@Override
	public boolean scheduleAppointment(int pId, int dId, LocalDate appointmentDate, String description) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="insert into appointment(patient_id,doctor_id,appointment_date,description) values (?,?,?,?)";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, pId);
		pstmt.setInt(2, dId);
		pstmt.setObject(3, appointmentDate);
		pstmt.setString(4, description);
		
		int res=pstmt.executeUpdate();
		DBUtil.dbClose();
		if(res==1)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateAppointment(int aId, LocalDate appointmentDate) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="update appointment set appointment_date=? where appointment_id=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setObject(1,appointmentDate);
		pstmt.setInt(2, aId);
		
		int res=pstmt.executeUpdate();
		DBUtil.dbClose();
		if(res==1)
		{
			return true;
		}
		return false;
	}
	
	public boolean cancelAppointment(int aId) throws SQLException {
		Connection conn=DBUtil.getDBConn();
		String sql="delete from appointment where appointment_id=?";
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, aId);
		
		int res=pstmt.executeUpdate();
		DBUtil.dbClose();
		if(res==1)
		{
			return true;
		}
		return false;
	}
	
	
}
