package com.cesarlucasjunior.taskmanager.adapter.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant

@ControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(ProducerMessageException::class)
    fun handleWithProblemsInMessageProducer(producerMessageException: ProducerMessageException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, producerMessageException.localizedMessage)
        problemDetail.title = "Problem sending the message to the queue."
        problemDetail.detail = "A generic error was returned."
        problemDetail.setProperty("timestamp", Instant.now())
        return problemDetail
    }

    @ExceptionHandler(ListenerJmsException::class)
    fun handleWithProblemsInJmsListener(listenerJmsException: ListenerJmsException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, listenerJmsException.localizedMessage)
        problemDetail.title = "Problem capturing listener messages."
        problemDetail.detail = "A generic error was returned."
        problemDetail.setProperty("timestamp", Instant.now())
        return problemDetail
    }
}