package com.oneHealth.DoctorSchedule.serviceImplementation;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneHealth.DoctorSchedule.entity.DoctorSchedule;
import com.oneHealth.DoctorSchedule.exception.DatabaseException;
import com.oneHealth.DoctorSchedule.exception.ScheduleNotFoundException;
import com.oneHealth.DoctorSchedule.repository.DoctorScheduleRepository;
import com.oneHealth.DoctorSchedule.service.DoctorScheduleService;

/**
 * The DoctorScheduleServiceImpl class is responsible for implementing the business logic
 * for managing doctor schedules. It interacts with the DoctorScheduleRepository to perform CRUD operations.
 *
 * Note: Make sure to import the required entities, exceptions, and repositories from the appropriate packages.
 *
 * @author Madhavi
 * @version 1.0
 */
@Service
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    private final Logger logger = LoggerFactory.getLogger(DoctorScheduleServiceImpl.class);

    @Autowired
    private DoctorScheduleRepository repo;

    // Method to save a DoctorSchedule object in the database.
    @Override
    public String saveDoctorSchedule(DoctorSchedule schedule) throws DatabaseException, InstanceAlreadyExistsException {
    	List<DoctorSchedule> list = repo.findByDoctorIdAndDateAndShift(schedule.getDoctorId(), schedule.getDate(),schedule.getShift());
    	if (list.isEmpty()) {
    		logger.info("In Service - Saving Doctor Schedule: " + schedule);
            DoctorSchedule saveSchedule = repo.save(schedule);
            return "Schedule Saved Successfully !!";  
		} else {
			throw new InstanceAlreadyExistsException("Schedule Already Exists For Date : "+schedule.getDate() + "And Shift : "+ schedule.getShift());
		}
        
    }

    // Method to retrieve DoctorSchedule by its ID and handle ScheduleNotFoundException if the schedule for the given doctorId is not found.
    @Override
    public DoctorSchedule getDoctorScheduleById(Long slotId) throws ScheduleNotFoundException {
        DoctorSchedule schedule = repo.findById(slotId)
                .orElseThrow(() -> new ScheduleNotFoundException("No Schedule with this ID: " + slotId));
        logger.info("In Service - Doctor Schedule Retrieved: " + schedule);
        return schedule;
    }

    // Method to retrieve a list of all DoctorSchedule objects from the database and handle DatabaseException if any occurs.
    @Override
    public List<DoctorSchedule> getAllDoctorsList() throws DatabaseException {
        List<DoctorSchedule> scheduleList = repo.findAll();
        logger.info("In Service - All Doctor Schedules Retrieved: " + scheduleList);
        return scheduleList;
    }

    // Method to update DoctorSchedule by its ID and handle ScheduleNotFoundException if the schedule for the given slotId is not found.
    @Override
    public DoctorSchedule updateScheduleByID(long slotId, DoctorSchedule doctorSchedule) throws ScheduleNotFoundException {
        DoctorSchedule details = repo.findById(slotId)
                .orElseThrow(() -> new ScheduleNotFoundException("No Doctor Schedule Found with this ID: " + slotId));

        // Update the fields of the existing DoctorSchedule with the new values
        //details.setDoctorId(doctorSchedule.getDoctorId());
        details.setStartTime(doctorSchedule.getStartTime());
        details.setEndTime(doctorSchedule.getEndTime());
        details.setDate(doctorSchedule.getDate());
        details.setTypeAvailability(doctorSchedule.getTypeAvailability());
        details.setAddressAvailability(doctorSchedule.getAddressAvailability());
        details.setShift(doctorSchedule.getShift());

        // Save the updated DoctorSchedule to the repository
        DoctorSchedule updatedSchedule = repo.save(details);
        logger.info("In Service - Doctor Schedule Updated Successfully: " + updatedSchedule);
        return updatedSchedule;
    }

    // Method to delete DoctorSchedule by its ID and handle ScheduleNotFoundException if the schedule for the given doctorId is not found.
    @Override
    public DoctorSchedule deleteScheduleByID(long slotId) throws ScheduleNotFoundException {
        DoctorSchedule doctorSchedule = repo.findById(slotId)
                .orElseThrow(() -> new ScheduleNotFoundException("No Doctor Schedule found with this ID: " + slotId));

        repo.delete(doctorSchedule);
        logger.info("In Service - Doctor Schedule Deleted Successfully with ID: " + slotId);
        return doctorSchedule;
    }

	@Override
	public List<DoctorSchedule> findByDoctorId(long doctorId) {
		return repo.findByDoctorId(doctorId);
	}

	@Override
	public List<DoctorSchedule> getTodaysScheduleForDoctor(Long doctorId) {
		Date today =Date.valueOf(LocalDate.now());
		List<DoctorSchedule> list1 =  repo.findByDoctorIdAndDate(doctorId, today);
		List<DoctorSchedule> list2 =  repo.findByDoctorIdAndDateAfterOrderByDateAscStartTimeAsc(doctorId, today);
		List<DoctorSchedule> list3 =  new ArrayList<>();
		list3.addAll(list2);
		list3.addAll(list1);
		return list3;
	}

	@Override
	public List<DoctorSchedule> getUpcomingSchedules(Long doctorId) {
		Date currentDate = Date.valueOf(LocalDate.now());
        return repo.findByDoctorIdAndDateAfterOrderByDateAscStartTimeAsc(doctorId, currentDate);
	}
}
