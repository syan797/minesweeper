package view;

import model.Cell;
import model.CellLocation;
import model.GameBoard;

import java.awt.*;

public class GameBoardPainter {

    protected final Graphics2D graphics;
    protected final int cellSize;
    private final GameBoard gameBoard;

    public GameBoardPainter(Graphics2D graphics, int cellSize, GameBoard gameBoard) {
        this.graphics = graphics;
        this.cellSize = cellSize;
        this.gameBoard = gameBoard;
    }

    public void paint() {

        //setting size of text
        Font font = new Font("Segoe UI Emoji", Font.BOLD, 20);
        graphics.setFont(font);

        //paint cells
        for (int col = 0; col < gameBoard.getBoardWidth(); col++) {
            for (int row = 0; row < gameBoard.getBoardHeight(); row++) {

                //Gets the cell in that spot
                Cell cell = gameBoard.getCell(new CellLocation(col, row));

                //Calculates where it's supposed to be and draws the cell
                int cellX = col * cellSize;
                int cellY = row * cellSize;
                graphics.setColor(getCellColour(cell).getColour());
                graphics.fillRect(cellX, cellY, cellSize, cellSize);

                //Draws the number of surrounding mines
                if (cell.isKnown() && (cell.getMineCount()!=0||(cell.isMine()))) {
                    drawInCell(cell, cellX, cellY);
                }
            }
        }
        //paint grid
        graphics.setColor(Color.BLACK);
        paintHorizontalLines();
        paintVerticalLines();

        if (gameBoard.isComplete()) {
            int centreX = gameBoard.getBoardWidth()*cellSize/2;
            int centreY = gameBoard.getBoardHeight()*cellSize/2;
            drawCentredText("Congratulations! You WIN", centreX, centreY);
        }

    }

    private void drawInCell(Cell cell, int cellX, int cellY) {
        String text;
        if (cell.isMine()) {
            //bomb emoji
            text = "\uD83D\uDCA3";
        } else {
            //getting the number of surrounding mines to display
            text = Integer.toString(cell.getMineCount());
        }
        drawCentredText(text, cellX + cellSize/2, cellY + cellSize/2);
    }

    private void drawCentredText(String text, int centreX, int centreY) {
        //calculating position of text
        FontMetrics fm = graphics.getFontMetrics();
        int textX = centreX - fm.stringWidth(text)/2;
        int textY = centreY + (fm.getAscent()-fm.getDescent())/2;

        graphics.setColor(Color.BLACK);
        graphics.drawString(text, textX, textY);
    }

    private void paintHorizontalLines() {
        for (int line = 0; line <= gameBoard.getBoardHeight(); line++) {
            graphics.drawLine(0,line*cellSize, gameBoard.getBoardWidth()*cellSize, line*cellSize);
        }
    }

    private void paintVerticalLines() {
        for (int line = 0; line <= gameBoard.getBoardWidth(); line++) {
            graphics.drawLine(line*cellSize,0,line*cellSize, gameBoard.getBoardHeight()*cellSize);
        }
    }

    private GameColours getCellColour(Cell cell) {
        if (!cell.isKnown()) {
            return GameColours.UnknownCell;
        } else if (cell.isMine()) {
            return GameColours.MineCell;
        } else {
            return GameColours.KnownCell;
        }
    }

}
