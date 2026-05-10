package org.pi.repository

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.*
import org.pi.model.Procedure
import org.pi.model.User

@Entity
@Table(name = "procedure")
class ProcedureJPA() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "name", nullable = false, length = 100)
    var name: String = ""

    @Column(name = "status_code", nullable = false)
    var statusCode: Int = 0

    @Column(name = "value", nullable = false)
    var value: Double = 0.0

    @Column(name = "rating", nullable = true)
    var rating: Int? = 0

    fun toProcedure(): Procedure = Procedure(
        id = this.id,
        name = this.name,
        statusCode = this.statusCode,
        value = this.value,
        rating = this.rating,
    )
}

@ApplicationScoped
class ProcedureRepositoryJPA : PanacheRepositoryBase<ProcedureJPA, Int>