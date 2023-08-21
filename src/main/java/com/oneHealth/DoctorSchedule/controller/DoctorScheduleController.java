package com.oneHealth.DoctorSchedule.controller;

import java.sql.Date;
import java.util.List;

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
    @PostMapping("/saveSchedule")
    public ResponseEntity<String> saveDoctorSchedule(@RequestBody DoctorSchedule schedule) throws DatabaseException {
        service.saveDoctorSchedule(schedule);
        logger.info("In Controller - Doctor Schedule Saved Successfully: " + schedule);
        return new ResponseEntity<>("Doctor Schedule Saved Successfully", HttpStatus.CREATED);
    }

    // Endpoint to retrieve the doctor's schedule information by doctorId from the database.
    @GetMapping("/getDoctorScheduleByID/{slotId}")
    public ResponseEntity<DoctorSchedule> getDoctorSchduleByID(@PathVariable(value = "slotId") Long slotId) throws ScheduleNotFoundException {
        DoctorSchedule obj = service.getDoctorScheduleById(slotId);
        logger.info("In Controller - Doctor Schedule Retrieved: " + obj);
        return ResponseEntity.ok().body(obj);
    }

    // Endpoint to retrieve the list of all doctors' schedules from the database.
    @GetMapping("/getAllDoctors")
    public ResponseEntity<List<DoctorSchedule>> getAllDoctorSchedule() throws DatabaseException {
        List<DoctorSchedule> doctorScheduleList = service.getAllDoctorsList();
        logger.info("In Controller - All Doctor Schedules Retrieved: " + doctorScheduleList);
        return new ResponseEntity<>(doctorScheduleList, HttpStatus.OK);
    }

    // Endpoint to update the doctor's schedule information by doctorId in the database.
    @PutMapping("/updateDoctorSchedule/{doctorId}")
    public ResponseEntity<String> updateDoctorSchedule(@PathVariable(value = "doctorId") long doctorId, @RequestBody DoctorSchedule doctorSchedule) throws ScheduleNotFoundException {
        service.updateScheduleByID(doctorId, doctorSchedule);
        logger.info("In Controller - Doctor Schedule Updated Successfully: " + doctorSchedule);
        return new ResponseEntity<>("Doctor Schedule updated successfully", HttpStatus.CREATED);
    }

    // Endpoint to delete the doctor's schedule information by doctorId from the database.
    @DeleteMapping("/deleteDoctorSchedule/{doctorId}")
    public ResponseEntity<String> deleteScheduleByID(@PathVariable(value = "doctorId") long doctorId) throws ScheduleNotFoundException {
        service.deleteScheduleByID(doctorId);
        logger.info("In Controller - Doctor Schedule Deleted Successfully with ID: " + doctorId);
        return new ResponseEntity<>("Doctor Schedule deleted Successfully", HttpStatus.OK);
    }
    
    @GetMapping("/getDoctorScheduleByDoctorID/{doctorId}")
    public ResponseEntity<List<DoctorSchedule>> getDoctorSchduleByDoctorID(@PathVariable(value = "doctorId") Long doctorId){
        List<DoctorSchedule> obj = service.findByDoctorId(doctorId);
        logger.info("In Controller - Doctor Schedule Retrieved: " + obj);
        return ResponseEntity.ok().body(obj);
    }
    
    
    @GetMapping("/todayandupcoming/{doctorId}")
    public List<DoctorSchedule> getTodaysSchedule(@PathVariable Long doctorId) {
        
        // Call the service method to retrieve today's schedule for the doctor
        return service.getTodaysScheduleForDoctor(doctorId);
    }
}
