package com.gpmrks.dslistapi.Repositories;

import com.gpmrks.dslistapi.Entities.Belonging;
import com.gpmrks.dslistapi.Entities.BelongingId;
import com.gpmrks.dslistapi.Projections.BelongingInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BelongingRepository extends JpaRepository<Belonging, BelongingId> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tb_belonging SET position = :newPosition WHERE game_list_id = :listId AND game_id = :gameId")
    void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);

    @Query(nativeQuery = true, value = """
		SELECT tb_belonging.position, tb_belonging.game_id, tb_belonging.game_list_id
		FROM tb_belonging
		WHERE tb_belonging.game_id = :gameId AND tb_belonging.game_list_id = :listId
			""")
    BelongingInfoProjection getBelongingByGameId(Long gameId, Long listId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM tb_belonging WHERE game_id = :gameId")
    void deleteByGameId(Long gameId);
}
