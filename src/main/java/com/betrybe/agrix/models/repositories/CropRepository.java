package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * javadoc.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  @Query(value = "SELECT c.id, c.name, c.planted_area, c.planted_date, c.harvest_date, c.farm_id "
      + "FROM crop c "
      + "WHERE c.harvest_date >= :start AND c.harvest_date <= :end",
      nativeQuery = true)
  List<Crop> findCropsByHarvestDateBetween(LocalDate start, LocalDate end);
}
