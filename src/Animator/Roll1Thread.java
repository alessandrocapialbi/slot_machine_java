/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animator;

import Main.Roll;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alessandro Capialbi - Alberto Rovai
 */
public class Roll1Thread extends Thread {

    private JPanel pn1;
    private Roll r1;
    private int num;
    private String nomeFileAvatar;

    public Roll1Thread(JPanel pn1, Roll r1, int num)
    {
        this.pn1 = pn1;
        this.r1 = r1;
        this.num = num;
    }

    public String getNomeFileAvatar() {
        return nomeFileAvatar;
    }
    
    

    @Override
    public void run() {
        int random = (int) (40 + Math.random() * 40);
        
        for (int i = 0; i <= random; i++) 
        {
            
            if (i == random) 
            {    
                if (num==17)
                num=0;
                
                if (num % 2 != 0) 
                num++;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Roll1Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            nomeFileAvatar = String.valueOf(num) + ".png";
            r1.changeImage("/Images/" + nomeFileAvatar);
            pn1.repaint();
            if (num == 17) {
                num = 0;
            } else {
                num++;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Roll1Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
