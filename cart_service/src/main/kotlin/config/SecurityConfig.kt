package com.example.config

import config.CustomAccessDeniedHandler
import config.JwtAuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import service.JwtService


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private var customAccessDeniedHandler: CustomAccessDeniedHandler,
    private var jwtService: JwtService
) {


    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/ws/**", "/index.html").permitAll()
                    .requestMatchers("/swagger-ui/**", "/swagger-resources/", "/v3/api-docs/**", "/openapi.yaml")
                    .permitAll()
                    .requestMatchers("/cart/**").hasAnyAuthority("USER", "ADMIN", "MODERATOR", "TRADER")
                    .anyRequest().authenticated()
            }
            .exceptionHandling { exceptionHandling ->
                exceptionHandling.accessDeniedHandler(customAccessDeniedHandler)
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(
                JwtAuthFilter(jwtService),
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val corsConfiguration = CorsConfiguration().apply {
            allowedOrigins = listOf("*")
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
            allowedHeaders = listOf("Authorization", "Content-Type", "X-Requested-With", "Origin", "Referer")
            allowCredentials = false
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }
}