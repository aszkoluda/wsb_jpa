package com.jpacourse.persistance.entity;

import java.time.LocalDateTime;
import java.util.List;
import com.jpacourse.dto.PatientTO;
import jakarta.persistence.*;

@Entity
@Table(name = "VISIT")
public class VisitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@Column(nullable = false)
	private LocalDateTime time;



	// relacja dwukierunkowa (wizyta  jest dzieckiem)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", nullable = false)
	private DoctorEntity doctor;


	// relacja dwukierunkowa (wizyta  jest dzieckiem)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	private PatientEntity patient;


	// relacja jednostronna (wizyta jest rodzicem)
	@OneToMany(mappedBy = "visit")
	private List<MedicalTreatmentEntity> treatments;
	public boolean isNew() {
		return id == null;
	}
	public DoctorEntity getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorEntity docotr) {
		this.doctor = docotr;
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

    public DoctorEntity getDoctorEntityForVisits() {
		return doctor;
    }

	public List<MedicalTreatmentEntity> getMedicalTreatmentEntityList() {
		return treatments;
	}
}
