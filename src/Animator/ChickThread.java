/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animator;

import Main.Chick;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alessandro Capialbi - Alberto Rovai
 */
public class ChickThread extends Thread {

    private JPanel pn1;
    private Chick c1;
    String nomeFileAvatar;

    public ChickThread(JPanel pn1, Chick c1)
    {
        this.pn1 = pn1;
        this.c1=c1;
    }



    @Override
    public void run() {
        
        int num=0;
        
        for (;;) 
        {
            

            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Roll1Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            nomeFileAvatar = "c"+ String.valueOf(num) + ".png";
            c1.changeImage("/Images/" + nomeFileAvatar);
            pn1.repaint();
            if (num == 21) {
                num = 0;
            } else {
                num++;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Roll1Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
