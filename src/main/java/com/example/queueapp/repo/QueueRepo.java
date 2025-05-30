package com.example.queueapp.repo;

import com.example.queueapp.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepo extends JpaRepository<Queue, Long> {
}