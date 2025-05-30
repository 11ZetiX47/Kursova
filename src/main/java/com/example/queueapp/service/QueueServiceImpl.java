package com.example.queueapp.service;

import com.example.queueapp.model.Queue;
import com.example.queueapp.repo.QueueRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueServiceImpl implements QueueService {

    private final QueueRepo queueRepo;

    public QueueServiceImpl(QueueRepo queueRepo) {
        this.queueRepo = queueRepo;
    }

    @Override
    public List<Queue> findAll() {
        return queueRepo.findAll();
    }

    @Override
    public Queue findById(Long id) {
        return queueRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Не знайдено чергу: " + id));
    }

    @Override
    public Queue save(Queue queue) {
        return queueRepo.save(queue);
    }

    @Override
    public void deleteParticipant(Long queueId, String participant) {
        Queue q = findById(queueId);
        if (q.getParticipants().remove(participant)) {
            q.getRemovedParticipants().add(participant);
        }
        queueRepo.save(q);
    }

    @Override
    public void next(Long queueId) {
        Queue q = findById(queueId);
        if (!q.getParticipants().isEmpty()) {
            String first = q.getParticipants().remove(0);
            q.getRemovedParticipants().add(first);
            queueRepo.save(q);
        }
    }

    @Override
    public void addParticipant(Long queueId, String participant) {
        Queue q = findById(queueId);
        q.getParticipants().add(participant);
        queueRepo.save(q);
    }
}