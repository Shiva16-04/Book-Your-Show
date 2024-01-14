package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Show;
import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.responseDTO.TheatreResponseShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Integer> {

    Optional<Show> findByCode(String showCode);
    @Query(value = "SELECT MAX(CAST(SUBSTRING(code, 8) AS SIGNED)) FROM shows WHERE SUBSTRING(code, 1, 4) = :year", nativeQuery = true)
    Long findLatestSequenceNumber(@Param("year") String year);
    @Query(value = "SELECT sh.* " +
            "FROM shows sh " +
            "JOIN screen sc ON sh.screen_id = sc.id " +
            "JOIN theatre t ON sc.theatre_id = t.id " +
            "WHERE sc.screen_number = :screenNumber " +
            "AND ((sh.start_time BETWEEN :startTimeRangeStart AND :startTimeRangeEnd) OR (sh.end_time BETWEEN :endTimeRangeStart AND :endTimeRangeEnd)) " +
            "AND t.code = :theatreCode", nativeQuery = true)
    List<Show> findShowsByScreenNumberAndTimeRangeAndTheatreCode(
            @Param("screenNumber") String screenNumber,
            @Param("startTimeRangeStart") LocalDateTime startTimeRangeStart,
            @Param("startTimeRangeEnd") LocalDateTime startTimeRangeEnd,
            @Param("endTimeRangeStart") LocalDateTime endTimeRangeStart,
            @Param("endTimeRangeEnd") LocalDateTime endTimeRangeEnd,
            @Param("theatreCode") String theatreCode);

    @Query(value = "SELECT DISTINCT sh.* "+
            "FROM shows sh " +
            "JOIN movie mve ON sh.movie_id = mve.id " +
            "JOIN movie_languages_released_in mlr ON mve.id = mlr.movie_list_id " +
            "JOIN language lg ON mlr.languages_released_in_id = lg.id " +
            "JOIN movie_format_list mfl ON mve.id = mfl.movie_list_id " +
            "JOIN format fmt ON mfl.format_list_id = fmt.id " +
            "JOIN screen sc ON sh.screen_id = sc.id " +
            "JOIN theatre thr ON sc.theatre_id = thr.id " +
            "JOIN address adr ON thr.id = adr.id " +
            "JOIN show_seat ss ON sh.id = ss.show_id " +
            "WHERE (:city IS NULL OR adr.city = :city) " +
            "AND (:movieCode IS NULL OR mve.code = :movieCode) " +
            "AND (:language IS NULL OR sh.language_enum = :language) " +
            "AND (:format IS NULL OR sh.format_enum = :format) " +
            "AND (:showDate IS NULL OR sh.show_date = :showDate) " +
            "AND ((:startTimeRange IS NULL AND :endTimeRange IS NULL) OR (sh.start_time BETWEEN :startTimeRange AND :endTimeRange)) " +
            "AND (:theatreCode IS NULL OR thr.code = :theatreCode) ", nativeQuery = true)
    List<Show> findFilteredTheatreShowResponseList(
            @Param("city") String city,
            @Param("movieCode") String movieCode,
            @Param("language") String language,
            @Param("format") String format,
            @Param("showDate") LocalDate showDate,
            @Param("startTimeRange") LocalDateTime startTimeRange,
            @Param("endTimeRange")LocalDateTime endTimeRange,
            @Param("theatreCode")String theatreCode);

}
