package org.spring.models;

import org.spring.models.enums.TournamentStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int numSpectators;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "ceremony_time")
    private Double ceremonyTime;

    @Column(name = "break_time_between_matches")
    private Double breakTimeBetweenMatches;

    @Column(name = "estimated_duration")
    private Double estimatedDuration;

    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id", nullable= false)
    private Game game;

    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(
            name = "tournament_team",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Set<Team> teams = new HashSet<>();


    public Tournament() {
    }

    public Tournament(Long id, String title, int numSpectators, LocalDate startDate, LocalDate endDate, Double ceremonyTime, Double breakTimeBetweenMatches, Double estimatedDuration, TournamentStatus status, Game game) {
        this.id = id;
        this.title = title;
        this.numSpectators = numSpectators;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ceremonyTime = ceremonyTime;
        this.breakTimeBetweenMatches = breakTimeBetweenMatches;
        this.estimatedDuration = estimatedDuration;
        this.status = status;
        this.game = game;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", numSpectators=" + numSpectators +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", ceremonyTime=" + ceremonyTime +
                ", breakTimeBetweenMatches=" + breakTimeBetweenMatches +
                ", estimatedDuration=" + estimatedDuration +
                ", status=" + status +
                ", game=" + game +
                '}';
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumSpectators() {
        return numSpectators;
    }

    public void setNumSpectators(int numSpectators) {
        this.numSpectators = numSpectators;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getCeremonyTime() {
        return ceremonyTime;
    }

    public void setCeremonyTime(Double ceremonyTime) {
        this.ceremonyTime = ceremonyTime;
    }

    public Double getBreakTimeBetweenMatches() {
        return breakTimeBetweenMatches;
    }

    public void setBreakTimeBetweenMatches(Double breakTimeBetweenMatches) {
        this.breakTimeBetweenMatches = breakTimeBetweenMatches;
    }

    public Double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public TournamentStatus getStatus() {
        return status;
    }

    public void setStatus(TournamentStatus status) {
        this.status = status;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
