import view.GameBoardPanel;
import javax.swing.*;
import java.awt.*;

public class MinesweeperApp extends JFrame {

    public MinesweeperApp() {
        GameBoardPanel gameBoardPanel = new GameBoardPanel();
        MainPanel mainPanel = new MainPanel(gameBoardPanel);
        ControlPanel controlPanel = new ControlPanel(mainPanel, this);
        JScrollPane scrollPane = new JScrollPane(mainPanel);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(controlPanel, BorderLayout.NORTH);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        this.setTitle("Sonnie's Minesweeper");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        // This will centre the JFrame on the screen.
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MinesweeperApp app = new MinesweeperApp();
            app.setVisible(true);
        });
    }

}
