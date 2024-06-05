package serializatonMy;

import java.io.Serializable;

public class PlayerScore implements Serializable,Comparable<PlayerScore>{
    private String nickname;
    private int score;
    private String mapType;
    public String getMapType() {return mapType;}
    public void setMapType(String mapType) {this.mapType = mapType;}
    public String getNickname() {return nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
    public PlayerScore(String nickname, int score, String mapType) {
        this.nickname = nickname;
        this.score = score;
        this.mapType = mapType;
    }
    @Override
    public String toString(){
        return nickname+" "+score+" "+mapType;
    }
    @Override
    public int compareTo(PlayerScore other) {
        return Integer.compare(other.score, this.score);
    }
}
