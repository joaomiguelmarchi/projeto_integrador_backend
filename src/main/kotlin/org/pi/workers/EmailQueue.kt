package org.pi.workers

import jakarta.inject.Singleton
import org.pi.service.EmailService
import org.pi.utils.entities.Email
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.LinkedList

@Singleton
class EmailQueue(
    private val service: EmailService,
) : Thread() {
    private val list = LinkedList<Email>()

    val logger: Logger by lazy {
        LoggerFactory.getLogger(this::class.java)
    }

    init {
        start()
    }

    fun push(email: Email) {
        synchronized(list) {
            list.add(email)
        }
    }

    override fun run() {
        while (true) {
            try {
                var email: Email? = null

                synchronized(list) {
                    email = list.poll()
                }

                if (email != null) {
                    service.publish(email)
                }
            } catch (e: Exception) {
                logger.error("error on send email", e)
            }
        }
    }
}