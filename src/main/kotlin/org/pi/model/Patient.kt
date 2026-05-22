package org.pi.model

import java.time.LocalDate

class Patient(
    var id: Int? = 0,
    var name: String? = "",
    var email: String? = "",
    var birthday: LocalDate? = LocalDate.now(),
    var age: Int? = 0,
    var sex: String? = "",
    var responsible: String? = null,
    var document: String? = "",
    var address: String? = "",
    var addressesNumber: String? = "",
    var homePhoneNumber: String? = null,
    var commercialPhoneNumber: String? = null,
    var phoneNumber: String? = null,
    var occupation: String? = null,
)
