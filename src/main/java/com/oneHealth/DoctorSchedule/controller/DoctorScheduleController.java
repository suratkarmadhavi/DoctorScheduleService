package com.oneHealth.DoctorSchedule.controller;

import java.sql.Date;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.oneHealth.DoctorSchedule.entity.DoctorSchedule;
import com.oneHealth.DoctorSchedule.exception.DatabaseException;
import com.oneHealth.DoctorSchedule.exception.ScheduleNotFoundException;
import com.oneHealth.DoctorSchedule.service.DoctorScheduleService;

/**
 * Controller class for handling Doctor Schedule related HTTP requests.
 *
 * This class defines methods for saving, retrieving, updating, and deleting doctor schedules.
 * It uses a service class, DoctorScheduleService, to perform the actual business logic.
 * The controller maps HTTP endpoints to these methods, allowing clients to interact with the application.
 * Additionally, the class includes logging statements to log important events for monitoring and debugging.
 *
 * Note: Make sure to import the required entities, exceptions, and services from the appropriate packages.
 *
 * @author Madhavi
 * @version 1.0
 */

//@CrossOrigin("*")
@RestController
@RequestMapping("/api/doctors/schedule")
public class DoctorScheduleController {

    // Logger for logging important events
    private final Logger logger = LoggerFactory.getLogger(DoctorScheduleController.class);

    @Autowired
    private DoctorScheduleService service;

    // Endpoint to save the doctor's schedule information into the database.
    /**
     * Saves a doctor schedule by accepting a JSON representation of the schedule.
     *
     * @param schedule The JSON representation of the doctor schedule to be saved.
     * @return ResponseEntity<String> A ResponseEntity containing a message indicating the result of the save operation.
     * @throws DatabaseException If there is an issue with the database during the save operation.
     */
    @PostMapping("/saveSchedule")
    public ResponseEntity<String> saveDoctorSchedule(@RequestBody DoctorSchedule schedule) throws DatabaseException {
        String s;
		try {
				s = service.saveDoctorSchedule(schedule);
				logger.info("In Controller - Doctor Schedule Saved Successfully: " + schedule);
				return new ResponseEntity<>(s, HttpStatus.CREATED);
			} catch (InstanceAlreadyExistsException e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
        
    }

    
    
    
    // Endpoint to retrieve the doctor's schedule information by doctorId from the database.
    /**
     * Retrieves a doctor schedule by its ID.
     *
     * @param slotId The ID of the doctor schedule to be retrieved.
     * @return ResponseEntity<DoctorSchedule> A ResponseEntity containing the retrieved doctor schedule.
 
     */
    @GetMapping("/getDoctorScheduleByID/{slotId}")
    public ResponseEntity<?> getDoctorSchduleByID(@PathVariable(value = "slotId") Long slotId) {
        try {
            DoctorSchedule obj = service.getDoctorScheduleById(slotId);
            logger.info("In Controller - Doctor Schedule Retrieved: " + obj);
            return ResponseEntity.ok().body(obj);
        } catch (ScheduleNotFoundException ex) {
            // Handle ScheduleNotFoundException here
            logger.error("Schedule not found: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            // Handle other exceptions here
            logger.error("An error occurred: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    
    
    // Endpoint to retrieve the list of all doctors' schedules from the database.
    /**
     * Retrieves a list of all doctor schedules.
     *
     * @return ResponseEntity<List<DoctorSchedule>> A ResponseEntity containing the list of all doctor schedules.
    
     */
    @GetMapping("/getAllDoctors")
    public ResponseEntity<List<DoctorSchedule>> getAllDoctorSchedule() {
        try {
            List<DoctorSchedule> doctorScheduleList = service.getAllDoctorsList();
            logger.info("In Controller - All Doctor Schedules Retrieved: " + doctorScheduleList);
            return new ResponseEntity<>(doctorScheduleList, HttpStatus.OK);
        } catch (DatabaseException ex) {
            // Handle DatabaseException here
            logger.error("Database error: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception ex) {
            // Handle other exceptions here
            logger.error("An error occurred: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    
    
    
    // Endpoint to update the doctor's schedule information by doctorId in the database.
    /**
     * Updates a doctor schedule by its ID with the provided updated schedule data.
     *
     * @param slotId          The ID of the doctor schedule to be updated.
     * @param doctorSchedule  The updated doctor schedule data.
     * @return ResponseEntity<String> A ResponseEntity containing a message indicating the result of the update operation.
    
     */
    @PutMapping("/updateDoctorSchedule/{slotId}")
    public ResponseEntity<String> updateDoctorSchedule(@PathVariable(value = "slotId") long slotId, @RequestBody DoctorSchedule doctorSchedule) {
        try {
            service.updateScheduleByID(slotId, doctorSchedule);
            logger.info("In Controller - Doctor Schedule Updated Successfully: " + doctorSchedule);
            return new ResponseEntity<>("Doctor Schedule updated successfully", HttpStatus.CREATED);
        } catch (ScheduleNotFoundException ex) {
            // Handle ScheduleNotFoundException here
            logger.error("Schedule not found: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Schedule not found with ID "+slotId);
        } catch (Exception ex) {
            // Handle other exceptions here
            logger.error("An error occurred: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }


    
    
    
    
    
    // Endpoint to delete the doctor's schedule information by doctorId from the database.
    /**
     * Deletes a doctor schedule by its ID.
     *
     * @param slotId The ID of the doctor schedule to be deleted.
     * @return ResponseEntity<String> A ResponseEntity containing a message indicating the result of the delete operation.
    
     */
    @DeleteMapping("/deleteDoctorSchedule/{slotId}")
    public ResponseEntity<String> deleteScheduleByID(@PathVariable(value = "slotId") long slotId) {
        try {
            service.deleteScheduleByID(slotId);
            logger.info("In Controller - Doctor Schedule Deleted Successfully with ID: " + slotId);
            return new ResponseEntity<>("Doctor Schedule deleted successfully", HttpStatus.OK);
        } catch (ScheduleNotFoundException ex) {
            // Handle ScheduleNotFoundException here
            logger.error("Schedule not found: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Schedule not found");
        } catch (Exception ex) {
            // Handle other exceptions here
            logger.error("An error occurred: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    
    
    
    
    
    /**
     * Retrieves a list of doctor schedules for a specific doctor by their ID.
     *
     * @param doctorId The ID of the doctor for whom doctor schedules are retrieved.
     * @return ResponseEntity<List<DoctorSchedule>> A ResponseEntity containing the list of doctor schedules for the specified doctor.
     */
    @GetMapping("/getDoctorScheduleByDoctorID/{doctorId}")
    public ResponseEntity<List<DoctorSchedule>> getDoctorSchduleByDoctorID(@PathVariable(value = "doctorId") Long doctorId) {
        try {
            List<DoctorSchedule> obj = service.findByDoctorId(doctorId);
            logger.info("In Controller - Doctor Schedule Retrieved: " + obj);
            return ResponseEntity.ok().body(obj);
        } catch (Exception ex) {
            // Handle exceptions here
            logger.error("An error occurred: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    
    
    
    
    /**
     * Retrieves today's doctor schedule and upcoming schedules for a specific doctor by their ID.
     *
     * @param doctorId The ID of the doctor for whom schedules are retrieved.
     * @return List<DoctorSchedule> A list of today's and upcoming doctor schedules for the specified doctor.
     */
    @GetMapping("/todayandupcoming/{doctorId}")
    public ResponseEntity<List<DoctorSchedule>> getTodaysSchedule(@PathVariable Long doctorId) {
        try {
            List<DoctorSchedule> schedules = service.getTodaysScheduleForDoctor(doctorId);
            return ResponseEntity.ok(schedules);
        } catch (Exception ex) {
            // Handle exceptions here
            logger.error("An error occurred: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
