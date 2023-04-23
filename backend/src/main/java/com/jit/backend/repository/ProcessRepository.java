package com.jit.backend.repository;

import com.jit.backend.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
    Process findByProcessName(String processName);
}
