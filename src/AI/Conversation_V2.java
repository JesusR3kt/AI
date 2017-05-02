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
public class Conversation_V2 extends Thread{

    private ArrayList<String> openers = new ArrayList();
    private ArrayList<String> firstopeners = new ArrayList();
    private ArrayList<String> closers = new ArrayList();
    private ArrayList<String> insults = new ArrayList();
    private ArrayList<String> compliments = new ArrayList();
    private String[] help;
    private Random r = new Random();
    
    private Ai ai, talkAi;
    
    private boolean conv;
    private int ca = 0, ct = 0;
    
   
    public void startConversation(Ai ai, Ai talkAi){
        this.ai = ai;
        this.talkAi = talkAi;
        conv = true;
        read();
        set();
        try{
            this.start(); 
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    @Override
    public void run(){
        while(conv){
            try {
                sleep(1000l);
            } catch (InterruptedException ex) {
                Logger.getLogger(Conversation_V2.class.getName()).log(Level.SEVERE, null, ex);
            }   
            talk();       
        }       
    }
    
    public void talk(){
        if(ca == 0){
            System.out.println(ai.getName());
            greet(ai);
            ca++;
            return;
        }
        if(ct == 0){
            System.out.println(talkAi.getName());
            greet(talkAi);
            ct++;
            return;
        }
        else if (ca == 3 && ca <= ct){
            System.out.println("ende ca");
            ca++;
            ct++;
            return;
        }
        else if(ct == 3+1){
            System.out.println("ende ct");
            conv = false;
            return;
        }
        else if (ca < 3 && ca<=ct){
            System.out.println("text ca");
            ca++;
            return;
        }
        else if (ct < 3 && ct<=ca){
            System.out.println("text ct");
            ct++;
            return;
        }
    }
    
    public void greet(Ai cur){
        if(ai.getAir().getTalk(talkAi) < 1){
            int z = r.nextInt(firstopeners.size()-1)+1;
            System.out.println(firstopeners.get(z));
        }
        else{
            int z = r.nextInt(openers.size()-1)+1;
            System.out.println(openers.get(z));
        }
    }
    
    public boolean isConv() {
        return conv;
    }
    
    public void set(){
        talkAi.setConversation(this);
    }
    
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
    
}
