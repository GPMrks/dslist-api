package com.gpmrks.dslistapi.Repositories;

import com.gpmrks.dslistapi.Entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Long> {
}
