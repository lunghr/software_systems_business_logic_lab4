package com.example.config


import com.example.model.UserNotFoundException
import com.example.service.UserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import service.JwtService

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