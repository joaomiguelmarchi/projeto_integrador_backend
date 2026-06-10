package org.pi.repository

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.pi.model.Procedure
import org.pi.model.User
import java.util.*

@ApplicationScoped
class ProcedureRepository(
    private val procedureRepositoryJPA: ProcedureRepositoryJPA,
) {
    @Transactional
    fun insertNewProcedure(procedure: Procedure): Procedure {
        val procedureJpa = ProcedureJPA()

        procedureJpa.name = procedure.name!!
        procedureJpa.value = procedure.value!!
        procedureJpa.type = procedure.type!!
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
        val procedureJpa = procedureRepositoryJPA.findById(procedure.id!!)
            ?: throw IllegalArgumentException("Procedure not found")

        procedureJpa.id = procedure.id!!
        procedureJpa.name = procedure.name!!
        procedureJpa.value = procedure.value!!
        procedureJpa.statusCode = procedure.statusCode!!
        procedureJpa.type = procedure.type!!

        procedureRepositoryJPA.persist(procedureJpa)

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