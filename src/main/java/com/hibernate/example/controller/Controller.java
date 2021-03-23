package com.hibernate.example.controller;

import com.hibernate.example.exception.CustomException;
import com.hibernate.example.exception.NotFoundException;
import com.hibernate.example.model.Event;
import com.hibernate.example.service.ServiceCrudResource;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value ="/api")
public class Controller {

    ServiceCrudResource serviceCrudResource;

    @Autowired
    public Controller (ServiceCrudResource serviceCrudResource){
        this.serviceCrudResource=serviceCrudResource;
    }

    // save option

    @PostMapping("/save-event")
    public ResponseEntity<Event> saveEventData(@RequestBody  Event event){
        return new ResponseEntity<>(serviceCrudResource.saveEventInformation(event), HttpStatus.CREATED);
    }


    //find options
    @GetMapping("/findbyid/{id}")
    @ApiOperation("Get data by  Event id")
    @ApiResponses({
        @ApiResponse(code=200 , message = "OK"),
        @ApiResponse(code=400, message = "Event not found"),
    })

    public ResponseEntity<Event> searchByID(
            @ApiParam(value ="The id of Event" , required = true, example = "1")
            @PathVariable long id){

        return serviceCrudResource.searchByID(id).map( event ->
             new ResponseEntity<>(event, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/findAllId")
    // for get more description on swagger
    @ApiOperation("get al events")
    @ApiResponse( code= 200 , message = "OK")
    public ResponseEntity<List<Object[]>> findAllId(){
        return serviceCrudResource.findAllId().map( event ->
                new ResponseEntity<>(event,HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("events/repo/{id}")
    public ResponseEntity<List<Event>> findEventByRepoIDController(@PathVariable long id){
        return serviceCrudResource.findEventByRepoID(id).map(events ->
                new ResponseEntity(events,HttpStatus.OK)).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("events/actor/{id}")
    public ResponseEntity<List<Event>> findEventByActorId(@PathVariable long id) {
        return serviceCrudResource.findEventByActorId(id).map(events ->
                new ResponseEntity<>(events, HttpStatus.OK)
                ).orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("events/repos/{repoId}/actors/{actorId}")
    public ResponseEntity<List<Event>> findEventByRepoIdAndActorId(@PathVariable long repoId,@PathVariable long actorId){
        return serviceCrudResource.findEventByRepoIdAndActorId(repoId,actorId).map(
                events -> new ResponseEntity<>(events,HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("events")
    public ResponseEntity<List<Event>> findAllEvents(){
        return serviceCrudResource.findAllEvents().map( events ->
                new ResponseEntity<>(events,HttpStatus.OK)).orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // deleting options
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> deleteAll() throws CustomException {
         serviceCrudResource.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteByName/{name}")
    public ResponseEntity<Object>deleteByName(@PathVariable String name) throws NotFoundException {
        return new ResponseEntity<> (serviceCrudResource.DeleteByName(name),HttpStatus.OK);
    }

    @DeleteMapping("deleteByEventId/{id}")
    public ResponseEntity<Object> deleteEventById(@PathVariable long id) throws NotFoundException{
         return new ResponseEntity<>( serviceCrudResource.deleteByEventId(id),HttpStatus.OK);
    }

    @GetMapping("testing")
    public List<Event> findTesting(){
      return (List<Event>) serviceCrudResource.noSeQueHace();
    }


}
