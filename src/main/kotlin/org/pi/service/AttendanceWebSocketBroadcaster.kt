package org.pi.service

import io.quarkus.websockets.next.OpenConnections
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AttendanceWebSocketBroadcaster(
    private val openConnections: OpenConnections,
) {
    fun sendNextPatient(patientId: Int, patientName: String) {
        val message = buildNextPatientMessage(patientId, patientName)

        openConnections.listAll().forEach { connection ->
            runCatching {
                connection.sendTextAndAwait(message)
            }
        }
    }

    fun buildNextPatientMessage(patientId: Int, patientName: String): String {
        return """{"patientId":$patientId,"patientName":"${patientName.escapeJson()}"}"""
    }

    private fun String.escapeJson(): String {
        return buildString {
            this@escapeJson.forEach { char ->
                when (char) {
                    '\\' -> append("\\\\")
                    '"' -> append("\\\"")
                    '\b' -> append("\\b")
                    '\u000C' -> append("\\f")
                    '\n' -> append("\\n")
                    '\r' -> append("\\r")
                    '\t' -> append("\\t")
                    else -> append(char)
                }
            }
        }
    }
}
