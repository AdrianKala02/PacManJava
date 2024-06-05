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

        Thread th = new Thread(()->{
            try {
                Thread.sleep(14000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            map.notFreezed=true;
        });
        th.start();
    }
    //changing diraction for enemy is slow 4-8 times
    public void slowThink(ArrayList<Enemy> enemies){
        //work
        for(Enemy enemy:enemies){
            enemy.setSpeedToChangeDirection(2000);
        }
        Thread th = new Thread(()->{
            try {
                Thread.sleep(14000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(Enemy enemy:enemies){
                enemy.setSpeedToChangeDirection(enemy.getIniciatedSpeedToChangeDirection());
            }
        });
        th.start();
    }
    //speedUP pacman
    public void speedster(Hero hero){
           hero.setSpeed(150);

        Thread th = new Thread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hero.setSpeed(hero.getIniciatedSpeed());
        });
        th.start();

    }
    //for x time pacman is invulnerable
    public void sheeldIt(Hero hero){
        //not working
        hero.setCoverToDmg(true);
        Thread th = new Thread(()->{
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            hero.setCoverToDmg(false);
        });
        th.start();
    }

    //setAllGhosts start position
    public void goHome(ArrayList<Enemy> enemies){
        for(Enemy enemy:enemies){
            enemy.setPosX(enemy.getStartPosX());
            enemy.setPosY(enemy.getStartPosY());
        }
    }

}
