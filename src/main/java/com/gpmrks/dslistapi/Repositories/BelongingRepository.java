package com.gpmrks.dslistapi.Repositories;

import com.gpmrks.dslistapi.Entities.Belonging;
import com.gpmrks.dslistapi.Entities.BelongingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BelongingRepository extends JpaRepository<Belonging, BelongingId> {
}
