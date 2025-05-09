# Hospital Information System - Summary

This document outlines the structure and relationships within a hospital's information system, modeled using an Entity-Relationship (ER) approach.

## Entities and Attributes

### 1. Employee (Superclass)
- **Attributes:** EmpID (PK), Surname, FirstName, Address, Telephone

### 2. Doctor (Subclass of Employee)
- **Attributes:** EmpID (PK, FK), Speciality

### 3. Nurse (Subclass of Employee)
- **Attributes:** EmpID (PK, FK), Rotation, Salary

### 4. Department
- **Attributes:** DeptCode (PK), Name, Building, DirectorID (FK → Doctor)

### 5. Ward
- **Attributes:** DeptCode (PK, FK), WardNumber (PK), NumberOfBeds, SupervisorID (FK → Nurse)

### 6. Patient
- **Attributes:** PatientID (PK), Surname, FirstName, Address, Telephone

### 7. Hospitalization
- **Attributes:** PatientID (PK, FK), DeptCode (FK), WardNumber (FK), BedNumber, Diagnosis, DoctorID (FK)

### 8. Transfer
- **Attributes:** TransferID (PK), PatientID (FK), FromDept, FromWard, ToDept, ToWard, Reason, Date

## Relationships

- Employees can be either **Doctors** or **Nurses**.
- Each **Department** is directed by a **Doctor**.
- Each **Ward** belongs to a **Department** and is supervised by a **Nurse**.
- **Nurses** are assigned to **one Department only**.
- **Doctors** are not department-bound but have a **speciality**.
- **Patients** are **hospitalized** in **Wards** and treated by **Doctors**.
- **Transfers** may occur between wards in case of complications.

## Notes
- Ward numbers are **local** to their departments.
- Each patient can be associated with **one or more hospitalizations and transfers** during their stay.
