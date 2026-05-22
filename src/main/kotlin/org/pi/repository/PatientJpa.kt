package org.pi.repository

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.pi.model.Patient
import org.pi.model.Procedure
import java.time.Instant
import java.time.LocalDate
import java.util.Date

@Entity
@Table(name = "patient")
class PatientJPA() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "name", nullable = false, length = 100)
    var name: String = ""

    @Column(name = "email", nullable = false, length = 100)
    var email: String = ""

    @Column(name = "birthday", nullable = false)
    var birthday: LocalDate = LocalDate.now()

    @Column(name = "age", nullable = false)
    var age: Int = 0

    @Column(name = "sex", nullable = false)
    var sex: String = ""

    @Column(name = "responsible", nullable = true)
    var responsible: String? = null

    @Column(name = "document", nullable = false, length = 11)
    var document: String = ""

    @Column(name = "address", nullable = false)
    var address: String = ""

    @Column(name = "addressesNumber", nullable = false, length = 8)
    var addressesNumber: String = ""

    @Column(name = "homePhoneNumber", nullable = true)
    var homePhoneNumber: String? = null

    @Column(name = "commercialPhoneNumber", nullable = true)
    var commercialPhoneNumber: String? = null

    @Column(name = "phoneNumber", nullable = true)
    var phoneNumber: String? = null

    @Column(name = "occupation", nullable = true)
    var occupation: String? = null

    fun toPatient(): Patient = Patient(
        id = this.id,
        name = this.name,
        birthday = this.birthday,
        age = this.age,
        sex = this.sex,
        responsible = this.responsible,
        document = this.document,
        address = this.address,
        addressesNumber = this.addressesNumber,
        homePhoneNumber = this.homePhoneNumber,
        commercialPhoneNumber = this.commercialPhoneNumber,
        phoneNumber = this.phoneNumber,
        occupation = this.occupation,
    )
}

@ApplicationScoped
class PatientRepositoryJPA : PanacheRepositoryBase<PatientJPA, Int>