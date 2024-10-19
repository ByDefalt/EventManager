package tracking_events.service

import tracking_events.model.Event
import tracking_events.repository.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService(private val eventRepository: EventRepository) {

    fun getAllEvents(): List<Event> {
        return eventRepository.findAll()
    }

    fun getEventById(id: Long): Event {
        return eventRepository.findById(id)
            .orElseThrow { RuntimeException("Event not found!") }
    }

    fun createEvent(event: Event): Event {
        return eventRepository.save(event)
    }

    fun updateEvent(id: Long, updatedEvent: Event): Event {
        val event = getEventById(id)
        event.name = updatedEvent.name
        event.startHour = updatedEvent.startHour
        event.finishHour = updatedEvent.finishHour
        // Mise à jour des autres champs
        return eventRepository.save(event)
    }

    fun deleteEvent(id: Long) {
        eventRepository.deleteById(id)
    }
}
