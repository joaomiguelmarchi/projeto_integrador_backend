package org.pi.usecase

import jakarta.enterprise.context.ApplicationScoped
import org.pi.model.Attendance
import org.pi.service.AttendanceWebSocketBroadcaster
import org.pi.utils.entities.DefaultResponse
import org.pi.utils.functions.NAME_IS_EMPTY
import org.pi.utils.functions.UNEXPECTED
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

@ApplicationScoped
class AttendanceQueueUseCase(
    private val attendanceWebSocketBroadcaster: AttendanceWebSocketBroadcaster,
) {
    private val queue = ConcurrentLinkedQueue<Attendance>()
    private val sequence = AtomicInteger(1)

    fun register(attendance: Attendance): DefaultResponse<Attendance> {
        try {
            val patientName = attendance.patientName?.trim()

            if (patientName.isNullOrEmpty()) {
                return DefaultResponse(
                    error = NAME_IS_EMPTY,
                )
            }

            val attendanceQueueItem = Attendance(
                id = sequence.getAndIncrement(),
                patientName = patientName,
            )

            queue.add(attendanceQueueItem)
            attendanceWebSocketBroadcaster.sendNextPatient(patientName)

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
        val patientName = nextAttendance.patientName ?: return null

        return attendanceWebSocketBroadcaster.buildNextPatientMessage(patientName)
    }
}
