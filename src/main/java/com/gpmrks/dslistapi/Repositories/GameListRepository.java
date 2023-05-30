package com.gpmrks.dslistapi.Repositories;

import com.gpmrks.dslistapi.Entities.GameList;
import com.gpmrks.dslistapi.Projections.MinimalGameInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Long> {

    @Query(nativeQuery = true, value = """
		SELECT tb_game.id, tb_game.title, tb_game.game_year AS `year`, tb_game.image_url AS imageUrl,
		tb_game.short_description AS shortDescription, tb_belonging.position
		FROM tb_game
		INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
		WHERE tb_belonging.game_list_id = :listId
		ORDER BY tb_belonging.position
			""")
	List<MinimalGameInfoProjection> searchByList(Long listId);

}
