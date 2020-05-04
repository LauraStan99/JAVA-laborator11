package com.example.lab11;

import com.example.lab11.models.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Players, Long> {

}
