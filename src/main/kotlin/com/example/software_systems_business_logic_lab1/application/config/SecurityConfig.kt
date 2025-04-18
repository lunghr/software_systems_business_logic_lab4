package com.example.software_systems_business_logic_lab1.application.config

import com.example.software_systems_business_logic_lab1.application.models.UserNotFoundException
import com.example.software_systems_business_logic_lab1.application.services.JwtService
import com.example.software_systems_business_logic_lab1.application.services.UserService
import org.springframework.beans.factory.annotation.Autowired
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
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private var userService: UserService,
    private var jwtService: JwtService
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
                    .requestMatchers("/auth/login", "auth/register-user").permitAll()
                    .requestMatchers("/ws/**").permitAll()
                    .requestMatchers("/index.html").permitAll()
                    .requestMatchers("/notifications/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/swagger-resources/", "/v3/api-docs/**","/openapi.yaml").permitAll()
                    .requestMatchers("/category/create/**").hasAnyAuthority("ADMIN", "MODERATOR")
                    .requestMatchers("/product/**").hasAuthority("TRADER")
                    .requestMatchers("/auth/change-role/**").hasAuthority("ADMIN")//
                    .anyRequest().authenticated()
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