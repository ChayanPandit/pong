import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public KL keyListener;

    public PlayerController(Rect rect, KL keyListener) {
        this.rect = rect;
        this.keyListener = keyListener;
    }

    public void update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)  && (rect.y + Constants.PADDLE_SPEED * dt + rect.height < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM)) {
            rect.y += Constants.PADDLE_SPEED * dt;
        }
        if (keyListener.isKeyPressed(KeyEvent.VK_UP) && (rect.y - Constants.PADDLE_SPEED * dt > Constants.TOOL_BAR_HEIGHT)) {
            rect.y -= Constants.PADDLE_SPEED * dt;
        }
    }
}
