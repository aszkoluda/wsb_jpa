package com.jpacourse.dto;

import com.jpacourse.persistance.enums.TreatmentType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompletedVisitsTO implements Serializable {
    private Long id;
    private Long doctorID;
    private LocalDateTime time;
    private String doctorFirstName;
    private String doctorLastName;
    private String Description;
    private List<TreatmentType> typesOfTreatment = new ArrayList<>();

    public CompletedVisitsTO(Long id,Long doctorID, LocalDateTime time, String doctorFirstName, String doctorLastName, List<TreatmentType> types, String Description) {
       this.id = id;
       this.doctorID = doctorID;
        this.time = time;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.typesOfTreatment = types;
        this.Description = Description;

    }
    public Long getId() {return  id;}
    public LocalDateTime getTime() {
        return time;
    }

    public String getDescription() {return Description;}

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public Long getDoctorID() {
        return doctorID;
    }

    public String getDoctorFullName(){
        return doctorFirstName + " " + doctorLastName;
    }

    public Collection<TreatmentType> getTypesOfTreatment() {
        return typesOfTreatment;
    }

    public void setTypesOfTreatment(List<TreatmentType> typesOfTreatment) {
        this.typesOfTreatment = typesOfTreatment;
    }
}
