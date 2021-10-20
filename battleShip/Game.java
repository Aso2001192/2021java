package BattleShipGame;

import java.util.*;

public class Game {

    final static int SHIPNUM=3;
    private static Ship[] ships=new Ship[SHIPNUM];
    private static Map map=new Map(); 

    static int turn=0;
    
    public Game(){
        displayTitle();
        turn=0;
        for(int i=0;i<SHIPNUM;i++){
            ships[i]=new Ship();
            ships[i].moveShip(map);
        }
    }

    public void start(){
        Scanner sc=new Scanner(System.in);
        int bombX=0;
        int bombY=0;
        while(shipsViability()){
            /*for(int i=0;i<Map.SIZEX;i++){
                for(int j=0;j<Map.SIZEY;j++){
                    System.out.print(map.getCoodinate(i, j));
                }
                System.out.println();
            }*/
            displayTurn();
            for(Ship ship:ships){
                ship.displayViability();
            }
            bombX=inputBombX(sc);
            bombY=inputBombY(sc);
            map.dropBomb(bombX, bombY, ships);
            process();
        }
        displayEnd();
        sc.close();
    }

    private void displayTitle(){
        System.out.println("*************************");
        System.out.println("        戦艦ゲーム        ");
        System.out.println("*************************");
    }

    public void displayTurn(){
        System.out.println("--------[ターン"+(++turn)+"]--------");
    }

    public void displayEnd(){
        System.out.println("あなたは"+turn+"ターンで船を全て沈めました。おめでとう！");
    }

    public int inputBombX(Scanner sc){
        System.out.println("爆弾のX座標を入力してください(1-"+Map.SIZEX+")");
        int x=sc.nextInt();
        while(x<1||Map.SIZEX<x){
            System.out.println("X座標は1-"+Map.SIZEX+"の範囲で入力してください");
            x=sc.nextInt();
        }
        return x-1;
    }

    public int inputBombY(Scanner sc){
        System.out.println("爆弾のY座標を入力してください(1-"+Map.SIZEY+")");
        int y=sc.nextInt();
        while(y<1||Map.SIZEY<y){
            System.out.println("Y座標は1-"+Map.SIZEY+"の範囲で入力してください");
            y=sc.nextInt();
        }
        return y-1;
    }

    public void process(){
        for(Ship ship:ships){
            ship.displayStatus();
            ship.resetStatus();
            if(ship.getAttackedFlag()){
                map.resetShip(ship);
                if(ship.isViability()){
                    ship.moveShip(map);
                }
            }
        }
    }

    public boolean shipsViability(){
        for(Ship ship:ships){
            if(ship.isViability()){
                return true;
            }
        }
        return false;
    }
}
