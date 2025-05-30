package com.example.queueapp.service;

import com.example.queueapp.model.Queue;

import java.util.List;

public interface QueueService {
    List<Queue> findAll();
    Queue findById(Long id);
    Queue save(Queue queue);
    void deleteParticipant(Long queueId, String participant);
    void next(Long queueId);
    void addParticipant(Long queueId, String participant);
}