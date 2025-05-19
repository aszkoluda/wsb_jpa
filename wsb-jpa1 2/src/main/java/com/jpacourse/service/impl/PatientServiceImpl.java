package com.jpacourse.service.impl;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import com.jpacourse.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService
{
    private final PatientDao patientDao;

    @Autowired
    public PatientServiceImpl(PatientDao pPatientDao)
    {
        patientDao = pPatientDao;
    }

    @Override
    public PatientTO findPatientById(Long id){
        final PatientEntity entity = patientDao.findOne(id);
        if(entity == null){
            throw new EntityNotFoundException(id);
        }
        return PatientMapper.mapToTO(entity);
    }

    @Override
    public void deletePatientById(Long id){
        var patient = patientDao.findOne(id);
        if(patient == null)
            throw new EntityNotFoundException(id);
        patientDao.delete(id);
    }

    @Override
    public List<VisitEntity> findVisitsByPatientId(Long id) {
        return patientDao.findVisitByPatientId(id);
    }
}
