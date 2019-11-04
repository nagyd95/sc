package gui;

import logic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel {

    private final GameLogic logic;
    private final ActionListener gameButtonActionListener = new GameButtonActionListener();
    private final InformationPanel infoPanel;

    public GamePanel(final GameLogic logic, final InformationPanel infoPanel){
        this.logic = logic;
        this.infoPanel = infoPanel;
        newGame();
    }


    private void setupGamePanel() {
        removeAll();
        int n = logic.getSize();
        setLayout(new GridLayout(n, n));
        for (int row = 0; row < n; ++row) {
            for (int column = 0; column < n; ++column) {
                final JButton btn = new GameButton(row, column);
                btn.setPreferredSize(new Dimension(40, 40));
                btn.addActionListener(gameButtonActionListener);
                add(btn);
            }
        }
    }


    private void refreshUI(){
        for(Component component : getComponents()){
            GameButton btn = (GameButton) component;
            int row = btn.getRow();
            int column = btn.getColumn();
            int fieldValue = logic.getFieldValue(row, column);
            int gamerNumber = logic.getGamerNumber(row,column);
            btn.setText(String.valueOf(fieldValue));
            btn.setBackground(getColorByGamerNumber(gamerNumber, fieldValue));
        }
    }

    private Color getColorByGamerNumber(int gamerNumber, int fieldValue) {
        if (fieldValue == 4) {
            if (gamerNumber == 1)
                return Color.RED;
            if (gamerNumber == 2)
                return Color.GREEN;
        }
        return Color.GRAY;
    }

    public final void newGame(){
        setupGamePanel();
        refreshUI();
    }

    private class GameButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameButton gameButton = (GameButton) e.getSource();
            int row = gameButton.getRow();
            int column = gameButton.getColumn();
            logic.changeFieldValues(row, column);
            infoPanel.setScore_1(logic.getScore_1());
            infoPanel.setScore_2(logic.getScore_2());
            refreshUI();

            checkForEndGame();
            if (logic.isGameEnd()){
                newGame();
                logic.newGame(3);
                infoPanel.newGame();
                refreshUI();
            }

        }
    }


    private void checkForEndGame(){
        if(logic.isGameEnd()){
            int winer = logic.getWiner();
            JOptionPane.showMessageDialog(
                    null,
                    "Jatek vege! Nyert a(z) " + winer +  ". jatekos", "Gratula",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

    }
}

