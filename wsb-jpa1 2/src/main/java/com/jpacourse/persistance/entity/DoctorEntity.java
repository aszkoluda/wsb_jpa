package com.jpacourse.persistance.entity;

import com.jpacourse.persistance.enums.Specialization;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String telephoneNumber;

	private String email;

	@Column(nullable = false)
	private String doctorNumber;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	// jendostronna od rodzica Doctor jest rodzicem
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private AddressEntity addresses;

	// relacja dwustronna doktor jest rodzicem
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<VisitEntity> Visits;

	public void setDoctorVisits(List<VisitEntity> treatments) {
		this.Visits = treatments;
	}

	public List<VisitEntity> getDoctorVisits() {
		return Visits;
	}
	public void addVisitToDoctor(VisitEntity visit) {
		this.Visits.add(visit);
	}
	public AddressEntity getAddress() {return addresses;}

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

	public String getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

}
