/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package atividade.processarautomato;

/**
 *
 * @author vitor
 */
public class Transicao {
    private int from;
    private int to;
    private String read;

    // Getters e setters
    public int getFrom() { 
        return from; 
    }
    
    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() { 
        return to; 
    }
    
    public void setTo(int to) {
        this.to = to; 
    }

    public String getRead() {
        return read; 
    }
    
    public void setRead(String read) {
        this.read = read; 
    }
}
