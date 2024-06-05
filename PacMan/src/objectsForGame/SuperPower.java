package objectsForGame;

import GuiSides.Rozgrywka;
import toolBox.Map;

import java.util.ArrayList;

public class SuperPower {
    public SuperPower(){

    }

    //stop all Enemy for 10sec
    public void freazer(Map map){
        //work
        map.notFreezed=false;
    }
    //changing diraction for enemy is slow 4-8 times
    public void slowThink(ArrayList<Enemy> enemies){
        //work
        for(Enemy enemy:enemies){
            enemy.setSpeedToChangeDirection(2000);
        }
    }
    //speedUP pacman
    public void speedster(Hero hero){
           hero.setSpeed(350);
    }
    //for x time pacman is invulnerable
    public void sheeldIt(Hero hero){
        //not working
        hero.setCoverToDmg(true);
    }

    //setAllGhosts start position
    public void goHome(ArrayList<Enemy> enemies){
        for(Enemy enemy:enemies){
            enemy.setPosX(enemy.getStartPosX());
            enemy.setPosY(enemy.getStartPosY());
        }
    }

}
