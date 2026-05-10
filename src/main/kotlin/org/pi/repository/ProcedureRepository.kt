package org.pi.repository

import jakarta.transaction.Transactional
import org.pi.model.Procedure
import org.pi.model.User
import java.util.*

class ProcedureRepository(
    private val procedureRepositoryJPA: ProcedureRepositoryJPA,
) {
    @Transactional
    fun insertNewProcedure(procedure: Procedure): Procedure {
        val procedureJpa = ProcedureJPA()

        procedureJpa.name = procedure.name!!
        procedureJpa.value = procedure.value!!
        procedureJpa.rating = procedure.rating
        procedureJpa.statusCode = procedure.statusCode!!

        procedureRepositoryJPA.persist(procedureJpa)

        return procedureJpa.toProcedure()
    }

    fun findAllProcedures(): List<Procedure> {
        val proceduresJpa = procedureRepositoryJPA.listAll()
        val procedures = ArrayList<Procedure>()

        for (procedureJpa in proceduresJpa) {
            procedures.add(procedureJpa.toProcedure())
        }

        return procedures
    }

    fun findProcedureById(id: Int): Procedure? {
        return procedureRepositoryJPA
            .findById(id)
            ?.toProcedure()
    }

    @Transactional
    fun updateProcedure(procedure: Procedure): Procedure {
        val procedureJpa = ProcedureJPA()

        procedureJpa.id = procedure.id!!
        procedureJpa.name = procedure.name!!
        procedureJpa.value = procedure.value!!
        procedureJpa.statusCode = procedure.statusCode!!
        procedureJpa.rating = procedure.rating

        return procedureJpa.toProcedure()
    }

    @Transactional
    fun deleteProcedure(id: Int): Boolean {
        val procedureJpa = procedureRepositoryJPA.findById(id)
            ?: return false

        procedureRepositoryJPA.delete(procedureJpa)

        return true
    }
}