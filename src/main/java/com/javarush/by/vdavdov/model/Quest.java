package com.javarush.by.vdavdov.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "quests")
@AllArgsConstructor
@NoArgsConstructor
public class Quest implements Model {
    @ManyToOne
    @JoinColumn(nullable = false, name = "users_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return id == quest.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
