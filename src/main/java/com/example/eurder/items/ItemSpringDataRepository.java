package com.example.eurder.items;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSpringDataRepository extends JpaRepository<Item, String> {

}
