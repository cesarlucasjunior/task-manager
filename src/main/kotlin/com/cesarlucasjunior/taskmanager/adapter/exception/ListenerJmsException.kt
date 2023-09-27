package com.cesarlucasjunior.taskmanager.adapter.exception

import java.lang.RuntimeException
import java.util.logging.Level
import java.util.logging.Logger

class ListenerJmsException: RuntimeException {

    constructor(error: String?) : super() {
        val log = Logger.getLogger(this.javaClass.name)
        log.log (Level.SEVERE,"Error in JMS Listener - $error")
    }
}