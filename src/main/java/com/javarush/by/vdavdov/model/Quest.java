package com.javarush.by.vdavdov.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "quests")
@AllArgsConstructor
@NoArgsConstructor
public class Quest implements Model {
    @ManyToOne
    @Column(nullable = false, name = "users_id")
    private User author;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "text")
    private String text;
    @Transient
    private List<String> questionsList;

}
