package tracking_events

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.junit.jupiter.api.extension.ExtendWith
import tracking_events.model.Utilisateur
import tracking_events.repository.UtilisateurRepository
import java.util.stream.Collectors

@DataJpaTest
@ExtendWith(SpringExtension::class)
class UtilisateurRepositoryTest {

    @Autowired
    lateinit var utilisateurRepository: UtilisateurRepository

    @Test
    fun `test utilisateur avec prenom et nom remplis`() {
        assertDoesNotThrow {
            Utilisateur(
                firstName = "John",
                lastName = "Doe",
                email = "john.doe@example.com"
            )
        }
    }

    @Test
    fun `test utilisateur avec prenom sans nom`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Utilisateur(
                firstName = "John",
                lastName = null,
                email = "john@example.com"
            )
        }
        assertEquals("Si le prénom est rempli, le nom doit l'être aussi, et inversement", exception.message)
    }

    @Test
    fun `test utilisateur avec nom sans prenom`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Utilisateur(
                firstName = null,
                lastName = "Doe",
                email = "doe@example.com"
            )
        }
        assertEquals("Si le prénom est rempli, le nom doit l'être aussi, et inversement", exception.message)
    }

    @Test
    fun `test utilisateur avec pseudo sans nom et prenom`() {
        assertDoesNotThrow {
            Utilisateur(
                pseudo = "johndoe",
                firstName = null,
                lastName = null,
                email = "johndoe@example.com"
            )
        }
    }

    @Test
    fun `test utilisateur sans pseudo ni nom ni prenom`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Utilisateur(
                pseudo = null,
                firstName = null,
                lastName = null,
                email = "anonymous@example.com"
            )
        }
        assertEquals("Le pseudo doit être rempli ou le nom/prénom", exception.message)
    }

    @Test
    fun `test utilisateur avec pseudo et nom et prenom remplis`() {
        assertDoesNotThrow {
            Utilisateur(
                pseudo = "johndoe",
                firstName = "John",
                lastName = "Doe",
                email = "johndoe@example.com"
            )
        }
    }

    @Test
    fun `test utilisateur sans email`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Utilisateur(
                pseudo = "johndoe",
                firstName = "John",
                lastName = "Doe",
                email = ""
            )
        }
        assertEquals("Email ne peut pas être vide", exception.message)
    }

    @Test
    fun `test utilisateur avec telephone optionnel rempli`() {
        assertDoesNotThrow {
            Utilisateur(
                pseudo = "johndoe",
                firstName = "John",
                lastName = "Doe",
                email = "johndoe@example.com",
                phone = "1234567890"
            )
        }
    }
}
