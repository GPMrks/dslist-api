package com.gpmrks.dslistapi.Repositories;

import com.gpmrks.dslistapi.Entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
