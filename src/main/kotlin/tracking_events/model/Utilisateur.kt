package tracking_events.model

import jakarta.persistence.*

@Entity
data class Utilisateur(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var pseudo: String?,
    var firstName: String?,
    var lastName: String?,
    var email: String,
    var phone: String? = null,

    @ElementCollection
    var eventCreator: MutableList<Long> = mutableListOf(),

    @ElementCollection
    var eventOrganisateur: MutableList<Long> = mutableListOf(),

    @ElementCollection
    var eventParticipant: MutableList<Long> = mutableListOf()
)
