import java.io.Serializable;

public class PlayerScore implements Serializable {
    private String nickname;
    private int score;
    private int mapType;
    public int getMapType() {return mapType;}
    public void setMapType(int mapType) {this.mapType = mapType;}
    public String getNickname() {return nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    public PlayerScore(String nickname, int score, int mapType) {
        this.nickname = nickname;
        this.score = score;
        this.mapType = mapType;
    }
    @Override
    public String toString(){

        return nickname+" "+score+" "+mapType;
    }
}
