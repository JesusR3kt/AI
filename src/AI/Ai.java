/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Data.AiRelations;
import static GUI.GUI.HE;
import static GUI.GUI.WI;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Phteven
 */
public class Ai {

    public static int MALE = 0, FEMALE = 1;
    private Ai bestfriend = null;
    private Ai talkPartner = null;

    private ArrayList<Ai> ais = new ArrayList();
    private Ai nearest = null;

    private Color c = null;
    private int gender = 0;
    private String name = "";
    private int x = 0, y = 0;
    private int width = 10;
    private Random r = new Random();
    private int durration;
    private int speed;
    private int cooldown = 0;

    private Conversation_V2 conversation = new Conversation_V2();
    //private Conversation conversation = new Conversation();
    private AiRelations air = new AiRelations();

    public AiRelations getAir() {
        return air;
    }
    private Movement movement = new Movement();

    public Ai(int gender, Color c, String name) {
        this.gender = gender;
        this.c = c;
        this.name = name;
        startPos();
    }

    public String getName() {
        return name;
    }

    private void startPos() {
        x = r.nextInt(WI - width) + 1;
        y = r.nextInt(HE - width) + 1;
    }

    public int getGender() {
        return gender;
    }

    public Ai getBestfriend() {
        return bestfriend;
    }

    public void setBestfriend(Ai bestfriend) {
        this.bestfriend = bestfriend;
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public Conversation_V2 getConversation() {
        return conversation;
    }

    public void setConversation(Conversation_V2 conversation) {
        this.conversation = conversation;
    }

    private void conv() {
        //conversation.startConv(this ,ais.get(0));
        if (cooldown < 1) {
            air.talk(nearest);
            cooldown = 3000;
            nearest.setCooldown(cooldown);
            System.out.println(name);
            conversation = new Conversation_V2();
            conversation.startConversation(this, nearest);
        } else {
            cooldown--;
        }
        //nearest.getConversation().startConversation();
    }

    private void move() {
        if (!movement.isRunning()) {
            durration = r.nextInt(2000) + 250;
            speed = r.nextInt(3) + 1;
            movement.move(this, ais, durration, speed);
        } else {
            x = movement.getX();
            y = movement.getY();
        }

    }

    private void calcNearestAi() {
        for (Ai ai : ais) {
            if (nearest == null && !ai.equals(this)) {
                nearest = ai;
            }
            if (Math.abs(ai.getX() + ai.getY() - x - y) < Math.abs(nearest.getX() + nearest.getY() - x - y)) {
                nearest = ai;
            }
        }
    }

    public void draw(Graphics g) {
        if (!conversation.isConv()) {
            move();
        }
        calcNearestAi();
        if (Math.abs(nearest.getX() - x) < 50 && Math.abs(nearest.getY() - y) < 50 && !conversation.isConv() && !nearest.getConversation().isConv()) {
            conv();
        }
        g.setColor(c);
        g.fillRect(x, y, width, width);
        g.drawString(name, x + width + 5, y + width);
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setAis(ArrayList<Ai> aisSource) {
        for (Ai ai : aisSource) {
            ais.add(ai);
        }
        ais.remove(this);
        air.setAis(ais);
        air.talk(ais.get(0));
    }

}
