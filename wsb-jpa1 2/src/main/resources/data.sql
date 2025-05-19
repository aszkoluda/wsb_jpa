INSERT INTO ADDRESS (id, address_line1, address_line2, city, postal_code)
VALUES
    (1, 'ul. Kwiatowa 15', 'm. 2', 'Warszawa', '00-123'),
    (2, 'ul. Długa 7', NULL, 'Kraków', '30-456'),
    (3, 'ul. Słoneczna 21', 'lok. 5', 'Gdańsk', '80-001'),
    (4, 'ul. Polna 3', NULL, 'Poznań', '60-001'),
    (5, 'ul. Leśna 42', 'blok 3', 'Wrocław', '50-300');

-- Doctor
INSERT INTO DOCTOR (ID, first_name, last_name, telephone_number, EMAIL, doctor_number, SPECIALIZATION, address_id)
VALUES
    (1, 'Anna', 'Kowalska', '501123456', 'a.kowalska@example.com', 'DOC123', 'SURGEON', 1),
    (2, 'Jan', 'Nowak', '502987654', 'j.nowak@example.com', 'DOC124', 'GP', 2),
    (3, 'Maria', 'Lewandowska', '503555777', 'm.lewandowska@example.com', 'DOC125', 'DERMATOLOGIST', 3),
    (4, 'Adam', 'Wiśniewski', '504666888', 'a.wisniewski@example.com', 'DOC126', 'OCULIST', 4),
    (5, 'Olga', 'Zając', '505999000', NULL, 'DOC127', 'DERMATOLOGIST', 5);

-- Patient
INSERT INTO PATIENT (ID, first_name, last_name, telephone_number, EMAIL, patient_number, date_of_birth, address_id, is_insured, insured_date_to)
VALUES
    (1, 'Tomasz', 'Wiśniewski', '503111222', 't.wisniewski@example.com', 'PAT001', '1990-04-20', 3, true,'2025-12-01'),
    (2, 'Ewa', 'Zielińska', '504333444', 'e.zielinska@example.com', 'PAT002', '1985-11-15', 1, true,'2026-12-01 '),
    (3, 'Piotr', 'Maj', '505444555', NULL, 'PAT003', '2000-06-30', 2, false,'2024-12-01 '),
    (4, 'Natalia', 'Krawczyk', '506666777', 'n.krawczyk@example.com', 'PAT004', '1978-02-12', 4, true,'2027-12-01 '),
    (5, 'Karol', 'Bąk', '507888999', 'k.bak@example.com', 'PAT005', '1969-09-09', 5, false,'2021-11-01 ');

-- Visit
INSERT INTO VISIT (ID, DESCRIPTION, TIME, doctor_id, patient_id)
VALUES
    (10, 'Kontrola pediatryczna', '2025-04-01 09:30:00', 1, 1),
    (20, 'Badanie EKG', '2025-04-02 14:00:00', 2, 2),
    (30, 'Konsultacja dermatologiczna', '2025-04-03 11:00:00', 3, 3),
    (40, 'Kontrola po złamaniu', '2025-04-04 15:30:00', 4, 4),
    (50, 'Ogólna konsultacja', '2025-04-05 10:15:00', 5, 5),
    (60, 'Ogólna konsultacja1', '2025-04-05 10:15:00', 5, 5);

-- Medical_treatment
INSERT INTO MEDICAL_TREATMENT (ID, DESCRIPTION, TYPE, visit_id)
VALUES
    (1, 'Podjerzenie kamicy nerkowej. Badanie usg nerek', 'USG', 10),
    (2, 'EKG serca i analiza wyników', 'EKG', 20),
    (3, 'Podejrzenie wyrostka', 'USG', 30),
    (4, 'Medycyna pracy - badanie okresowe', 'EKG', 40),
    (5, 'Badanie kontrolne - ciąża', 'USG', 50),
    (6, 'Wykonanie zdjecia RTG. Podejrzenie złamania nogi', 'RTG', 50),
(7, 'Wykonanie zdjecia RTG. Podejrzenie złamania nogi', 'RTG', 60);

-- Wnioski – Laboratorium III
--
-- FetchMode.SELECT powoduje wykonywanie osobnych zapytań SQL do każdej relacji (tzw. problem N+1), co obniża wydajność przy dużej liczbie pacjentów.
-- FetchMode.JOIN wykonuje jedno zapytanie SQL z JOIN-em, co znacząco poprawia wydajność, ale trzeba uważać na powielanie danych przy paginacji.
-- Optimistic Locking (adnotacja @Version) wykrywa kolizje przy równoległej modyfikacji danych i zabezpiecza przed ich nadpisaniem.
