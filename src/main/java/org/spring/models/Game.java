package org.spring.models;

import org.spring.models.enums.GameDifficulty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "name" , nullable = false)
    private String name ;

    @Column( name = "avgMatchDuration" , nullable = false)
    private double avgMatchDuration;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", nullable = false)
    private GameDifficulty difficulty;

    @OneToMany(mappedBy = "game" , cascade = CascadeType.ALL)
    private List<Tournament> tournaments;

    public Game() {

    }
    public Game(Long id, String name, double avgMatchDuration, GameDifficulty difficulty, List<Tournament> tounaments) {
        this.id = id;
        this.name = name;
        this.avgMatchDuration = avgMatchDuration;
        this.difficulty = difficulty;
        this.tournaments = tounaments;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvgMatchDuration() {
        return avgMatchDuration;
    }

    public void setAvgMatchDuration(double avgMatchDuration) {
        this.avgMatchDuration = avgMatchDuration;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Tournament> getTounaments() {
        return tournaments;
    }

    public void setTounaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avgMatchDuration=" + avgMatchDuration +
                ", difficulty=" + difficulty +
                ", tounaments=" + tournaments +
                '}';
    }
}
