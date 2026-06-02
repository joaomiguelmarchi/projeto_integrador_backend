package org.pi.service

import io.quarkus.websockets.next.OpenConnections
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class AttendanceWebSocketBroadcaster(
    private val openConnections: OpenConnections,
) {
    fun sendNextPatient(patientName: String) {
        val message = buildNextPatientMessage(patientName)

        openConnections.listAll().forEach { connection ->
            runCatching {
                connection.sendTextAndAwait(message)
            }
        }
    }

    fun buildNextPatientMessage(patientName: String): String {
        return "O proximo atendimento é com o paciente \"$patientName\""
    }
}
