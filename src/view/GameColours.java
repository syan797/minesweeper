package view;

import java.awt.*;

public enum GameColours {

    UnknownCell(new Color(210, 210, 210)),

    KnownCell(new Color(157, 157, 157)),

    MineCell(Color.RED),

    PanelColour(Color.WHITE);

    private final Color colour;

    GameColours(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }

}
