package com.jpacourse.persistance.dao.impl;

import com.jpacourse.persistance.dao.PatientDao;
import com.jpacourse.persistance.entity.DoctorEntity;
import com.jpacourse.persistance.entity.PatientEntity;
import com.jpacourse.persistance.entity.VisitEntity;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.query.sqm.TemporalUnit;
import org.hibernate.sql.ast.tree.expression.DurationUnit;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Override
    @Transactional
    public void AddVisit(Long patientId, Long doctorId, LocalDateTime date, String description) {
       PatientEntity patientEntity = entityManager.find(PatientEntity.class, patientId);
       DoctorEntity doctorEntity = entityManager.find(DoctorEntity.class, doctorId);
       VisitEntity visitEntity = new VisitEntity();

       visitEntity.setPatient(patientEntity);
       visitEntity.setDoctor(doctorEntity);
       visitEntity.setTime(date);
       visitEntity.setDescription(description);

       entityManager.persist(visitEntity);
    }

    public List<PatientEntity> findPatientByLastName(String lastName){
       String sql = "Select u From PatientEntity u Where u.lastName = :lastName";
        TypedQuery<PatientEntity> query = entityManager.createQuery(sql, PatientEntity.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    public List<VisitEntity> findVisitByPatientId(Long patientId) {
        String jpql = "SELECT v FROM VisitEntity v WHERE v.patient.id = :patientId ORDER BY v.time DESC";

        TypedQuery<VisitEntity> query = entityManager.createQuery(jpql, VisitEntity.class);
        query.setParameter("patientId", patientId);

        return query.getResultList();
    }


    public List<PatientEntity> findPatientsWithMoreVisitThan(int visitCount) {
        String sql = "SELECT DISTINCT p FROM PatientEntity p " +
                "LEFT JOIN FETCH p.Visits v " +
                "GROUP BY p,v " +
                "HAVING COUNT(v) > :minVisits";
        TypedQuery<PatientEntity> query = entityManager.createQuery(sql, PatientEntity.class);
        query.setParameter("minVisits", visitCount);
        return query.getResultList();
    }


    public List<PatientEntity> findPatientsWithEndInsurenceIn(int monthToEndInsurence) {

        var currnetDate = LocalDateTime.now();
        String sql =  "SELECT p FROM PatientEntity p WHERE :monthToEndInsurence < p.InsuredDateTo ";
        TypedQuery<PatientEntity> query = entityManager.createQuery(sql, PatientEntity.class);
        query.setParameter("monthToEndInsurence", currnetDate.plusMonths(monthToEndInsurence));
       // query.setParameter("unit", DurationUnit);
      //  query.setParameter("currentDate", LocalDateTime.now());
        return query.getResultList();
    }
}
