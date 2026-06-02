package org.pi.websocket

import io.quarkus.websockets.next.OnOpen
import io.quarkus.websockets.next.WebSocket
import org.pi.usecase.AttendanceQueueUseCase

@WebSocket(path = "/attendance/ws")
class AttendanceWebSocket(
    private val attendanceQueueUseCase: AttendanceQueueUseCase,
) {
    @OnOpen
    fun onOpen(): String? {
        return attendanceQueueUseCase.currentMessage()
    }
}
