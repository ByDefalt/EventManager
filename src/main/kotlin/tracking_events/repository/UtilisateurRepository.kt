package tracking_events.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tracking_events.model.Utilisateur

@Repository
interface UtilisateurRepository : JpaRepository<Utilisateur, Long> {
    // Méthode personnalisée pour rechercher des utilisateurs par nom
    fun findByNom(nom: String): List<Utilisateur>
}
