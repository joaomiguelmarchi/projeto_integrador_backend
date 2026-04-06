package org.pi.repository

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.pi.model.User

@Entity
@Table(name = "user")
class UserJPA() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "name", nullable = false, length = 100)
    var name: String = ""

    @Column(name = "email", nullable = false, length = 90)
    var email: String = ""

    @Column(name = "password", nullable = false)
    var password: String = ""

    fun toUser(): User = User(
        id = this.id,
        name = this.name,
        email = this.email,
        password = this.password,
    )
}

@ApplicationScoped
class UserRepositoryJPA : PanacheRepositoryBase<UserJPA, Int>