package BattleShipGame;

import java.util.*;

public class Ship {
    private int id;
    static int cnt;

    private int hp=3;
    private boolean viability=true;

    private String status;

    private int x;
    private int y;

    private boolean attackedFlag=false;

    public Ship(){
        this.id=++cnt;
        this.status="はずれ！";
    }

    public int getId() {
        return this.id;
    }

    public int getHp() {
        return this.hp;
    }

    public boolean getAttackedFlag(){
        return attackedFlag;
    }
    public void setAttackedFlag(boolean flag){
        attackedFlag=flag;
    }

    private void decreaseHp() {
        this.hp--;
        if(hp<=0){
            this.viability=false;
        }
    }

    public boolean isViability() {
        return viability;
    }

    public void displayViability(){
        if(this.viability){
            System.out.println("船"+this.id+":生きてる");
        }else{
            System.out.println("船"+this.id+":撃沈");
        }
    }

    public void displayStatus(){
        System.out.println("船"+this.id+":"+this.status);
    }

    public void attackedShip() {
        this.decreaseHp();
        this.attackedFlag=true;
        if(this.isViability()){
            this.status="爆弾が当たった！しかし船はまだ沈まない！船は移動します";
        }else{
            this.status="爆弾が当たった！船"+this.id+"は撃沈しました";
        }
    }

    public void rockShip(){
        this.status="波高し！";
    }

    public int getY() {
        return y;
    }
    
    public int getX() {
        return x;
    }

    public void moveShip(Map map){
        Random rand=new Random();
        int x,y;
        do{
            x=rand.nextInt(Map.SIZEX);
            y=rand.nextInt(Map.SIZEY);
        }while(map.getCoodinate(x, y)!=0);
        this.x=x;
        this.y=y;
        map.setShip(this);
    }

    public void resetStatus(){
        if(this.viability){
            this.status="はずれ！";
        }else{
            this.status="船"+this.id+"は沈んでいます";
        }
    }
}