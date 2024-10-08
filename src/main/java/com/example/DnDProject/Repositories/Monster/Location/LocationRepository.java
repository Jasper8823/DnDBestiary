package com.example.DnDProject.Repositories.Monster.Location;

import com.example.DnDProject.Entities.Monster.Location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,String> {
}
