package com.example

import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// Server Engines Supported by Ktor: Netty, Jetty, Tomcat, CIO
// running on localhost:8080/

// Engine Main Approach  ================================================================

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
//
//// This will run on localhost:8080/
//fun Application.module() {
//    routing {
//        get ("/module") {
//            call.respondText ("Hello World!")
//        }
//    }
//}
//
//fun Application.module_alt() {
//    routing {
//        get ("/module_alt") {
//            call.respondText ("Hello Module Alt!")
//        }
//    }
//}

// Embedded Server Approach ================================================================

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
        module_alt()
    }.start(wait = true)
}

/*
    We can create multiple modules which extend from the Application Class. They can be grouped together, or can be
    split into different classes.  The size and complexity of the server will dictate the need for multiple modules.
 */

fun Application.module() {
    routing {
        get ("/test_one") {
            call.respondText ("Hello Test Module One!")
        }
    }
}

fun Application.module_alt() {
    routing {
        get ("/test_two") {
            call.respondText ("Hello Test Module Two!")
        }
    }
}