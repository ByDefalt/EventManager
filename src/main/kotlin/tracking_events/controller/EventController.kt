package tracking_events.controller

import tracking_events.model.Event
import tracking_events.service.EventService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/events")
class EventController(private val eventService: EventService) {

    @GetMapping
    fun getAllEvents(): List<Event> {
        return eventService.getAllEvents()
    }

    @GetMapping("/{id}")
    fun getEventById(@PathVariable id: Long): Event {
        return eventService.getEventById(id)
    }

    @PostMapping
    fun createEvent(@RequestBody event: Event): Event {
        return eventService.createEvent(event)
    }

    @PutMapping("/{id}")
    fun updateEvent(@PathVariable id: Long, @RequestBody event: Event): Event {
        return eventService.updateEvent(id, event)
    }

    @DeleteMapping("/{id}")
    fun deleteEvent(@PathVariable id: Long) {
        eventService.deleteEvent(id)
    }
}
