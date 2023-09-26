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
        problemDetail.title = "Problema no envio da mensagem pra fila."
        problemDetail.detail = "Não soubemos especificar o motivo do erro."
        problemDetail.setProperty("timestamp", Instant.now())
        return problemDetail
    }

    @ExceptionHandler(ListenerJmsException::class)
    fun handleWithProblemsInJmsListener(listenerJmsException: ListenerJmsException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, listenerJmsException.localizedMessage)
        problemDetail.title = "Problema na captura de mensagens do listner."
        problemDetail.detail = "Não soubemos especificar o motivo do erro."
        problemDetail.setProperty("timestamp", Instant.now())
        return problemDetail
    }
}