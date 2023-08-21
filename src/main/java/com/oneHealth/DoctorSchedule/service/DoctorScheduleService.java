package com.oneHealth.DoctorSchedule.service;

import java.sql.Date;
import java.util.List;

import com.oneHealth.DoctorSchedule.entity.DoctorSchedule;
import com.oneHealth.DoctorSchedule.exception.DatabaseException;
import com.oneHealth.DoctorSchedule.exception.ScheduleNotFoundException;

/**
 * The DoctorScheduleService interface defines the contract for performing operations related
 * to DoctorSchedule. It declares methods for saving, retrieving, updating, and deleting doctor schedules.
 * 
 * Note: Make sure to import the required entities and exceptions from the appropriate packages.
 * 
 * @author Madhavi
 * @version 1.0
 */
public interface DoctorScheduleService {

    // Method to save a DoctorSchedule object in the database and handle DatabaseException if any occurs.
    DoctorSchedule saveDoctorSchedule(DoctorSchedule schedule) throws DatabaseException;

    // Method to retrieve DoctorSchedule by its ID and handle ScheduleNotFoundException if the schedule for the given doctorId is not found.
    DoctorSchedule getDoctorScheduleById(Long doctorId) throws ScheduleNotFoundException;

    // Method to retrieve a list of all DoctorSchedule objects from the database and handle DatabaseException if any occurs.
    List<DoctorSchedule> getAllDoctorsList() throws DatabaseException;

    // Method to update DoctorSchedule by its ID and handle ScheduleNotFoundException if the schedule for the given doctorId is not found.
    DoctorSchedule updateScheduleByID(long doctorId, DoctorSchedule doctorSchedule) throws ScheduleNotFoundException;

    // Method to delete DoctorSchedule by its ID and handle ScheduleNotFoundException if the schedule for the given doctorId is not found.
    DoctorSchedule deleteScheduleByID(long doctorId) throws ScheduleNotFoundException;
    
    List<DoctorSchedule> findByDoctorId(long doctorId);
    
    List<DoctorSchedule> getTodaysScheduleForDoctor(Long doctorId);
    
    List<DoctorSchedule> getUpcomingSchedules(Long doctorId);

}
