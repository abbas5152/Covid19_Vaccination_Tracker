package com.covid.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.covid.model.Inventory;

@Repository
public interface InventoryDao extends JpaRepository<Inventory,Integer>{

}
