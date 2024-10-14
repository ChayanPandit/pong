public class Ball {
    public Rect rect;
    public Rect leftPaddle, rightPaddle;
    public Text leftScoreText, rightScoreText;

    private double vy = 10.0;
    private double vx = -200.0;

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
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
                int rightScore = Integer.parseInt(rightScoreText.text);
                rightScore++;
                rightScoreText.text = Integer.toString(rightScore);
                this.rect.x = Constants.SCREEN_WIDTH / 2;
                this.rect.y = Constants.SCREEN_HEIGHT / 2;
                this.vx = -200.0;
                this.vy = 10.0;

                if (rightScore > Constants.WIN_SCORE) {
                    System.out.println("You have lost!");
                    Main.changeState(0);
                }
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
                int leftScore = Integer.parseInt(leftScoreText.text);
                leftScore++;
                leftScoreText.text = Integer.toString(leftScore);
                this.rect.x = Constants.SCREEN_WIDTH / 2;
                this.rect.y = Constants.SCREEN_HEIGHT / 2;
                this.vx = -200.0;
                this.vy = 10.0;

                if (leftScore > Constants.WIN_SCORE) {
//                    System.out.println("You have won!");
                    Main.changeState(0);
                }
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
