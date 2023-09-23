package com.cesarlucasjunior.taskmanager.adapter.exception


class ProducerMessageException: RuntimeException {

    constructor(error: String?) : super() {
        println("Error - $error")
    }
}