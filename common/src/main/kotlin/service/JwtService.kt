package service

import io.github.cdimascio.dotenv.Dotenv
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.spec.SecretKeySpec


@Service
class JwtService {
    private var secret: String = "53A73E5F1C4E0A2D3B5F2D784E6A1B423D6F247D1F6E5C3A596D635A75327855"

    val signingKey: SecretKeySpec
        get() {
            val keyBytes = Decoders.BASE64.decode(secret)
            return SecretKeySpec(keyBytes, 0, keyBytes.size, "HmacSHA256")
        }

    fun parseToken(token: String): Claims =
        Jwts.parser()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body

    fun extractId(token: String): UUID = UUID.fromString(parseToken(token)["id"].toString())

    private fun getExpiration(token: String): Date = parseToken(token).expiration

    fun extractToken(token: String): String = token.removePrefix("Bearer ")

    fun getTokenFromHeader(request: HttpServletRequest): String? =
        request.getHeader("Authorization")?.takeIf { it.startsWith("Bearer ") }?.let { extractToken(it) }


}