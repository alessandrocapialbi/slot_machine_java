/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePanel;

import Animator.*;
import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alessandro Capialbi- Alberto Rovai
 */
public class MainPanel extends JPanel implements MouseListener {

    private Image sfondo, rullo;
    private Credit c;
    private String credito;
    private Integer cred, result;
    private Roll r1;
    private Roll r2;
    private Roll r3;
    private MakeClic fc1;
    private Chick c1;
    private Roll1Thread tr1;
    private Roll2Thread tr2;
    private Roll3Thread tr3;
    private MakeClickThread trfc1;
    private ChickThread tc1;
    private SpinThread tg1;
    private Spin g1;
    private ControlThread trc;
    private int num1 = 3;
    private int num2 = 1;
    private int num3 = 5;
    private Boolean count = true;
    private String m1 = "src/Music/1.wav";
    
    public MainPanel(int credit) {

        setDoubleBuffered(true);
        cred = new Integer(credit);
        addMouseListener(this);
        r1 = new Roll(100, 250, 229, 30, "/Images/0.png");
        r2 = new Roll(100, 250, 344, 30, "/Images/0.png");
        r3 = new Roll(100, 250, 459, 30, "/Images/0.png");
        fc1 = new MakeClic(135, 10, 330, 320, "/Images/faiclic2.png");
        c1= new Chick(200, 200, 15, 30, "/Images/c0.png");
        c = new Credit(cred.intValue());
        g1 = new Spin(98, 57, 596, 350, "/Images/butt1.png");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        sfondo = toolkit.getImage(getClass().getResource("/Images/slotmachine3rulli.jpg"));
        trfc1 = new MakeClickThread(this, fc1);
        trfc1.start();
        tc1= new ChickThread(this, c1);
        tc1.start();
    }

    public Credit getC() {
        return c;
    }

    public String getCredito() {
        return credito;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        credito = String.valueOf(c.getCredito());
        if (count == true) 
        {
            c.setEsito("0");
            count = false;
        }
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawImage(sfondo, 0, 0, this.size().width, this.size().height, this);
        g2.drawImage(r1.getImage(), r1.getX(), r1.getY(), r1.getLarghezza(), r1.getAltezza(), this);
        g2.drawImage(r2.getImage(), r2.getX(), r2.getY(), r2.getLarghezza(), r2.getAltezza(), this);
        g2.drawImage(r3.getImage(), r3.getX(), r3.getY(), r3.getLarghezza(), r3.getAltezza(), this);
        g2.drawImage(fc1.getImage(), fc1.getX(), fc1.getY(), fc1.getLarghezza(), fc1.getAltezza(), this);
        g2.drawImage(g1.getImage(), g1.getX(), g1.getY(), g1.getLarghezza(), g1.getAltezza(), this);
        g2.drawImage(c1.getImage(), c1.getX(), c1.getY(), c1.getLarghezza(), c1.getAltezza(), this);
        g2.setFont(new Font("TimesNewRoman", Font.PLAIN, 20));
        g2.drawString(credito, 100, 383);
        if (null == c.getEsito()) {
            g2.setColor(Color.BLACK);
        } 
        else switch (c.getEsito()) 
        {
            case "0":
                g2.setColor(Color.WHITE);
                break;
            case "-50":
                g2.setColor(Color.RED);
                break;
            case "100":
                g2.setColor(Color.GREEN);
                break;
            default:
                g2.setColor(Color.YELLOW);
                break;
        }
        g2.drawString(c.getEsito(), 625, 350);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial Black", Font.BOLD, 20));
        g2.drawString("GIRA", 615, 385);

    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if(c.getCredito()<=0){
            JOptionPane.showMessageDialog(this, "Hai esaurito tutti i soldi!", "Bancarotta!", JOptionPane.ERROR_MESSAGE);
            removeMouseListener(this);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        }
        int x = e.getX();
        int y = e.getY();
        if (x >= 597 && x <= 690 && y >= 352 && y <= 407) {
            tr1 = new Roll1Thread(this, r1, num1);
            tr2 = new Roll2Thread(this, r2, num2);
            tr3 = new Roll3Thread(this, r3, num3);
            trc = new ControlThread(tr1, tr2, tr3, this);
            tr1.setPriority(10);
            tr2.setPriority(10);
            tr3.setPriority(10);
            trc.setPriority(1);
            tg1 = new SpinThread(this, g1);
            tr1.start();
            tr2.start();
            tr3.start();
            trc.start();
            tg1.start();
            trfc1.stop();
            fc1.changeImage("/Images/faiclic2.png");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
