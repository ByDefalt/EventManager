package tracking_events.model

import jakarta.persistence.*

@Entity
data class Event(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var startHour: String,
    var finishHour: String,
    var description: String,
    var location: String,
    var creator: Long,  // ID du créateur
    var limitedPlaceNum: Int = 0,

    @ElementCollection
    var organisateurs: MutableList<Long> = mutableListOf(),

    @ElementCollection
    var listUser: MutableList<Long> = mutableListOf(),

    @ElementCollection
    var listeAttente: MutableList<Long> = mutableListOf(),

    var previousEventId: Long? = null  // Lien vers l'historique
)
