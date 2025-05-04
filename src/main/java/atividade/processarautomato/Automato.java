/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atividade.processarautomato;

import java.util.List;

public class Automato {
    private int initial;
    private List<Integer> _final;
    private List<Transicao> transitions;

    public int getInitial() { 
        return initial; 
    }
    
    public void setInitial(int initial) {
        this.initial = initial; 
    }

    public List<Integer> getFinal() {
        return _final; 
    }
    
    public void setFinal(List<Integer> _final) {
        this._final = _final; 
    }

    public List<Transicao> getTransitions() {
        return transitions; 
    }
    
    public void setTransitions(List<Transicao> transitions) {
        this.transitions = transitions; 
    }
}
