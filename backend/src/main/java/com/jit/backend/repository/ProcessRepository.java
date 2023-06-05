package com.jit.backend.repository;

import com.jit.backend.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findByFactoryName(String factoryName);
    List<Process> findByFactoryNameAndProcessName(String factoryName, String processName);
}
