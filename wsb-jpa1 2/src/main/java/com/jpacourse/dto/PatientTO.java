package com.jpacourse.dto;

import com.jpacourse.persistance.entity.VisitEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


public class PatientTO implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String telephoneNumber;
    private String email;
    private String patientNumber;
    private LocalDate dateOfBirth;
    private Boolean isInsured;
    private Collection<CompletedVisitsTO> completedVisits = new ArrayList<>();




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getIsInsured() {
        return isInsured;
    }

    public void setIsInsured(Boolean isActive) {
        this.isInsured = isActive;
    }

    public Collection<CompletedVisitsTO> getCompletedVisits() {
        return completedVisits;
    }

    public void setCompletedVisits(Collection<VisitEntity> patientVisits) {
        Collection<CompletedVisitsTO> visitInfos = new ArrayList<>();
        for (VisitEntity visit : patientVisits) {
            if (visit.getTime().isBefore(LocalDateTime.now())) {
                CompletedVisitsTO visitInfo = new CompletedVisitsTO(visit.getId(),visit.getDoctor().getId(),visit.getTime(),visit.getDoctorEntityForVisits().getFirstName(),
                        visit.getDoctorEntityForVisits().getLastName(),
                        visit.getMedicalTreatmentEntityList().stream().map(x->x.getType()).collect(Collectors.toList()),
                        visit.getDescription());

                visitInfos.add(visitInfo);
            }
        }
        this.completedVisits = visitInfos;
    }
}


