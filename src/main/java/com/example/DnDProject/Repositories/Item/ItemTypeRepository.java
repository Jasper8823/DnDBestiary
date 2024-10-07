package com.example.DnDProject.Repositories.Item;

import com.example.DnDProject.Entities.Item.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTypeRepository extends JpaRepository<ItemType,String> {
}
