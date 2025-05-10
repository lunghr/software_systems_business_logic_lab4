package com.example.config

import com.example.model.UserNotFoundException
import com.example.service.UserService
import config.CustomAccessDeniedHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import service.JwtService


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private var userService: UserService,
    private var jwtService: JwtService,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(): AuthenticationProvider = DaoAuthenticationProvider().apply {
        setUserDetailsService(combinedUserDetailsService())
        setPasswordEncoder(passwordEncoder())
    }

    @Bean
    fun combinedUserDetailsService(): UserDetailsService {
        return UserDetailsService { username ->
            userService.getUserByEmailOrPhone(username, username)
                ?: throw UserNotFoundException()
        }
    }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
        configuration.authenticationManager

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.configurationSource(corsConfigurationSource()) }
            .csrf { it.disable() }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/auth/login/**", "/auth/register-user/**", "/bank/**").permitAll()
                    .requestMatchers("/ws/**").permitAll()
                    .requestMatchers("/index.html").permitAll()
                    .requestMatchers("/notifications/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/swagger-resources/", "/v3/api-docs/**","/openapi.yaml").permitAll()
                    .requestMatchers("/category/create/**").hasAnyAuthority("ADMIN", "MODERATOR")
                    .requestMatchers("/product/**").hasAnyAuthority("TRADER", "ADMIN")
                    .requestMatchers("/auth/change-role/**").hasAuthority("ADMIN")//
                    .anyRequest().authenticated()
            }
            .exceptionHandling { exceptionHandling ->
                exceptionHandling.accessDeniedHandler(customAccessDeniedHandler)
            }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(
                JwtAuthFilter(jwtService, userService),
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