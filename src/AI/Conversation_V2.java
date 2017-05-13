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
import static GUI.GUI.taLog;

/**
 *
 * @author Phteven
 */
public class Conversation_V2 extends Thread{

    //Listen für Phrasen mache von s zu h 
    
    private ArrayList<String>   
            openers = new ArrayList(), 
            agreem = new ArrayList(),
            dissag = new ArrayList(),
            yesnoq = new ArrayList(),
            firstopeners = new ArrayList(), 
            closers = new ArrayList(), 
            
            insults = new ArrayList(), 
            sinsults = new ArrayList(), 
            minsults = new ArrayList(), 
            hinsults = new ArrayList(), 
            
            scompliments = new ArrayList(),
            mcompliments = new ArrayList(),
            hcompliments = new ArrayList(),
            compliments = new ArrayList();
    
    
    private String[] help;
    private Random r = new Random();
    
    private Ai ai, talkAi;
    
    private boolean conv, ynq = false;
    private int ca = 0, ct = 0, len = 0, c=0;
   
    public void startConversation(Ai ai, Ai talkAi, int len){
        this.ai = ai;
        this.talkAi = talkAi;
        this.len = len;
        taLog.append("--------------------------------------------------------------------\n");
        taLog.append(ai.getName()+" and "+talkAi.getName()+ " have started a conversation.\n");
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
                sleep(r.nextInt(2000)+500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Conversation_V2.class.getName()).log(Level.SEVERE, null, ex);
            }   
            talk();       
        }       
    }
    
    
    public void talkNew(){
        if(c%2==0){
            taLog.append(ai.getName() + ": ");
        }
        else{
            taLog.append(talkAi.getName() + ": ");
        }
        c++;
        
        ////////////////////////////////////////////////////////////////////////
        
        if(c<=2){
            //greet
        }
        if(c>len){
            //servas net vergessen conv aus machen
        }
        
        ////////////////////////////////////////////////////////////////////////
        
        
    }
    
    private void decision(){
        
        //nice sein
        
        //neutral
        
        //holy fuck
        
    }
    
    private void nice(){
        //yesno
        //compliments m-h
        //gettin to know shit
    }
    
    private void neutral(){
        //yesno
        //compliments s-m
        //insults s
    }
    
    private void mean(){
        //insults s-h
        //insultqestion
    }
    
    private void writeNew(){
        
    }
    
    public void talk(){
        // nice eigene methoden pls
        if(ca == 0){
            greet(ai);
            ca++;
            return;
        }
        if(ct == 0){
            greet(talkAi);
            ct++;
            return;
        }
        else if (ca == len && ca <= ct){
            bye(ai);
            return;
        }
        else if(ct == len+1){
            bye(talkAi);
            conv = false;
            return;
        }
        else if (ca < len && ca<=ct){
            write(ai);
            return;
        }
        else if (ct < len && ct<=ca){
            write(talkAi);
            return;
        }
    }
    
    public void greet(Ai cur){
        if(ai.getAir().getTalk(talkAi) < 2){
            int z = (r.nextInt(firstopeners.size())+1)-1;
            taLog.append(cur.getName()+": "+firstopeners.get(z)+"\n");
        }
        else{
            int z = (r.nextInt(openers.size())+1)-1;
            taLog.append(cur.getName()+": "+openers.get(z)+"\n");
        }
    }
    
    public void write(Ai cur){
        
        if(cur.equals(ai)){
            ca++;
        }
        
        else{
            ct++;
        }
        
        taLog.append(cur.getName() + ": ");
        //später je nach likey or no likey schreiben
        
        if(ynq){
            
            ynq = false;
            
            if(r.nextBoolean()){
                int z = (r.nextInt(agreem.size())+1)-1;
                taLog.append(agreem.get(z)+"\n");
                return;
            }
            else{
                int z = (r.nextInt(dissag.size())+1)-1;
                taLog.append(dissag.get(z)+"\n");
                return;
            }
        }
        
        int x = r.nextInt(30)+1;
        
        if(x<10){
            int z = (r.nextInt(insults.size())+1)-1;
            taLog.append(insults.get(z)+"\n");
            return;
        }
        else if(x<20){
            int z = (r.nextInt(compliments.size())+1)-1;
            taLog.append(compliments.get(z)+"\n");
            return;
        }
        else if(x<30){
            int z = (r.nextInt(yesnoq.size())+1)-1;
            taLog.append(yesnoq.get(z)+"\n");
            ynq = true;
            return;
        }
        
    }
    
    public void bye(Ai cur){
     
        taLog.append(cur.getName() + ": ");
        
        int z = (r.nextInt(closers.size())+1)-1;
        taLog.append(closers.get(z)+"\n");
        
        ca++;
        ct++;
        
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
                        break;
                    case "-compliments":
                        for (String l : helpList) {
                            compliments.add(l);
                        } 
                        break;
                    case "-agreements":
                        for (String l : helpList) {
                            agreem.add(l);
                        }  
                        break;
                    case "-disagreements":
                        for (String l : helpList) {
                            dissag.add(l);
                        }  
                        break;
                    case "-yesnoq":
                        for (String l : helpList) {
                            yesnoq.add(l);
                        } 
                        break;
                }

                helpList.clear();
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //sout();
    }
    
    public void sout(){
        System.out.println("firstopener");
        for(String s:firstopeners){
            System.out.println(s);
        }
        System.out.println("");
        System.out.println("openers");
        for(String s:openers){
            System.out.println(s);
        }
        System.out.println("");
        System.out.println("yesno");
        for(String s:yesnoq){
            System.out.println(s);
        }
        System.out.println("");
        System.out.println("insults");
        for(String s:insults){
            System.out.println(s);
        }
        System.out.println("");
        System.out.println("compl");
        for(String s:compliments){
            System.out.println(s);
        }
        System.out.println("");
        System.out.println("agree");
        for(String s:agreem){
            System.out.println(s);
        }
        System.out.println("");
        System.out.println("diss");
        for(String s:dissag){
            System.out.println(s);
        }
    }
    
}
