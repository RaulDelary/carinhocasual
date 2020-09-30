package com.carinhocasual.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.auth.*
import io.ktor.gson.*

import com.carinhocasual.resource.basicAuthentication
import com.carinhocasual.resource.jwtAuthorization

fun Application.authRoute () {
    
    routing { 
        basicAuthentication ()
        jwtAuthorization()

        authenticate ("getAuthToken") {
            get ("/login") {
                val userPrincipal = call.authentication.principal <UserIdPrincipal> ()


                call.response.status (HttpStatusCode.OK)
                call.respondText ("Authenticated. UserID: ${userPrincipal?.name}")
            }
        }
    }
}