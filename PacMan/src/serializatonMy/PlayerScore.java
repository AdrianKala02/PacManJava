package serializatonMy;

import java.io.Serializable;

public class PlayerScore implements Serializable,Comparable<PlayerScore>{
    private String nickname;
    private int score;
    private String mapType;
    private String rodzajRozgrywki;
    public String getRodzajRozgrywki() {
        return rodzajRozgrywki;
    }
    public void setRodzajRozgrywki(String rodzajRozgrywki) {this.rodzajRozgrywki = rodzajRozgrywki;}
    public String getMapType() {return mapType;}
    public void setMapType(String mapType) {this.mapType = mapType;}
    public String getNickname() {return nickname;}
    public void setNickname(String nickname) {this.nickname = nickname;}
    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
    public PlayerScore(String nickname, int score, String mapType,String rodzajRozgrywki) {
        this.nickname = nickname;
        this.score = score;
        this.mapType = mapType;
        this.rodzajRozgrywki=rodzajRozgrywki;
    }
    @Override
    public String toString(){
        return  "NICK: "+nickname+" || PUNKTY: "+score+" || MAPTYPE: "+mapType+" || GAMEMODE: "+rodzajRozgrywki;
    }
    @Override
    public int compareTo(PlayerScore other) {
        return Integer.compare(other.score, this.score);
    }
}
