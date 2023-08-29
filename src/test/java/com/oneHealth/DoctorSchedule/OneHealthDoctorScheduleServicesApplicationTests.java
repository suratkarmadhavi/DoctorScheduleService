package com.oneHealth.DoctorSchedule;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = OneHealthDoctorScheduleServicesApplication.class)
public class OneHealthDoctorScheduleServicesApplicationTests {

//    private static final String BASE_URL = "http://localhost:8080/api/doctors/schedule";

//    @Test
//    public void testSaveDoctorSchedule() {
//        DoctorSchedule schedule = new DoctorSchedule(/* Initialize with schedule data */);
//        
//        given()
//            .contentType(ContentType.JSON)
//            .body(schedule)
//        .when()
//            .post(BASE_URL + "/saveSchedule")
//        .then()
//            .statusCode(201)
//            .body(equalTo("Doctor Schedule Saved Successfully"));
//    }

//    @Test
//    public void testGetDoctorScheduleById() {
//        Long slotId = 1L; // Provide an existing slot ID
//        
//        when()
//            .get(BASE_URL + "/getDoctorScheduleByID/" + slotId)
//        .then()
//            .statusCode(200)
//            .body("slotId", equalTo(slotId.intValue()));
//    }

//    @Test
//    public void testGetAllDoctorSchedules() {
//        when()
//            .get(BASE_URL + "/getAllDoctors")
//        .then()
//            .statusCode(200)
//            .body("size()", greaterThan(0));
//    }

//    @Test
//    public void testUpdateDoctorSchedule() {
//        Long doctorId = 1L; // Provide an existing doctor ID
//        DoctorSchedule updatedSchedule = new DoctorSchedule(/* Updated schedule data */);
//        
//        given()
//            .contentType(ContentType.JSON)
//            .body(updatedSchedule)
//        .when()
//            .put(BASE_URL + "/updateDoctorSchedule/" + doctorId)
//        .then()
//            .statusCode(201)
//            .body(equalTo("Doctor Schedule updated successfully"));
//    }

//    @Test
//    public void testDeleteDoctorSchedule() {
//        Long doctorId = 1L; // Provide an existing doctor ID
//        
//        when()
//            .delete(BASE_URL + "/deleteDoctorSchedule/" + doctorId)
//        .then()
//            .statusCode(200)
//            .body(equalTo("Doctor Schedule deleted Successfully"));
//    }

//    @Test
//    public void testGetDoctorScheduleByDoctorId() {
//        Long doctorId = 1L; // Provide an existing doctor ID
//        
//        when()
//            .get(BASE_URL + "/getDoctorScheduleByDoctorID/" + doctorId)
//        .then()
//            .statusCode(200)
//            .body("size()", greaterThan(0));
//    }
//
//    @Test
//    public void testGetTodaysSchedule() {
//        Long doctorId = 1L; // Provide an existing
//}
}