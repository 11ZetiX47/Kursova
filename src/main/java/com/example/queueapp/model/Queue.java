package com.example.queueapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Queue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String ownerPassword;
    private boolean open = true;

    @ElementCollection
    @CollectionTable(name = "queue_participants", joinColumns = @JoinColumn(name = "queue_id"))
    @Column(name = "participant")
    private List<String> participants = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "queue_removed", joinColumns = @JoinColumn(name = "queue_id"))
    @Column(name = "removed_participant")
    private List<String> removedParticipants = new ArrayList<>();
}