package com.url.shortener.repository;

import com.url.shortener.models.ClickEvent;
import com.url.shortener.models.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent, Long> {

   // existing methods (rehne do)
   List<ClickEvent> findByUrlMappingAndClickDateBetween(
           UrlMapping mapping,
           LocalDateTime startDate,
           LocalDateTime endDate
   );

   List<ClickEvent> findByUrlMappingInAndClickDateBetween(
           List<UrlMapping> urlMappings,
           LocalDateTime startDate,
           LocalDateTime endDate
   );

   // 🔥 NEW METHOD FOR GRAPH
   @Query("""
        SELECT DATE(c.clickDate), COUNT(c)
        FROM ClickEvent c
        WHERE c.urlMapping.shortCode = :shortCode
        AND c.clickDate BETWEEN :start AND :end
        GROUP BY DATE(c.clickDate)
        ORDER BY DATE(c.clickDate)
    """)
   List<Object[]> getClicksGroupedByDate(
           @Param("shortCode") String shortCode,
           @Param("start") LocalDateTime start,
           @Param("end") LocalDateTime end
   );
}
