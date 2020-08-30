/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Animator;

import GamePanel.MainPanel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alessandro Capialbi - Alberto Rovai
 */
public class ControlThread extends Thread {

    private Roll1Thread tr1;
    private Roll2Thread tr2;
    private Roll3Thread tr3;
    private MainPanel mp;
    private int credito;
    private String m2 = "src/Music/2.wav";
    private String m3 = "src/Music/3.wav";

    public ControlThread(Roll1Thread tr1, Roll2Thread tr2, Roll3Thread tr3, MainPanel mp) {
        this.tr1 = tr1;
        this.tr2 = tr2;
        this.tr3 = tr3;
        this.mp = mp;
    }

    @Override
    public void run() {
        //Controllo di vita dei rulli
        while (tr1.isAlive() == true || tr2.isAlive() == true || tr3.isAlive() == true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Esecuzione regole e musica
        if (tr1.getNomeFileAvatar().equals(tr2.getNomeFileAvatar()) && tr2.getNomeFileAvatar().equals(tr3.getNomeFileAvatar())) {

            
            try {
                 mp.getC().jackpot();
                 mp.getC().vincitaJackpot();
                 mp.repaint();
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tr1.getNomeFileAvatar().equals(tr2.getNomeFileAvatar())) {

            mp.getC().dueuguali();
            mp.getC().vincitaDueUguali();
            mp.repaint();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (tr2.getNomeFileAvatar().equals(tr3.getNomeFileAvatar())) {


            try {
                mp.getC().dueuguali();
                mp.getC().vincitaDueUguali();
                mp.repaint();
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (tr1.getNomeFileAvatar().equals(tr3.getNomeFileAvatar())) {

            try {
                mp.getC().dueuguali();
                mp.getC().vincitaDueUguali();
                mp.repaint();
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else 
        {
            mp.getC().gira();
            mp.getC().perdita();
            mp.repaint();
        }
    }

}
