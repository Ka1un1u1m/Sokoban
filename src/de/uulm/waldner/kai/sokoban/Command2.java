package de.uulm.waldner.kai.sokoban;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static de.uulm.waldner.kai.sokoban.Sokoban.*;

public class Command2 implements ActionListener {
    @Override
    public void actionPerformed(@org.jetbrains.annotations.NotNull ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals("QUIT")){
            frame.dispose();
            System.exit(0);
        } else if(actionEvent.getActionCommand().equals("RESTART")){
            reDrawGui(sokoban);
            restartWindow.setVisible(false);
            frame.setTitle("SOKOBAN");
        }
    }
}
