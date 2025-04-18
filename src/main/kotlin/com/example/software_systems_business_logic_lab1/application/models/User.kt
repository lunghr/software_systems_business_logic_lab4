package com.example.software_systems_business_logic_lab1.application.models

import com.example.software_systems_business_logic_lab1.application.models.enums.UserRole
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(name = "user_id")
    val id: UUID = UUID.randomUUID(),
    @get:JvmName("getUsernameValue")
    @Column(name = "username", nullable = false, unique = false)
    val username: String,
    @get:JvmName("getPasswordValue")
    @Column(name = "password", nullable = false)
    val password: String,
    @Column(name = "email", nullable = false, unique = true)
    val email: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: UserRole,
    @Column(name = "phone_number", nullable = false, unique = true)
    val phoneNumber: String
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true

}
