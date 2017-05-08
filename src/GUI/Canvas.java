/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import AI.Ai;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JPanel;

/**
 *
 * @author Phteven
 */
public class Canvas extends JPanel {

    public static int CW, CH;
    private ArrayList<Ai> ais;

    public void setList(ArrayList ais) {
        this.ais = ais;       
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        CW = getWidth();
        CH = getHeight();

        g.fillRect(0, 0, CW, CH);
        
        for(Ai ai:ais){
            ai.draw(g);
        }
        
    }

}