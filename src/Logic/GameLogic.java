/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import AI.Ai;
import static AI.Ai.FEMALE;
import static AI.Ai.MALE;
import GUI.Canvas;
import static GUI.GUI.taLog;
import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Phteven
 */
public class GameLogic extends Thread{
    private Canvas canvas;
    private ArrayList<Ai> ais = new ArrayList();
    
    public GameLogic(Canvas canvas){
        this.canvas = canvas;
        testdata();
    }
    
    @Override
    public void run(){
        while(true){
            canvas.repaint();  
            try {
                sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void testdata() {
        Ai robert = new Ai(MALE, Color.BLUE, "Robert");
        Ai uschi = new Ai(FEMALE, Color.PINK, "Uschi");
        Ai james = new Ai(MALE, Color.WHITE, "James"); 
        ais.add(robert);       
        ais.add(uschi);
        ais.add(james);
        for(Ai ai:ais){  
            taLog.append(ai.getName() + " was created.\n");
            ai.setAis(ais);
        }
        canvas.setList(ais);        
    }
}
