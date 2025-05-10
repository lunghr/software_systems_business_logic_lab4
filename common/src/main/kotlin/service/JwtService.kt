package service

import io.github.cdimascio.dotenv.Dotenv
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.spec.SecretKeySpec


@Component
class JwtService {
    private var secret: String = Dotenv.load().get("SECRET_KEY")

    private val signingKey: SecretKeySpec
        get() {
            val keyBytes = Decoders.BASE64.decode(secret)
            return SecretKeySpec(keyBytes, 0, keyBytes.size, "HmacSHA256")
        }

    fun getSigningKey(): SecretKeySpec {
        return signingKey
    }

    private fun parseToken(token: String): Claims =
        Jwts.parser()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body

    private fun getExpiration(token: String): Date = parseToken(token).expiration

    private fun isTokenExpired(token: String): Boolean = getExpiration(token) < Date()

    private fun extractToken(token: String): String = token.removePrefix("Bearer ")

    fun getTokenFromHeader(request: HttpServletRequest): String? =
        request.getHeader("Authorization")?.takeIf { it.startsWith("Bearer ") }?.let { extractToken(it) }

    fun validateToken(token: String, userDetails: org.springframework.security.core.userdetails.UserDetails): Boolean {
        val username = getUsername(token)
        return (username == userDetails.username && !isTokenExpired(token))
    }

    fun getUsername(token: String): String = parseToken(token).subject

}