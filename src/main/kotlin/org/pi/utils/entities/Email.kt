package org.pi.utils.entities

data class Email(
    val subject: String,
    val content: String,
    val to: List<String>,
    val withHtml: Boolean? = false
)

data class ResendEmailRequest(
    val from: String,
    val to: List<String>,
    val subject: String,
    val html: String,
)