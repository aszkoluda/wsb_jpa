package com.jpacourse.service;

import com.jpacourse.dto.CompletedVisitsTO;
import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.dao.DoctorDao;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.dao.VisitDao;
import com.jpacourse.persistance.dao.impl.VisitDaoImpl;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.persistance.enums.TreatmentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;
@Autowired
private VisitDao visitDao;
@Autowired
private PatientDao patientDao;
@Autowired
private DoctorDao doctorDao;
    @PersistenceContext
    private EntityManager entityManager;

    public void resetVisitSequence() {
        entityManager.createNativeQuery("ALTER TABLE VISIT ALTER COLUMN ID RESTART WITH 1")
                .executeUpdate();
    }
    @Test
    public void shouldFindPatientByIdWithVisits() {
        // when
        PatientTO patient = patientService.findPatientById(1L);

        // then
        assertNotNull(patient);
        assertEquals("Tomasz", patient.getFirstName());
        assertTrue(patient.getIsInsured());
        assertEquals(1, patient.getCompletedVisits().size());

        var visit = patient.getCompletedVisits().stream().findFirst().get();
        assertEquals("Kontrola pediatryczna", visit.getDescription());
        assertEquals("Anna Kowalska", visit.getDoctorFullName());
        assertTrue( visit.getTypesOfTreatment().contains(TreatmentType.USG));
    }

    @Test
    public void shouldDeletePatientAndCascadeVisits() {
        // given
        Long patientId = 1L;
        var visits = patientService.findPatientById(patientId).getCompletedVisits();

        // when
        patientService.deletePatientById(patientId);

        // then
        Exception exceptionPatient = assertThrows(RuntimeException.class, () -> {
            patientService.findPatientById(patientId);
        });

        assertTrue(exceptionPatient.getMessage().contains("Could not find entity of id"));
        for(CompletedVisitsTO visitTo : visits){
            var deletedVisit  = visitDao.findOne(visitTo.getId());
            assertNull(deletedVisit);
            var doctor = doctorDao.findOne(visitTo.getDoctorID());
            assertNotNull(doctor);
        }
    }

    @Test
    void shouldAddVisitToPatient() {
        resetVisitSequence();
        // Given
        Long patientId = 1L;
        Long doctorId = 1L;

        var patient = patientService.findPatientById(patientId);
        var doctor = doctorDao.findOne(doctorId);
        LocalDateTime visitDate = LocalDateTime.now().plusDays(1);
        String description = "Konsultacja kontrolna";

        // When
        patientDao.AddVisit(patientId,doctorId,visitDate,description);

        // Then
        List<VisitEntity> savedVisit =doctor.getDoctorVisits();
       assertNotNull(savedVisit.stream()
               .filter(p->p.getPatient().getId().equals(patientId))
               .filter(p->p.getDoctor().getId().equals(doctorId))
               .filter(p->p.getTime().equals(visitDate))
               .filter(o->o.getDescription().equals(description))
               .findFirst().get());
    }
}
