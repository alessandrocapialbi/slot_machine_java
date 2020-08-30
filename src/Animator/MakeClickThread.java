/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animator;

import Main.MakeClic;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alessandro Capialbi - Alberto Rovai
 */
public class MakeClickThread extends Thread {
    
    private JPanel pn1;
    private MakeClic fc1;

    public MakeClickThread(JPanel pn1, MakeClic fc1) {
        this.pn1 = pn1;
        this.fc1 = fc1;
    }

    public MakeClic getFc1() {
        return fc1;
    }
    
    @Override
    public void run()
    {
        int num = 1;
        for (;;)
        {
                String nomeFileAvatar = "faiclic" + String.valueOf(num) + ".png";
                fc1.changeImage("/Images/" + nomeFileAvatar);
                pn1.repaint();
                if (num == 2) 
                {
                    num = 1;
                } 
                else 
                { 
                    num++;
                }
                try 
                {
                    Thread.sleep(800);
                } 
                catch (InterruptedException ex)
                {
                    Logger.getLogger(Roll1Thread.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        }
    }
   
