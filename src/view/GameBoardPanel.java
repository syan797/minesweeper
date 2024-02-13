package view;

import model.Cell;
import model.CellLocation;
import model.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoardPanel extends JPanel {

    private GameBoard gameBoard;
    private int borderSize = 10;
    private int cellSize;
    private static final int PREFERRED_CELL_SIZE = 30;
    private static final int DEFAULT_WIDTH = 8;
    private static final int DEFAULT_HEIGHT = 10;
    private static final int DEFAULT_MINES = 24;

    public GameBoardPanel() {
        this(DEFAULT_HEIGHT,DEFAULT_WIDTH,DEFAULT_MINES);
    }

    public GameBoardPanel(int rows, int cols, int mines) {
        this.gameBoard = new GameBoard(cols,rows,mines);
        cellSize = PREFERRED_CELL_SIZE;

        this.addMouseListener(mouseListener);

        this.setBackground(GameColours.PanelColour.getColour());

        this.setPreferredSize(new Dimension(cellSize*gameBoard.getBoardWidth() + borderSize *2,
                cellSize* gameBoard.getBoardHeight() + borderSize *2));
        //can set max and min size later - mayb not necessary
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Create a copy of the graphics object which we'll use here, so we can mess with its properties
        // (such as Colors) without worrying about messing up the rendering of any other components.
        Graphics2D graphics = (Graphics2D) g.create();

        //offset
        graphics.translate(borderSize, borderSize);

        //ok continue here later
        GameBoardPainter boardPainter = new GameBoardPainter(graphics,cellSize,gameBoard);
        boardPainter.paint();

    }

    private final MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (!gameBoard.isDead()) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    //get the cell that the user clicked on:
                    CellLocation cellLocation = getCellLocation(e.getPoint());
                    if (cellLocation != null) {
                        Cell chosenCell = gameBoard.getCell(cellLocation);
                        if (chosenCell.isMine()) {
                            gameBoard.gameOver();
                            repaint();
                        } else if (!chosenCell.isKnown()) {
                            chosenCell.reveal();
                            repaint();
                        }
                    }
                }
            }
        }
    };

    public CellLocation getCellLocation(Point point) {
        //if clicked in border -- return null
        if (!withinBoard(point)) {
            return null;
        }

        //get coordinates relative to board
        int xOnBoard = point.x - borderSize;
        int yOnBoard = point.y - borderSize;

        //Returns null if clicked on a line
        if (xOnBoard%cellSize==0 || yOnBoard%cellSize==0) {
            return null;
        }

        //If click is valid (within a cell), gets the coordinates of that cell
        return new CellLocation(xOnBoard/cellSize, yOnBoard/cellSize);
    }

    //Checks if point given is within the game board
    private boolean withinBoard(Point point) {
        if (point.x <= borderSize || point.x >= gameBoard.getBoardWidth()*cellSize+borderSize) {
            return false;
        } else return (point.y > borderSize && point.y < gameBoard.getBoardHeight()*cellSize + borderSize);
    }

    public void setBorderSize(int borderSize) {
        this.borderSize = borderSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
