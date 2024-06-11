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
        map.setNotFreezed(false);

        Thread th = new Thread(()->{
            try {
                Thread.sleep(14000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            map.setNotFreezed(true);
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
        // working

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
        // working
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
    public void goHome(ArrayList<Enemy> enemies, Character[][] gritCharMap){
        // working

        for(Enemy enemy:enemies){
            gritCharMap[enemy.getPosY()][enemy.getPosX()]='X';
            enemy.setPosX(enemy.getStartPosX());
            enemy.setPosY(enemy.getStartPosY());
            enemy.setOldPosX(enemy.getStartPosX());
            enemy.setOldPosY(enemy.getStartPosY());
        }
    }

    public void offAll(Map map,ArrayList<Enemy> enemies,Hero hero){
        map.setNotFreezed(true);
        for(Enemy enemy:enemies){
            enemy.setSpeedToChangeDirection(enemy.getIniciatedSpeedToChangeDirection());
        }
        hero.setSpeed(hero.getIniciatedSpeed());
        hero.setCoverToDmg(false);

    }
}
