import model.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private MinesweeperApp frame;
    private MainPanel mainPanel;
    private JTextField rowsField;
    private JTextField columnsField;
    private JTextField minesField;
    private JButton restartButton;

    public ControlPanel(MainPanel mainPanel, MinesweeperApp frame) {
        super(new FlowLayout(FlowLayout.CENTER));

        this.frame = frame;
        this.mainPanel = mainPanel;

        setBackground(Color.white);

        GameBoard gameBoard = mainPanel.getGameBoardPanel().getGameBoard();
        JLabel rowsLabel = new JLabel("Rows:");
        rowsField = new JTextField(5);
        rowsField.setText(String.valueOf(gameBoard.getBoardHeight()));
        JLabel columnsLabel = new JLabel("Columns:");
        columnsField = new JTextField(5);
        columnsField.setText(String.valueOf(gameBoard.getBoardWidth()));
        JLabel minesLabel = new JLabel("Mines:");
        minesField = new JTextField(5);
        minesField.setText(String.valueOf(gameBoard.getNumMines()));
        restartButton = new JButton("Restart");

        add(rowsLabel);
        add(rowsField);
        add(columnsLabel);
        add(columnsField);
        add(minesLabel);
        add(minesField);
        add(restartButton);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validSpecs()) {
                    int rows = Integer.parseInt(rowsField.getText());
                    int cols = Integer.parseInt(columnsField.getText());
                    int mines = Integer.parseInt(minesField.getText());
                    mainPanel.newGame(rows, cols, mines);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                }
            }
        });
    }

    private boolean validSpecs() {
        try {
            int rows = Integer.parseInt(rowsField.getText());
            int cols = Integer.parseInt(columnsField.getText());
            int mines = Integer.parseInt(minesField.getText());
            if (rows <= 0 || rows > 100) {
                System.out.println("Invalid number of rows.");
                return false;
            } else if (cols <= 0 || cols > 100) {
                System.out.println("Invalid number of columns.");
                return false;
            } else if (mines <=0 || mines >= rows*cols) {
                System.out.println("Invalid number of mines.");
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return false;
        }
    }

}
