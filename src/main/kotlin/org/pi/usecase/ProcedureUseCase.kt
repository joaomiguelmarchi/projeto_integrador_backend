package org.pi.usecase

import jakarta.enterprise.context.ApplicationScoped
import org.pi.model.Procedure
import org.pi.repository.ProcedureRepository
import org.pi.repository.UserRepository
import org.pi.utils.entities.DefaultResponse
import org.pi.utils.entities.Email
import org.pi.utils.functions.UNEXPECTED
import org.pi.workers.EmailQueue

@ApplicationScoped
class ProcedureUseCase(
    private val procedureRepository: ProcedureRepository,
    private val mailQueue: EmailQueue
) {
    fun insertProcedure(procedure: Procedure): DefaultResponse<Procedure> {
        try {
            mailQueue.push(Email(
                subject = "Procedure",
                content = "Procedure ${procedure.name}",
                to = listOf("lucasvicenti60@gmail.com")
            ))

            val procedure = procedureRepository.insertNewProcedure(procedure)

            return DefaultResponse(procedure)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun list(): DefaultResponse<List<Procedure>> {
        try {
            val procedures = procedureRepository.findAllProcedures()

            return DefaultResponse(procedures)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun details(id: Int): DefaultResponse<Procedure> {
        try {
            val procedure = procedureRepository.findProcedureById(id)

            return DefaultResponse(procedure)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun edit(procedure: Procedure): DefaultResponse<Procedure> {
        try {
            val procedure = procedureRepository.updateProcedure(procedure)

            return DefaultResponse(procedure)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }

    fun delete(id: Int): DefaultResponse<Boolean> {
        try {
            val procedure = procedureRepository.deleteProcedure(id)

            return DefaultResponse(procedure)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED
            )
        }
    }
}