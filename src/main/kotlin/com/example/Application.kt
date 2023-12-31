package com.example

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.routing.head
import kotlinx.html.*
import kotlinx.serialization.Serializable

// Server Engines Supported by Ktor: Netty, Jetty, Tomcat, CIO

// Embedded Server Approach ================================================================

fun main() {
    embeddedServer(Netty, port = 8080) {
        extracted()
    }.start(wait = true)
}

private fun Application.extracted() {
    install(ContentNegotiation) {
        json()
    }
    module()
    //module_alt()
}

/*
    We can create multiple modules which extend from the Application Class. They can be grouped together, or can be
    split into different classes.  The size and complexity of the server will dictate the need for multiple modules.
 */

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Hello Test Module One!")
        }

        // Ex. passing normal parameters
        get("/users/{username}") {
            // extract param from request
            val username = call.parameters["username"]
            // extract header from request
            val header = call.request.headers["Connection"]
            // add header to response
            if (username.equals("Admin")) {
                call.response.header(name = "CustomHeader", value = "Admin")
                call.respond(message = "Hello Admin", status = HttpStatusCode.OK)
            }
            call.respondText("Hello $username -> with header: $header")
        }

        // Ex. passing query parameters
        get("/user") {
            val name = call.request.queryParameters["name"]
            val age = call.request.queryParameters["age"]

            call.respondText { "Hi, my name is $name and I'm $age years old" }
        }

        // Ex. returning custom object
        get("/person") {
            val person = Person("Jessica", 34)

            // Respond with various statuses
            try {
                call.respond(message = person, status = HttpStatusCode.OK)
            } catch (e: Exception) {
                call.respond(message = "${e.message}", status = HttpStatusCode.BadRequest)
            }
        }

        // Ex. redirecting to a different url
        get("/redirect") {
            call.respondRedirect(url = "/moved", permanent = false)
        }

        get("/moved") {
            call.respondText("You have successfully been redirected!")
        }

        // Mapping to any file in resources/assets with provided url param matching filename.
        staticResources("/static", "assets")

        // HTML DSL with Kotlin
        get("/welcome") {
            val name = call.request.queryParameters["name"]
            call.respondHtml {
                head {
                    title { +"Random Webshite"}
                }
                body {
                    if(name.isNullOrEmpty()) {
                        h3 { +"Welcome!"}
                    } else {
                        h3 { + "Welcome, $name!" }
                    }
                    p { +"Current directory is: ${System.getProperty("user.dir")}"}
                    // referencing image with specified path for resources declared above
                    img(src = "/static/tacoma.jpg")
                }
            }
        }

    }
}

// Serialization / Deserialization -> Transform any object into a string or json or vice versa.
// This object needs to be serializable if we are going to return it above.
@Serializable
data class Person(val name: String, val age: Int)

// left over from initial demonstration of multiple modules
fun Application.module_alt() {
    routing {
        get ("/alternate_module") {
            call.respondText ("Hello Alternate Module!")
        }
    }
}

// Engine Main Approach  ================================================================

//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
//
// This will run on localhost:8080/
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