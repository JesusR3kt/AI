/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phteven
 */
public class Conversation extends Thread {

    private ArrayList<String> openers = new ArrayList();
    private ArrayList<String> firstopeners = new ArrayList();
    private ArrayList<String> closers = new ArrayList();
    private ArrayList<String> insults = new ArrayList();
    private ArrayList<String> compliments = new ArrayList();
    private String[] help;
    private Random r = new Random();

    private Ai ai;
    private Ai talkAi;
    private Conversation aicon;
    private boolean talking = false;
    private boolean conv = false;
    
    private int counter;
    private int durration;

    public void read() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/Data/sentences.txt"));
            ArrayList<String> helpList = new ArrayList();
            for (String line : lines) {
                help = line.split(";");
                for (int i = 1; i < help.length; i++) {
                    helpList.add(help[i]);
                }

                switch (help[0]) {
                    case "-openers":
                        for (String l : helpList) {
                            openers.add(l);
                        }
                        break;
                    case "-firstopeners":
                        for (String l : helpList) {
                            firstopeners.add(l);
                        }
                        break;
                    case "-closers":
                        for (String l : helpList) {
                            closers.add(l);                            
                        }
                        break;
                    case "-insults":
                        for (String l : helpList) {
                            insults.add(l);
                        }
                    case "-compliments":
                        for (String l : helpList) {
                            compliments.add(l);
                        }    
                }

                helpList.clear();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void request(Ai talkAi, Ai ai, int durration) {
        this.ai = ai;
        this.talkAi = talkAi;
        this.durration = durration;
        counter = 0;
        conv = true;
        //aicon = talkAi.getConversation();
        read();
        //this.run();
    }

    public void startConv(Ai ai, Ai talkAi) {
        this.ai = ai;
        this.talkAi = talkAi;
        counter = 0;
        durration = r.nextInt(20)+3;
        talking = true;
        conv = true;
        //aicon = talkAi.getConversation();
        aicon.request(ai, talkAi, durration);
        read();
        //this.run();
    }

    @Override
    public void run() {
        while (talking) {   
            // alles in eigene Methoden boi
            System.out.println("------------\n" + ai.getName() + "\n");            
            if (counter == 0) {
                if (ai.getAir().getTalk(talkAi) < 1) {
                    int z = r.nextInt(firstopeners.size()-1) + 1;
                    System.out.println(firstopeners.get(z));
                } else {
                    int z = r.nextInt(openers.size()-1) + 1;
                    System.out.println(openers.get(z));
                }
            }
            
            else if(counter == durration){
                System.out.println(closers.size());
                conv = false;
            }
            
            //frage u antwort
             
            else{
               //später ob man sich mag oda net insulten bzw compliment               
               int z = r.nextInt(compliments.size()-1) + 1;
               System.out.println(compliments.get(z));
            }
            
            int x = r.nextInt(2000)+1;  
            try {
                sleep(x);
            } catch (InterruptedException ex) {
                Logger.getLogger(Conversation.class.getName()).log(Level.SEVERE, null, ex);
            }
            talking = false;           
            aicon.setTalking(true);
            counter++;
            aicon.run();
        }
    }

    public void setTalking(boolean talking) {
        this.talking = talking;
    }
    
    public boolean isConv() {
        return conv;
    }

}
