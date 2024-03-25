package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.AppointmentIdException;
import com.exception.DoctorIdException;
import com.exception.PatientNumberNotFoundException;
import com.model.Appointment;
import com.service.HospitalService;

public class HospitalController {

	public static void main(String[] args) {
		HospitalService hospitalService=new HospitalService();
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("Press 1. Get appointment by appointment Id");
			System.out.println("Press 2. Get appointments for patient by patient Id");
			System.out.println("Press 3. Get appointments for doctor by doctor Id");
			System.out.println("Press 4. Schedule appointment");
			System.out.println("Press 5. Update appointment");
			System.out.println("Press 6. Cancel appointment");
			System.out.println("Press 0. Exit");
			int input=sc.nextInt();
			if(input==0)
			{
				System.out.println("Exiting... Thank You!");
				break;
			}
			switch(input)
			{
				case 1:
					System.out.println("Enter the appointment ID");
					int aId=sc.nextInt();
					try {
						Appointment appointment = hospitalService.getAppointmentById(aId);
						System.out.println(appointment);
						System.out.println();
					} catch (SQLException | AppointmentIdException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 2:
					System.out.println("Enter the patient Id");
					int pId=sc.nextInt();
					try {
						List<Appointment> list = hospitalService.getAppointmentsForPatients(pId);
						
						for(Appointment a:list)
						{
							System.out.println(a);
						}
						System.out.println();
					} catch (SQLException | PatientNumberNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					System.out.println("Enter the doctor Id");
					int dId=sc.nextInt();
					try {
						List<Appointment> list = hospitalService.getAppointmentsForDoctors(dId);
						for (Appointment a : list) {
							System.out.println(a);
						}
						System.out.println();
					} catch (SQLException | DoctorIdException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 6:
					System.out.println("Enter the appointment Id");
					aId=sc.nextInt();
					try {
						boolean result = hospitalService.cancelAppointment(aId);
						if(result)
						{
							System.out.println("Appointment cancelled successfully");
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("Invalid Input");
					break;
			}
		}
		sc.close();
	}

}
