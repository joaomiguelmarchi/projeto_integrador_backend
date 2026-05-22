package org.pi.usecase

import org.pi.model.Patient
import org.pi.repository.PatientRepository
import org.pi.utils.entities.DefaultResponse
import org.pi.utils.functions.ADDRESSES_NUMBER_IS_EMPTY
import org.pi.utils.functions.ADDRESS_IS_EMPTY
import org.pi.utils.functions.AGE_IS_EMPTY
import org.pi.utils.functions.BIRTHDAY_IS_EMPTY
import org.pi.utils.functions.DOCUMENT_IS_EMPTY
import org.pi.utils.functions.EMAIL_IS_EMPTY
import org.pi.utils.functions.NAME_IS_EMPTY
import org.pi.utils.functions.RESPONSIBLE_IS_EMPTY
import org.pi.utils.functions.SEX_IS_EMPTY
import org.pi.utils.functions.UNEXPECTED

class PatientUseCase(
    private val patientRepository: PatientRepository,

    ) {
    fun insertPatient(patient: Patient): DefaultResponse<Patient> {
        try {

            if (patient.name?.isEmpty() ?: true) {
                return DefaultResponse(
                    error = NAME_IS_EMPTY,
                )
            }

            if (patient.name?.isEmpty() ?: true) {
                return DefaultResponse(
                    error = NAME_IS_EMPTY,
                )
            }

            if (patient.email?.isEmpty() ?: true) {
                return DefaultResponse(
                    error = EMAIL_IS_EMPTY,
                )
            }

            if (patient.birthday == null) {
                return DefaultResponse(
                    error = BIRTHDAY_IS_EMPTY,
                )
            }

            if (patient.age == null || patient.age == 0) {
                return DefaultResponse(
                    error = AGE_IS_EMPTY,
                )
            }

            if (patient.sex?.isEmpty() ?: true) {
                return DefaultResponse(
                    error = SEX_IS_EMPTY,
                )
            }

            if (patient.responsible?.isEmpty() ?: true) {
                return DefaultResponse(
                    error = RESPONSIBLE_IS_EMPTY,
                )
            }

            if (patient.document?.isEmpty() ?: true) {
                return DefaultResponse(
                    error = DOCUMENT_IS_EMPTY,
                )
            }

            if (patient.address?.isEmpty() ?: true) {
                return DefaultResponse(
                    error = ADDRESS_IS_EMPTY,
                )
            }

            if (patient.addressesNumber?.isEmpty() ?: true) {
                return DefaultResponse(
                    error = ADDRESSES_NUMBER_IS_EMPTY,
                )
            }

            val patient = patientRepository.insertNewPatient(patient)

            return DefaultResponse(patient)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun list(): DefaultResponse<List<Patient>> {
        try {
            val patients = patientRepository.findAllPatients()

            return DefaultResponse(patients)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun details(id: Int): DefaultResponse<Patient> {
        try {
            val patient = patientRepository.findPatientById(id)

            return DefaultResponse(patient)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun edit(patient: Patient): DefaultResponse<Patient> {
        try {
            val patient = patientRepository.updatePatient(patient)

            return DefaultResponse(patient)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun delete(id: Int): DefaultResponse<Boolean> {
        try {
            val patient = patientRepository.deletePatient(id)

            return DefaultResponse(patient)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }
}