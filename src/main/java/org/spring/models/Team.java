package org.spring.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Team name cannot be blank")
    @Column(nullable = false)
    private String name;

    @Min(value = 1, message = "Ranking must be a positive number or zero")
    @Column(nullable = true)
    private int ranking;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
    private List<Player> players;

    @ManyToMany(mappedBy = "teams", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Tournament> tournaments = new HashSet<>();

    public Team() {
    }

    public Team(Long id, String name, int ranking, List<Player> players) {
        this.id = id;
        this.name = name;
        this.ranking = ranking;
        this.players = players;
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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ranking=" + ranking +
                '}';
    }
}
