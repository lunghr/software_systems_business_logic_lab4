package com.example.software_systems_business_logic_lab1.application.services

import com.example.software_systems_business_logic_lab1.application.models.User
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import jakarta.servlet.http.HttpServletRequest
import java.util.*
import javax.crypto.spec.SecretKeySpec
import kotlin.collections.HashMap

@Service
class JwtService {
    private var secret: String = Dotenv.load().get("SECRET_KEY")

    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        if (userDetails is User) {
            val customUserDetails = userDetails
            claims["id"] = customUserDetails.id ?: 0
            claims["email"] = customUserDetails.email
            claims["role"] = customUserDetails.role
        }
        println("Token generated")
        return Jwts.builder()
            .subject(userDetails.username)
            .claims(claims)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(signingKey)
            .compact()
    }

    private val signingKey: SecretKeySpec
        get() {
            val keyBytes = Decoders.BASE64.decode(secret)
            return SecretKeySpec(keyBytes, 0, keyBytes.size, "HmacSHA256")
        }

    // TODO: Try to escape deprecated methods but i really dk how to do it
    fun <T> getClaim(token: String, resolver: (Claims) -> T): T =
        Jwts.parser()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body
            .let(resolver)

    fun getUsername(token: String): String = getClaim(token) { it.subject }

    fun getRole(token: String): String = getClaim(token) { it["role"] as String }

    fun extractToken(token: String): String = token.removePrefix("Bearer ")

    fun getTokenFromHeader(request: HttpServletRequest): String? =
        request.getHeader("Authorization")?.takeIf { it.startsWith("Bearer ") }?.let { extractToken(it) }

    fun getExpiration(token: String): Date = getClaim(token) { it.expiration }

    fun isTokenExpired(token: String): Boolean = getExpiration(token) < Date()

    fun validateToken(token: String, userDetails: UserDetails): Boolean =
        getUsername(token) == userDetails.username && !isTokenExpired(token)
}