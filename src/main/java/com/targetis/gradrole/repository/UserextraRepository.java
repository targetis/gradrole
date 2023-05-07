package com.targetis.gradrole.repository;

import com.targetis.gradrole.domain.Userextra;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Userextra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserextraRepository extends JpaRepository<Userextra, Long> {}
