package com.d2d.grh.grhBackend.service;

import com.d2d.grh.grhBackend.entity.Event;
import com.d2d.grh.grhBackend.exception.EventNotFoundException;
import com.d2d.grh.grhBackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addNewEvent(Event event){
        return this.eventRepository.save(event);
    }

    public List<Event> getAllEvents(){
        return this.eventRepository.findAll();
    }

    public Event updateEvent(Long eventId, Event updatedEvent){
        Event currentEvent = this.eventRepository.findById(eventId).orElseThrow(()->
            new EventNotFoundException("no event match with this ID .."));
        System.out.println(currentEvent);
        currentEvent.setEventName(updatedEvent.getEventName());
        currentEvent.setEventType(updatedEvent.getEventType());
        currentEvent.setEventStartDate(updatedEvent.getEventStartDate());
        currentEvent.setEventEndDate(updatedEvent.getEventEndDate());
        return this.eventRepository.save(currentEvent);
    }
}
