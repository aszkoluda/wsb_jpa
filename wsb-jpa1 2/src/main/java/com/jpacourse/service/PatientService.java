package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistance.entity.VisitEntity;

import java.util.List;

public interface PatientService
{
    PatientTO findPatientById(final Long id);

    void deletePatientById(final Long id);

    List<VisitEntity> findVisitsByPatientId(final Long id);
}
