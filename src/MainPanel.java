import view.GameBoardPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private GameBoardPanel gameBoardPanel;

    public MainPanel(GameBoardPanel gameBoardPanel) {
        super(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.WHITE);
        this.gameBoardPanel = gameBoardPanel;
        add(gameBoardPanel);
    }

    public void newGame(int rows, int cols, int mines) {
        remove(gameBoardPanel);
        gameBoardPanel = new GameBoardPanel(rows, cols, mines);
        add(gameBoardPanel);
        revalidate();
        repaint();
    }

    public GameBoardPanel getGameBoardPanel() {
        return gameBoardPanel;
    }
}
