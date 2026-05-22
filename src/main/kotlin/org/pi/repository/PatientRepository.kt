package org.pi.repository

import jakarta.transaction.Transactional
import org.pi.model.Patient
import org.pi.model.Procedure
import java.util.ArrayList

class PatientRepository (
    private val patientRepositoryJPA: PatientRepositoryJPA,
) {
    @Transactional
    fun insertNewPatient(patient: Patient): Patient {
        val patientJpa = PatientJPA()

        patientJpa.name = patient.name!!
        patientJpa.email = patient.email!!
        patientJpa.birthday = patient.birthday!!
        patientJpa.age = patient.age!!
        patientJpa.sex = patient.sex!!
        patientJpa.responsible = patient.responsible!!
        patientJpa.document = patient.document!!
        patientJpa.address = patient.address!!
        patientJpa.addressesNumber = patient.addressesNumber!!
        patientJpa.homePhoneNumber = patient.homePhoneNumber
        patientJpa.commercialPhoneNumber = patient.commercialPhoneNumber
        patientJpa.phoneNumber = patient.phoneNumber
        patientJpa.occupation = patient.occupation

        patientRepositoryJPA.persist(patientJpa)

        return patientJpa.toPatient()
    }

    fun findAllPatients(): List<Patient> {
        val patientsJpa = patientRepositoryJPA.listAll()
        val patients = ArrayList<Patient>()

        for (procedureJpa in patientsJpa) {
            patients.add(procedureJpa.toPatient())
        }

        return patients
    }

    fun findPatientById(id: Int): Patient? {
        return patientRepositoryJPA
            .findById(id)
            ?.toPatient()
    }

    @Transactional
    fun updatePatient(patient: Patient): Patient {
        val patientJpa = PatientJPA()

        patientJpa.id = patient.id!!
        patientJpa.name = patient.name!!
        patientJpa.email = patient.email!!
        patientJpa.birthday = patient.birthday!!
        patientJpa.age = patient.age!!
        patientJpa.sex = patient.sex!!
        patientJpa.responsible = patient.responsible!!
        patientJpa.document = patient.document!!
        patientJpa.address = patient.address!!
        patientJpa.addressesNumber = patient.addressesNumber!!
        patientJpa.homePhoneNumber = patient.homePhoneNumber
        patientJpa.commercialPhoneNumber = patient.commercialPhoneNumber
        patientJpa.phoneNumber = patient.phoneNumber
        patientJpa.occupation = patient.occupation

        patientRepositoryJPA.persist(patientJpa)

        return patientJpa.toPatient()
    }

    @Transactional
    fun deletePatient(id: Int): Boolean {
        val patientJpa = patientRepositoryJPA.findById(id)
            ?: return false

        patientRepositoryJPA.delete(patientJpa)

        return true
    }
}