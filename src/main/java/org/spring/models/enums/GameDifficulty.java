package org.spring.models.enums;

public enum GameDifficulty {
    EASY(1.0),
    MEDIUM(1.5),
    HARD(2.0),
    EXPERT(2.5);

    private final double multiplier;

    GameDifficulty(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
