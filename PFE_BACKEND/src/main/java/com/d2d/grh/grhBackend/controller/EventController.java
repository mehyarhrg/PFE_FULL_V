package com.d2d.grh.grhBackend.controller;

import com.d2d.grh.grhBackend.entity.Event;
import com.d2d.grh.grhBackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/addNewEvent")
    public Event addNewEvent(@RequestBody Event event){
       return this.eventService.addNewEvent(event);
    }

    @GetMapping("/allEvents")
    public List<Event> getAllEvents(){
        return this.eventService.getAllEvents();
    }

    @PostMapping("/updateEvent/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event){
        System.out.println(event);
        return this.eventService.updateEvent(id, event);
    }
}
