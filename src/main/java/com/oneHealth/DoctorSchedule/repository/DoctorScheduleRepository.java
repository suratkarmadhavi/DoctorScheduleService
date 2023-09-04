package com.oneHealth.DoctorSchedule.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.oneHealth.DoctorSchedule.entity.DoctorSchedule;

/**
 * The DoctorScheduleRepository interface extends JpaRepository to perform CRUD operations on DoctorSchedule entity.
 * 
 * This repository provides the necessary methods to interact with the database and manage doctor schedule data.
 * It inherits basic CRUD operations from JpaRepository and supports additional custom queries if needed.
 
 * @author Madhavi
 * @version 1.0
 */
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long>{
	/**
	 * Finds a list of doctor schedules for a specific doctor by their ID.
	 *
	 * @param doctorId The ID of the doctor for whom doctor schedules are retrieved.
	 * @return List<DoctorSchedule> A list of doctor schedules for the specified doctor.
	 */
	List<DoctorSchedule> findByDoctorId(long doctorId);

	/**
	 * Finds a list of doctor schedules for a specific doctor by their ID and date.
	 *
	 * @param doctorId The ID of the doctor for whom doctor schedules are retrieved.
	 * @param date     The date for which doctor schedules are retrieved.
	 * @return List<DoctorSchedule> A list of doctor schedules for the specified doctor and date.
	 */
	List<DoctorSchedule> findByDoctorIdAndDate(Long doctorId, Date date);

	/**
	 * Finds a list of doctor schedules for a specific doctor by their ID, date after a certain date, and orders them by date and start time in ascending order.
	 *
	 * @param doctorId The ID of the doctor for whom doctor schedules are retrieved.
	 * @param date     The date after which doctor schedules are retrieved.
	 * @return List<DoctorSchedule> A list of doctor schedules for the specified doctor, date, and shift.
	 */
	List<DoctorSchedule> findByDoctorIdAndDateAfterOrderByDateAscStartTimeAsc(Long doctorId, Date date);

	/**
	 * Finds a list of doctor schedules for a specific doctor by their ID, date, and shift.
	 *
	 * @param doctorId The ID of the doctor for whom doctor schedules are retrieved.
	 * @param date     The date for which doctor schedules are retrieved.
	 * @param shift    The shift for which doctor schedules are retrieved.
	 * @return List<DoctorSchedule> A list of doctor schedules for the specified doctor, date, and shift.
	 */
	List<DoctorSchedule> findByDoctorIdAndDateAndShift(Long doctorId, Date date, String shift);

}
