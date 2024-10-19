package tracking_events

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.junit.jupiter.api.extension.ExtendWith
import tracking_events.model.Utilisateur
import tracking_events.repository.UtilisateurRepository

@DataJpaTest
@ExtendWith(SpringExtension::class)
class UtilisateurRepositoryTest {

    @Autowired
    lateinit var utilisateurRepository: UtilisateurRepository

    @Test
    fun `test findByNom should return a list of users with the same name`() {
        // Préparer les données de test
        val utilisateur1 = Utilisateur(nom = "Jean", email = "jean@example.com")
        val utilisateur2 = Utilisateur(nom = "Jean", email = "jean2@example.com")
        val utilisateur3 = Utilisateur(nom = "Marie", email = "marie@example.com")

        // Sauvegarder les utilisateurs dans la base
        utilisateurRepository.saveAll(listOf(utilisateur1, utilisateur2, utilisateur3))

        // Effectuer la demande à la base de données
        val result = utilisateurRepository.findByNom("Jean")

        // Vérifier que le résultat contient les utilisateurs attendus
        assertEquals(2, result.size)
        assertTrue(result.any { it.email == "jean@example.com" })
        assertTrue(result.any { it.email == "jean2@example.com" })

        // Afficher le contenu de la table Utilisateur
        val allUsers = utilisateurRepository.findAll()
        println("Contenu de la table Utilisateur :")
        allUsers.forEach { user ->
            println("Id: ${user.id}, Nom: ${user.nom}, Email: ${user.email}")
        }
    }
}
