package org.spring.models;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String pseudo;

    @Column(nullable = false)
    private int age;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = true )
    private Team team;

    public Player() {
    }

    public Player(Long id, String pseudo, int age, Team team) {
        this.id = id;
        this.pseudo = pseudo;
        this.age = age;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
