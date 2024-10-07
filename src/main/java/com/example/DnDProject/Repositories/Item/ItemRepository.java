package com.example.DnDProject.Repositories.Item;

import com.example.DnDProject.Entities.Item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,String> {
}
