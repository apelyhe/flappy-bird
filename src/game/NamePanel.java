package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the class for the panel where the player can give his name
 */
public class NamePanel extends BackgroundPanel {
    /**
     * the text field where the user can type his name
     */
    private JTextField jt = new JTextField(25);

    /**
     * the constructor of the class
     * sets the look of the panel
     */
    public NamePanel() {
        this.setLayout(new BorderLayout());
        JLabel jl = new JLabel("What is your name?");
        jl.setFont(new Font("Bodoni MT Black", Font.BOLD, 50));
        this.add(jl, BorderLayout.NORTH);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        textPanel.setOpaque(false);
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(70, 35));
        okButton.addActionListener(new OkButtonActionListener());
        gbc.gridy = 0;
        gbc.gridx = 0;
        textPanel.add(jt);      //jt a jtextfield ahova a nev bekerul, privat adattag az OkAct.Listener miatt
        gbc.gridx = 1;
        textPanel.add(okButton);
        this.add(textPanel);
    }

    /**
     * a class which is used as the action listener of the OK button
     */
    class OkButtonActionListener implements ActionListener {
        /**
         * the method where the player name is being set
         * @param e, action event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Screen.getInstance().setPlayerName(jt.getText());
            Screen.getInstance().switchStartScreen();
        }
    }
}
