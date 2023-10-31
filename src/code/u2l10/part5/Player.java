package src.code.u2l10.part5;

public class Player {

    private int highScore;   // A player's high score
    public Player(int highScore) {
        this.highScore = highScore;
    }
    public int getHighScore() {
        return highScore;
    }
    public void setHighScore(int newHighScore) {
        highScore = newHighScore;
    }
    public String toString(){
        return "High Score: " + highScore;
    }
}