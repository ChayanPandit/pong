import org.w3c.dom.Text;

public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;

    private double vy = 10.0;
    private double vx = -200.0;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
    }

    public double calculateNewVelocityAngle(Rect paddle) {
        double relativeIntersectY = (paddle.y + (paddle.height / 2)) - (this.rect.y + (this.rect.height / 2));
        double normalIntersectY = relativeIntersectY / (paddle.height / 2);
        double theta = normalIntersectY * Constants.MAX_ANGLE;
        return Math.toRadians(theta);
    }

    public void update(double dt) {
        if (vx < 0) {
            if (this.rect.x <= this.leftPaddle.x + this.leftPaddle.width && this.rect.x >= this.leftPaddle.x && this.rect.y >= this.leftPaddle.y && this.rect.y <= this.leftPaddle.y + this.leftPaddle.height) {
                double theta = calculateNewVelocityAngle(leftPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta) * Constants.BALL_SPEED);

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;

            } else if (this.rect.x + this.leftPaddle.width < this.leftPaddle.x) {
                System.out.println("You lost a point");
            }
        } else if (vx > 0) {
            if (this.rect.x + this.rect.width >= this.rightPaddle.x && this.rect.x <= this.rightPaddle.x && this.rect.y >= this.rightPaddle.y && this.rect.y <= this.rightPaddle.y + this.rightPaddle.height) {
                double theta = calculateNewVelocityAngle(rightPaddle);
                double newVx = Math.abs((Math.cos(theta)) * Constants.BALL_SPEED);
                double newVy = (-Math.sin(theta) * Constants.BALL_SPEED);

                double oldSign = Math.signum(vx);
                this.vx = newVx * (-1.0 * oldSign);
                this.vy = newVy;

            } else if (this.rect.x + this.rect.width > this.rightPaddle.x + this.rightPaddle.width) {
                System.out.println("AI has lost a point");
            }
        }

        if (vy > 0) {
            if (this.rect.y + this.rect.height >= Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM) {
                this.vy *= -1;
            }
        } else if (vy < 0) {
            if (this.rect.y <= Constants.TOOL_BAR_HEIGHT) {
                this.vy *= -1;
            }
        }

        this.rect.x += vx * dt;
        this.rect.y += vy * dt;
    }
}
