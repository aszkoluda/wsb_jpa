package com.jpacourse.persistance.dao;

import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    void AddVisit(Long patientId, Long doctorId, LocalDateTime date, String description);
    List<PatientEntity> findPatientByLastName(String lastName);
    List<VisitEntity> findVisitByPatientId(Long patientId);
    List<PatientEntity> findPatientsWithMoreVisitThan(int visitCount);
    List<PatientEntity> findPatientsWithEndInsurenceIn(int monthToEndInsurence);
}
