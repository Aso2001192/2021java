package BattleShipGame;

public class Map {
    public final static int SIZEX=5;
    public final static int SIZEY=5;

    private int mapData[][]=new int[SIZEX][SIZEY];
    public final static int DEFAULTNUM=0;

    public Map(){
        for(int i=0;i<SIZEX;i++){
            for(int j=0;j<SIZEY;j++){
                mapData[i][j]=DEFAULTNUM;
            }
        }
    }

    public int getCoodinate(int x,int y){
        return mapData[x][y];
    }

    public void setShip(Ship ship){
        mapData[ship.getX()][ship.getY()]=ship.getId();
    }

    public void dropBomb(int x,int y,Ship[] ships){
        if(this.mapData[x][y]!=0){
            ships[this.mapData[x][y]-1].attackedShip();
        }
        int[][] waves={
            {x-1,y},
            {x+1,y},
            {x,y-1},
            {x,y+1}
        };
        boolean[] exist={x-1<0,x+1>Map.SIZEX-1,y-1<0,y+1>Map.SIZEY-1};
        for(int i=0;i<waves.length;i++){
            if(exist[i]){
                continue;
            }
            if(this.mapData[waves[i][0]][waves[i][1]]!=0){
                ships[this.mapData[waves[i][0]][waves[i][1]]-1].rockShip();
            }
        }
        /*
        for(int[] wave:waves){
            if(wave[0]<0||wave[0]>Map.SIZEX-1||wave[1]<0||wave[1]>Map.SIZEY-1){
                continue;
            }
            if(this.mapData[wave[0]][wave[1]]!=0){
                ships[this.mapData[wave[0]][wave[1]]-1].rockShip();
            }
        }*/
    }

    public void resetShip(Ship ship){
        mapData[ship.getX()][ship.getY()]=DEFAULTNUM;
        ship.setAttackedFlag(false);
    }
}
