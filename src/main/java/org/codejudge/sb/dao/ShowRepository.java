package org.codejudge.sb.dao;

import org.codejudge.sb.entity.ShowMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<ShowMapping, Integer> {

    @Query(value = "select * from show_mapping where theatre_id = :theatreId", nativeQuery = true)
    List<ShowMapping> findByTheatreId(@Param("theatreId") Integer theatreId);

    @Query(value = "select * from show_mapping where theatre_id = :theatreId and movie_id = :movieId", nativeQuery = true)
    List<ShowMapping> findByMovieIdAndTheatreId(@Param("movieId") Integer movieId, @Param("theatreId") Integer theatreId);
}
