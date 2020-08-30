package GamePanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Animator.JackpotThread;
import Main.AnimationFrame;
import Main.Jackpot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alessandro Capialbi - Alberto Rovai
 */
public class RulesPanel extends JPanel implements ActionListener {

    private Image sfondo;
    private Jackpot j1;
    private JackpotThread tj1;
    private JLabel l1;
    private JTextField t1;
    private JButton b1;
    private JFrame f;
    private String m1 = "src/Music/4.wav";

    public RulesPanel(JFrame f) {
        
        try 
        {
            InputStream test = new FileInputStream(m1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.f=f;
        setDoubleBuffered(true);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        sfondo = toolkit.getImage(getClass().getResource("/Images/rulesbackground.png"));
        j1 = new Jackpot(250, 120, 280, 200, "/Images/jackpot1.png");
        tj1 = new JackpotThread(this, j1);
        tj1.start();
        setLayout(null);
        l1 = new JLabel("Inserisci importo:");
        l1.setFont(new Font("Verdana", Font.BOLD, 14));
        l1.setForeground(Color.white);
        l1.setBounds(10, -30, 200, 100);
        add(l1);
        t1 = new JTextField();
        t1.setBounds(10, 35, 100, 20);
        add(t1);
        b1 = new JButton("Vai!");
        b1.setBounds(125, 35, 70, 20);
        add(b1);
        b1.addActionListener(this);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawImage(sfondo, 0, 0, this.size().width, this.size().height, this);
        g2.drawImage(j1.getImage(), j1.getX(), j1.getY(), j1.getLarghezza(), j1.getAltezza(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(b1)) {
            if (Integer.parseInt(t1.getText()) > 0) {
                AnimationFrame f1 = new AnimationFrame(Integer.parseInt(t1.getText()));
                f1.setVisible(true);
                f1.setResizable(false);
                f.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Inserisci un importo superiore a 0!", "Errore!", JOptionPane.ERROR_MESSAGE);
                t1.setText("");
            }

        }

    }
}
