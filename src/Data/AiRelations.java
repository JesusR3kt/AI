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
    private List<Ai> ais = new ArrayList();
    private List<Integer> talkList = new ArrayList();

    public void setAis(ArrayList ais) {
        this.ais = ais;

        for (int i = talkList.size(); i < ais.size(); i++) {
            talkList.add(0);
        }

    }

    public void talk(Ai ai) {
        int index = 0;
        for (int i = 0; i < ais.size(); i++) {
            if (ais.get(i).equals(ai)) {
                index = i;
            }
        }
        talkList.add(index, talkList.get(index)+1);
    }
    
    public int getTalk(Ai ai){
        int index = 0;
        for (int i = 0; i< ais.size(); i++){
            index = i;
        }
        return talkList.get(index);     
    }


}
