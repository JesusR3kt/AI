/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import static GUI.Canvas.CH;
import static GUI.Canvas.CW;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 *
 * @author Phteven
 */
public class Movement extends Thread {

    private int x, y;
    private int durration;
    private int speed;
    private Ai ai;
    private boolean running = false;
    private Random r = new Random();
    private String dir;
    private ArrayList<Ai> ais;
    private int ccount = 1;

    public void move(Ai ai, ArrayList ais, int durration, int speed) {
        this.durration = durration;
        this.speed = speed;
        this.ai = ai;
        this.ais = ais;
        x = ai.getX();
        y = ai.getY();
        direction();
        running = true;
        task();

    }
    
    private void right(){
        if(x+ai.getWidth()<CW){
            x+=speed;
        }        
    }
    
    private void left(){
        if(x-(ai.getWidth()/2)>0){
            x-=speed;
        }
    }
    
    private void up(){
        if(y-(ai.getWidth()/2)>0){
            y-=speed;
        }
    }
    
    private void down(){
        if(y+ai.getWidth()<CH){
            y+=speed;
        }
    }
    
    private void towardsAi(){
        int ax = 0, ay = 0, ent = CW+CH;
        for(Ai ai:ais){
            if(Math.abs(ai.getX()+ai.getY()-x-y)<ent&&(x!=ai.getX()||y!=ai.getY())){
                ent = ai.getX()+ai.getY()-x-y;
                ax = ai.getX();
                ay = ai.getY();
            }
        }

        if(x<=ax){
            x++;
        }
        else if(x>ax){
            x--;
        }
        
        if(y<=ay){
            y++;
        }
        else if(y>ay){
            y--;
        }
        
    }
   
      
    private void direction(){
        int i = r.nextInt(100)+1;
        
        if(i<10){
            dir = "l";
        }
        
        else if(i<20){
            dir = "r";
        }
        
        else if(i<30){
            dir = "u";
        }
        
        else if(i<40){
            dir = "d";
        }
        
        else if(i<50){
            dir = "dr";
        }
        
        else if(i<60){
            dir = "dl";
        }
        
        else if(i<70){
            dir = "ur";
        }
        
        else if(i<80){
            dir = "ul";
        }
        
        else if(i<90){
            dir = "n";
        }
        
        else if(i<100){
            dir = "ta";
        }
    }
    
    private void getDir(){
        switch(dir){
            case "l":   left(); break;
            case "r":   right(); break;
            case "u":   up(); break;
            case "d":   down(); break;
            case "ul":  up(); left(); break;
            case "ur":  up(); right(); break;
            case "dl":  down(); left(); break;
            case "dr":  down(); right(); break;
            case "ta":  towardsAi(); break;
        }
    }

    private void task() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable t = new Runnable() {
            @Override
            public void run() {
                getDir();
                
            }
        };

        final ScheduledFuture<?> handler
                = scheduler.scheduleAtFixedRate(t, 100, 50, MILLISECONDS);
        scheduler.schedule(new Runnable() {
            public void run() {
                handler.cancel(true);
                running = false;
                
            }
        }, durration, MILLISECONDS);

        

    }
        

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isRunning() {
        return running;
    }

}
