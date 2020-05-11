package org.codejudge.sb.dao;


import org.codejudge.sb.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findAll();

    @Query(value = "select * from movie where movie_id = :movieId", nativeQuery = true)
    Movie findMovieById(@Param("movieId") Integer movieId);

}
