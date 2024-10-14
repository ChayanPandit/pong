import java.awt.*;

public class Text {
    public String text;
    public Font font;
    public double x,y;

    public Text(String text, Font font, double x, double y) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public Text(int text, Font font, double x, double y) {
        this.text = "" + text;
        this.font = font;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(text, (float) x, (float) y);
    }
}
