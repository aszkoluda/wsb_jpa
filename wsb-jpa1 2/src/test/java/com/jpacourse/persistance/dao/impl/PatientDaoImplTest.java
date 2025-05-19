package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.jpacourse.WsbJpaApplication;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = WsbJpaApplication.class)
@Transactional
public class PatientDaoImplTest {


    @Autowired
    private PatientDao patientDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void shouldAddVisitToPatient() {
        // given
        Long patientId = 1L;
        Long doctorId = 1L;
        LocalDateTime dateTime = LocalDateTime.of(2025, 4, 15, 12, 0);
        String description = "Testowa wizyta";

        // when
        //patientDao.addVisitToPatient(patientId, doctorId, dateTime, description);

        // then
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        assertNotNull(patient);
        assertFalse(patient.getVisits().isEmpty());

        VisitEntity visit = patient.getVisits().get(patient.getVisits().size() - 1);
        assertEquals(description, visit.getDescription());
        assertEquals(dateTime, visit.getTime());
        assertEquals(doctorId, visit.getDoctor().getId());
    }

    @Test
    public void shouldReturnPaitientWithLastName(){
        String lastName = "Wiśniewski";
        var patients = patientDao.findPatientByLastName(lastName);
        assertNotNull(patients);
        for(PatientEntity pat : patients){
            assertEquals(lastName, pat.getLastName());
        }
    }

    @Test
    public void shouldReturnPatientWithMoreThenXVisit(){
        int visitCount = 1;
        var patients =patientDao.findPatientsWithMoreVisitThan(visitCount);
        assertNotNull(patients);
    }

    @Test
    public void shouldReturnPatientWithEndInsurance(){
        var patients = patientDao.findPatientsWithEndInsurenceIn(2);
        assertNotNull(patients);
    }
}
