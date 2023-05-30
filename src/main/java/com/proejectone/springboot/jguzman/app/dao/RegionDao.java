package com.proejectone.springboot.jguzman.app.dao;

import com.proejectone.springboot.jguzman.app.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionDao extends JpaRepository<Region, Long> {
}
