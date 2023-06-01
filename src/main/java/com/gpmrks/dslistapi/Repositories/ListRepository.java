package com.gpmrks.dslistapi.Repositories;

import com.gpmrks.dslistapi.Entities.ListOfGames;
import com.gpmrks.dslistapi.Projections.MinimalGameInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<ListOfGames, Long> {

    @Query(nativeQuery = true, value = """
		SELECT tb_game.id, tb_game.title, tb_game.game_year AS gameYear, tb_game.image_url AS imageUrl,
		tb_game.short_description AS shortDescription, tb_belonging.position
		FROM tb_game
		INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
		WHERE tb_belonging.list_id = :listId
		ORDER BY tb_belonging.position
			""")
	java.util.List<MinimalGameInfoProjection> searchByList(Long listId);

}
