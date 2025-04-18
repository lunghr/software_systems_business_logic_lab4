package com.example.software_systems_business_logic_lab1.application.config

import com.example.software_systems_business_logic_lab1.application.models.UserNotFoundException
import com.example.software_systems_business_logic_lab1.application.services.JwtService
import com.example.software_systems_business_logic_lab1.application.services.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userService: UserService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = jwtService.getTokenFromHeader(request)
        val username = jwt?.let { jwtService.getUsername(it) }
//        println(username)
        if (username != null && SecurityContextHolder.getContext().authentication == null) {


            val authenticatedUser = userService.getUserByEmailOrPhone(username, username)
                ?: throw UserNotFoundException()

            if (jwtService.validateToken(jwt, authenticatedUser)) {
                val authToken =
                    UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.authorities).apply {
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    }
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }
}