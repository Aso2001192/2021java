package rpgcreature;

import java.util.Random;

/**
 * ゴーレムクラス
 */
public class Golem extends Monster{
    private final static int CRITICAL_HIT_RATE = 5;
    private final static int GOLD=100;
    private final static int DEFENCE=5;

    /**
     * ゴーレムクラスのコンストラクタ
     */
    public Golem(){
        super("ゴーレム",100);
        this.gold=GOLD;
        this.defence=DEFENCE;
    }

    /**
     * 攻撃メソッド
     * @param opponent：攻撃相手
     */
    @Override
    public void attack(Creature opponent) {
        
        Random r = new Random();
        int damage = 0;
        System.out.printf("%sの攻撃！\n",getName());

        //クリティカルヒットかのチェック
        if( r.nextInt(100) < CRITICAL_HIT_RATE ){
            //クリティカルヒット
            damage = 30;
            System.out.printf("%sのクリティカルヒット！\n",getName());
        }else{
            damage = r.nextInt(6)+5;
            
        }
        dealDamage(opponent, damage);        
    }
    
}
