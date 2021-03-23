package com.hibernate.example.service;

import com.hibernate.example.exception.CustomException;
import com.hibernate.example.exception.NotFoundException;
import com.hibernate.example.model.Event;
import java.util.List;
import java.util.Optional;

public interface ServiceCrudResource {

    // generate
    Event saveEventInformation(Event event);


    // search
    Optional<Event> searchByID(long id);

    Optional<List<Object[]>> findAllId();

    Optional<List<Event>> findEventByRepoID(long id);

    Optional<List<Event>> findEventByActorId(long id);

     Optional<List<Event>> findEventByRepoIdAndActorId(long repoId,long actorId);

    Optional<List<Event>> findAllEvents();

    //deleting
    void deleteAll() throws CustomException;

    Object DeleteByName(String name) throws NotFoundException;

    Object deleteByEventId(long id) throws NotFoundException;

    Object noSeQueHace();

}
