package org.codejudge.sb.dao;

import org.codejudge.sb.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

    @Query(value = "select * from theatre where city = :city", nativeQuery = true)
    List<Theatre> findAllByCity(@Param("city") String city);

    @Query(value = "select * from theatre where theatre_id = :theatreId", nativeQuery = true)
    Theatre findTheatreById(@Param("theatreId") Integer theatreId);

}
