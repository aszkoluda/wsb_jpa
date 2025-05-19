package com.jpacourse.persistance.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String telephoneNumber;

	private boolean isInsured;

	private LocalDateTime InsuredDateTo;
	private String email;

	@Column(nullable = false)
	private String patientNumber;



	@Column(nullable = false)
	private LocalDate dateOfBirth;

	// jendostronna od rodzica Patitent jest rodzicem
	@OneToOne()
	@JoinColumn(name = "address_id")
	private AddressEntity addresses;


	// relacja dwustronna pacjent jest rodzicem
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitEntity> Visits;

	public AddressEntity getPatientAddresses() {return  addresses;}

	public void addVisitToPatient(VisitEntity visit) {
		this.Visits.add(visit);
	}


	public List<VisitEntity> getVisits() {
		return Visits;
	}

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

	public void setIsInsured(Boolean isInsured) {
		this.isInsured = isInsured;
	}

	public void setInsuredDateTo(LocalDateTime InsuredDateTo) {
		this.InsuredDateTo = InsuredDateTo;
	}

	public LocalDateTime getInsuredDateTo() {
		return InsuredDateTo;
	}
}
