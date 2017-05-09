/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import AI.Ai;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author steven
 */
public class AiRelations {

    //talk counter mit jeder ai
    //Sympatie mit jeder ai, standart(neutral)500, max 1000
    private List<Ai> ais = new ArrayList();
    private List<Integer> talkList = new ArrayList();
    private List<Integer> sympList = new ArrayList();
    private List<Integer> knowList = new ArrayList();
    
    public void setAis(ArrayList ais) {
        this.ais = ais;

        for (int i = talkList.size(); i < ais.size(); i++) {
            talkList.add(0);
            sympList.add(500);
        }

    }

    public void talk(Ai ai) {      
        talkList.add(searchIndex(ai), talkList.get(searchIndex(ai))+1);
    }
    
    public void plusSymp(Ai ai, int i){
        sympList.add(searchIndex(ai), sympList.get(searchIndex(ai))+i);
    }
    
    public int getTalk(Ai ai){
        return talkList.get(searchIndex(ai));     
    }
    
    public int getSymp(Ai ai){
        return sympList.get(searchIndex(ai));
    }

    private int searchIndex(Ai ai){
        for(int i = 0; i < ais.size(); i++){
            if(ais.get(i).equals(ai)){
                return i;
            }
        }
        return 0;
    }
    
}
