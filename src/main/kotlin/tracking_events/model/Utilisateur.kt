package tracking_events.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Utilisateur(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var pseudo: String? = null, // Champ optionnel
    var firstName: String? = null,
    var lastName: String? = null,

    var email: String, // Champ obligatoire
    var phone: String? = null, // Champ optionnel

    @ElementCollection
    var eventCreator: MutableList<Long>? = mutableListOf(), // Liste des événements créés par l'utilisateur

    @ElementCollection
    var eventOrganisateur: MutableList<Long>? = mutableListOf(), // Liste des événements où l'utilisateur est organisateur

    @ElementCollection
    var eventParticipant: MutableList<Long>? = mutableListOf(), // Liste des événements auxquels l'utilisateur participe

    var preferredContact: String? = null, // Moyen de contact préféré (optionnel)
    var profilePicture: String? = null, // URL de l'image de profil (optionnel)

    var notifications: Boolean = false, // Notifications activées pour les événements (par défaut false)
    var bio: String? = null, // Brève description ou bio de l'utilisateur (optionnel)

    var registrationDate: LocalDateTime = LocalDateTime.now() // Date d'inscription (par défaut à la date actuelle)
){
    init {
        validateUser()
    }

    private fun validateUser() {
        // Validation : Pseudo optionnel si prénom et nom sont remplis
        if (firstName.isNullOrEmpty() && !lastName.isNullOrEmpty()) {
            throw IllegalArgumentException("Si le prénom est rempli, le nom doit l'être aussi, et inversement")
        }

        if (!firstName.isNullOrEmpty() && lastName.isNullOrEmpty()) {
            throw IllegalArgumentException("Si le prénom est rempli, le nom doit l'être aussi, et inversement")
        }

        if (pseudo.isNullOrEmpty() && firstName.isNullOrEmpty() && lastName.isNullOrEmpty()) {
            throw IllegalArgumentException("Le pseudo doit être rempli ou le nom/prénom")
        }
        if (email.isEmpty()) {
            throw IllegalArgumentException("Email ne peut pas être vide")
        }

    }
}
