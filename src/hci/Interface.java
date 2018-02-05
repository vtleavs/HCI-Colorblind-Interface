/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 *
 * @author Benji
 */
public class Interface extends javax.swing.JFrame {

    /**
     * Creates new form Interface
     */
    
    String currentStyle = "baseline";
    String currentOrder[] = {"brown", "brown", "brown", "brown", "brown", "brown"};
    String currentColor = "red";
    
    int trials = 0;
    int numTrials = 25;
    
    String outputFile;
    BufferedWriter writer;
    BufferedReader reader;
    
    long startTime;
    
    public Interface() {
        initComponents();
        
        jLabel1.setText("Press Any Square to Start");
        jLabel2.setText("");
        
        jButton1.setIcon(new ImageIcon("images/baseline-" + currentOrder[0] + ".png"));
        jButton2.setIcon(new ImageIcon("images/baseline-" + currentOrder[1] + ".png"));
        jButton3.setIcon(new ImageIcon("images/baseline-" + currentOrder[2] + ".png"));
        jButton4.setIcon(new ImageIcon("images/baseline-" + currentOrder[3] + ".png"));
        jButton5.setIcon(new ImageIcon("images/baseline-" + currentOrder[4] + ".png"));
        jButton6.setIcon(new ImageIcon("images/baseline-" + currentOrder[5] + ".png"));
        
        try {
            File logDir = new File("output");
            if(!logDir.exists())
                logDir.mkdir();
            
            outputFile = "output/output_" + System.currentTimeMillis()/60000;
            
            writer = new BufferedWriter(new FileWriter(outputFile + ".csv", true));
           
            
            writer.append("Interface"
                    + ",Color"
                    + ",Time"
                    + ",Correct\n");
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }
    
    public void buttonPressed(int buttonNum)
    {
        
        long time = System.currentTimeMillis() - startTime;
        
        if(trials > 0)
        {
            try {
                buttonNum--;
           
                if(currentColor.equals(currentOrder[buttonNum]))
                    writer.append(currentStyle + ","
                            + currentColor + ","
                            + time + ","
                            + 1 + "\n");
                else
                    writer.append(currentStyle + ","
                            + currentColor + ","
                            + time + ","
                            + 0 + "\n");


            } catch (IOException ex) {
                System.err.print(ex);
            }
        }
            
        trials++;
        
        if(trials < numTrials)
        {
            jLabel2.setText("Trial " + trials + " / " + numTrials);
            initScene();
            startTime = System.currentTimeMillis();
        }
        else
        {
            try {
                writer.close();
            } catch (IOException ex) {
            }
            this.dispose();
        }
    }
    
    public void end()
    {
        end();
        
        try {
            writer.close();
        } catch (IOException ex) {
        }
        this.dispose();
        
//        try {
//            Scanner input = new Scanner(new File(outputFile + ".csv"));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    private void initScene()
    {
        double style = Math.random()*6;
        
        if(style <= 1)
           currentStyle = "baseline";
        else if(style > 1 && style <= 2)
           currentStyle = "plain";
        else if(style > 2 && style <= 3)
           currentStyle = "h1";
        else if(style > 3 && style <= 4)
           currentStyle = "h2";
        else if(style > 4 && style <= 5)
           currentStyle = "h3";
        else
           currentStyle = "cube";
        
        ArrayList<String> rbg = new ArrayList<>();
        rbg.add("red");
        rbg.add("brown");
        rbg.add("green");
        Collections.shuffle(rbg);
        
        ArrayList<String> bp = new ArrayList<>();
        bp.add("blue");
        bp.add("purple");
        Collections.shuffle(bp);
        
        ArrayList<String> y = new ArrayList<>();
        y.add("yellow");
        
        ArrayList<ArrayList<String>> colors = new ArrayList<>();
        colors.add(rbg);
        colors.add(bp);
        colors.add(y);
        Collections.shuffle(colors);
        
        int numGroups = colors.size();
        int orderCounter = 0;
        for(int i=0; i<numGroups; ++i){
            ArrayList<String> group = colors.get(i);
            
            int numColors = group.size();
            for(int j=0; j<numColors; ++j){
                String color = group.get(j);
                currentOrder[orderCounter] = color;
                orderCounter++;
            }
        }
        
        
        if(currentStyle.equals("baseline"))
        {
            jButton1.setIcon(new ImageIcon("images/baseline-" + currentOrder[0] + ".png"));
            jButton2.setIcon(new ImageIcon("images/baseline-" + currentOrder[1] + ".png"));
            jButton3.setIcon(new ImageIcon("images/baseline-" + currentOrder[2] + ".png"));
            jButton4.setIcon(new ImageIcon("images/baseline-" + currentOrder[3] + ".png"));
            jButton5.setIcon(new ImageIcon("images/baseline-" + currentOrder[4] + ".png"));
            jButton6.setIcon(new ImageIcon("images/baseline-" + currentOrder[5] + ".png"));
        }
        else if(currentStyle.equals("plain"))
        {
            jButton1.setIcon(new ImageIcon("images/plain-" + currentOrder[0] + ".png"));
            jButton2.setIcon(new ImageIcon("images/plain-" + currentOrder[1] + ".png"));
            jButton3.setIcon(new ImageIcon("images/plain-" + currentOrder[2] + ".png"));
            jButton4.setIcon(new ImageIcon("images/plain-" + currentOrder[3] + ".png"));
            jButton5.setIcon(new ImageIcon("images/plain-" + currentOrder[4] + ".png"));
            jButton6.setIcon(new ImageIcon("images/plain-" + currentOrder[5] + ".png"));
        }
        else if(currentStyle.equals("h1"))
        {
            jButton1.setIcon(new ImageIcon("images/h1-" + currentOrder[0] + ".png"));
            jButton2.setIcon(new ImageIcon("images/h1-" + currentOrder[1] + ".png"));
            jButton3.setIcon(new ImageIcon("images/h1-" + currentOrder[2] + ".png"));
            jButton4.setIcon(new ImageIcon("images/h1-" + currentOrder[3] + ".png"));
            jButton5.setIcon(new ImageIcon("images/h1-" + currentOrder[4] + ".png"));
            jButton6.setIcon(new ImageIcon("images/h1-" + currentOrder[5] + ".png"));
        }
        else if(currentStyle.equals("h2"))
        {
            jButton1.setIcon(new ImageIcon("images/h2-" + currentOrder[0] + ".png"));
            jButton2.setIcon(new ImageIcon("images/h2-" + currentOrder[1] + ".png"));
            jButton3.setIcon(new ImageIcon("images/h2-" + currentOrder[2] + ".png"));
            jButton4.setIcon(new ImageIcon("images/h2-" + currentOrder[3] + ".png"));
            jButton5.setIcon(new ImageIcon("images/h2-" + currentOrder[4] + ".png"));
            jButton6.setIcon(new ImageIcon("images/h2-" + currentOrder[5] + ".png"));
        }
        else if(currentStyle.equals("h3"))
        {
            jButton1.setIcon(new ImageIcon("images/h3-" + currentOrder[0] + ".png"));
            jButton2.setIcon(new ImageIcon("images/h3-" + currentOrder[1] + ".png"));
            jButton3.setIcon(new ImageIcon("images/h3-" + currentOrder[2] + ".png"));
            jButton4.setIcon(new ImageIcon("images/h3-" + currentOrder[3] + ".png"));
            jButton5.setIcon(new ImageIcon("images/h3-" + currentOrder[4] + ".png"));
            jButton6.setIcon(new ImageIcon("images/h3-" + currentOrder[5] + ".png"));
        }
        else
        {
            jButton1.setIcon(new ImageIcon("images/cube-" + currentOrder[0] + ".png"));
            jButton2.setIcon(new ImageIcon("images/cube-" + currentOrder[1] + ".png"));
            jButton3.setIcon(new ImageIcon("images/cube-" + currentOrder[2] + ".png"));
            jButton4.setIcon(new ImageIcon("images/cube-" + currentOrder[3] + ".png"));
            jButton5.setIcon(new ImageIcon("images/cube-" + currentOrder[4] + ".png"));
            jButton6.setIcon(new ImageIcon("images/cube-" + currentOrder[5] + ".png"));
        }
        
        double color = Math.random()*6;
        
        if(color <= 1)
        {
            currentColor = "red";
            jLabel1.setText("RED");
        }
        else if(color > 1 && color <= 2)
        {
            currentColor = "brown";
            jLabel1.setText("BROWN");
        }
        else if(color > 2 && color <= 3)
        {
            currentColor = "green";
            jLabel1.setText("GREEN");
        }
        else if(color > 3 && color <= 4)
        {
            currentColor = "blue";
            jLabel1.setText("BLUE");
        }
        else if(color > 4 && color <= 5)
        {
            currentColor = "yellow";
            jLabel1.setText("YELLOW");
        }
        else
        {
            currentColor = "purple";
            jLabel1.setText("PURPLE");
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jButton1.setForeground(new java.awt.Color(240, 240, 240));
        jButton1.setActionCommand("button1");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setActionCommand("button2");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setActionCommand("button3");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setIconTextGap(0);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setActionCommand("button4");
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setActionCommand("button5");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setActionCommand("button6");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 150)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RED");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(216, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(527, 527, 527))))
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addComponent(jLabel1)
                .addGap(117, 117, 117)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(417, 417, 417))
        );

        jButton6.getAccessibleContext().setAccessibleName("button6");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //System.out.println("You pressed button 1");
        buttonPressed(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //System.out.println("You pressed button 2");
        buttonPressed(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //System.out.println("You pressed button 3");
        buttonPressed(3);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //System.out.println("You pressed button 4");
        buttonPressed(4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //System.out.println("You pressed button 5");
        buttonPressed(5);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //System.out.println("You pressed button 6");
        buttonPressed(6);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
