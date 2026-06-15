package org.pi.usecase

import jakarta.enterprise.context.ApplicationScoped
import org.pi.model.Attendance
import org.pi.repository.PatientRepository
import org.pi.service.AttendanceWebSocketBroadcaster
import org.pi.utils.entities.DefaultResponse
import org.pi.utils.functions.PATIENT_ID_IS_EMPTY
import org.pi.utils.functions.PATIENT_NOT_FOUND
import org.pi.utils.functions.UNEXPECTED
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

@ApplicationScoped
class AttendanceQueueUseCase(
    private val attendanceWebSocketBroadcaster: AttendanceWebSocketBroadcaster,
    private val patientRepository: PatientRepository,
) {
    private val queue = ConcurrentLinkedQueue<Attendance>()
    private val sequence = AtomicInteger(1)

    fun register(attendance: Attendance): DefaultResponse<Attendance> {
        try {
            val patientId = attendance.patientId

            if (patientId == null || patientId <= 0) {
                return DefaultResponse(
                    error = PATIENT_ID_IS_EMPTY,
                )
            }

            val patient = patientRepository.findPatientById(patientId)
                ?: return DefaultResponse(
                    error = PATIENT_NOT_FOUND,
                )

            val patientName = patient.name?.trim().orEmpty()

            val attendanceQueueItem = Attendance(
                id = sequence.getAndIncrement(),
                patientId = patientId,
            )

            queue.add(attendanceQueueItem)
            attendanceWebSocketBroadcaster.sendNextPatient(patientId, patientName)

            return DefaultResponse(attendanceQueueItem)
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED,
            )
        }
    }

    fun list(): DefaultResponse<List<Attendance>> {
        try {
            return DefaultResponse(queue.toList())
        } catch (e: Exception) {
            return DefaultResponse(
                error = UNEXPECTED,
            )
        }
    }

    fun currentMessage(): String? {
        val nextAttendance = queue.peek() ?: return null
        val patientId = nextAttendance.patientId ?: return null
        val patientName = nextAttendance.patientName ?: return null

        return attendanceWebSocketBroadcaster.buildNextPatientMessage(patientId, patientName)
    }
}
