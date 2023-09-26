package com.cesarlucasjunior.taskmanager.adapter.exception

import java.lang.RuntimeException

class ListenerJmsException: RuntimeException {

    constructor(error: String?) : super() {
        println("Error in JMS Listener - $error")
    }
}