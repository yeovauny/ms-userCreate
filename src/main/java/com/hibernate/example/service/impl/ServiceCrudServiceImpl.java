package com.hibernate.example.service.impl;

import com.hibernate.example.common.Constant;
import com.hibernate.example.exception.CustomException;
import com.hibernate.example.exception.NotFoundException;
import com.hibernate.example.repository.EventRepository;
import com.hibernate.example.model.Event;
import com.hibernate.example.service.ServiceCrudResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCrudServiceImpl implements ServiceCrudResource {


    @Autowired
    EventRepository eventRepository;

    @Override
    public Event saveEventInformation(Event event) {
        eventRepository.save(event);
        return event;
    }

    @Override
    public Optional<Event> searchByID(long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<List<Event>> findEventByActorId(long id){
        return eventRepository.findByActor_actorid(id);
    }

    @Override
     public Optional<List<Event>>  findEventByRepoIdAndActorId(long repoId, long actorId) {
      return eventRepository.findDistinctByActor_actorid_OrRepo_repoid(actorId,repoId);
    }

    @Override
    public Optional<List<Event>> findEventByRepoID(long id) {
        return eventRepository.findByRepo_repoid(id);
    }

    //  a esta busqueda hay que modificarla para que se transforme en un lista de mapas de clases
    @Override
    public Optional<List<Object[]>> findAllId() {
       return eventRepository.myFindEventIds();
    }

    @Override
    public Optional<List<Event>> findAllEvents() {
        return eventRepository.findAllEvents();
    }

    @Override
    public void deleteAll() throws CustomException {
        try {
            eventRepository.deleteAll();
        }catch(Exception e){
            throw new CustomException("there is an error when you are trying to delete");
        }

    }

    @Override
    public Object DeleteByName(String name) throws NotFoundException {
         boolean result=  eventRepository.findByname(name).map(event -> {
                    eventRepository.delete(event);
                    return true;
                }
                ).orElse(  false );

         if(!result)
             throw new NotFoundException(Constant.RECOD_DONT_EXIST);
         else
             return Constant.WAS_DETELE;


    }


    // this delete is by crudRepository , is necessary know all crud repository methods.
    @Override
    public Object deleteByEventId(long id) throws NotFoundException{
        boolean result = eventRepository.findById(id).map( event -> {
                    eventRepository.delete(event);
                    return true;
                }
        ).orElse(false);
        if(!result)
            throw  new NotFoundException(Constant.RECOD_DONT_EXIST);
        else
            return Constant.WAS_DETELE;
    }


    @Override
    public Object noSeQueHace(){
        return eventRepository.findEventsByActor_EmptyOrderByName();
    }




}
