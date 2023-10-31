package src.code.u2l10.part5;

/*
 * Represents a player in a game
 */
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
//    public String to.String(){
//        //return super.toString();
//    }
}