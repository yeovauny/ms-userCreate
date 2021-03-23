package com.hibernate.example.repository;

import com.hibernate.example.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {


    @Query(value = "select eventid , name from Event ")
     Optional<List<Object[]>> myFindEventIds();

    // this search occurs because jpa with the same name can search the information for the @OneTomany relationship
    // learn more of this!!!
   Optional<List<Event>> findByRepo_repoid(long id);

    Optional<List<Event>>  findByActor_actorid(long id);

    // this options is called query methods!!!
    // this search occurs between two class, and searching with it's primary keys...
    Optional<List<Event>> findDistinctByActor_actorid_OrRepo_repoid(long actorid,long repoid);

    List<Event> findEventsByActor_EmptyOrderByName();

    // this search si for finds all events...
    @Query(value="select * from Event order by eventid DESC", nativeQuery = true)
     Optional<List<Event>> findAllEvents();

    //deleting transacctions

    Optional<Event> findByname(String name);

    //void deleteByName(String name);

    @Query(value= "delete from Event b where b.name=:name")
    void deleteEventByName(@Param("name") String name);

}
