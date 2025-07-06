package components;

public enum AnchorPoint {
    TOP_LEFT(0.0, 0.0),

    TOP_CENTER(0.5, 0.0),
    TOP_RIGHT(1.0, 0.0),


    CENTER_LEFT(0.0, 0.5),


    CENTER(0.5, 0.5),


    CENTER_RIGHT(1.0, 0.5),


    BOTTOM_LEFT(0.0, 1.0),


    BOTTOM_CENTER(0.5, 1.0),


    BOTTOM_RIGHT(1.0, 1.0);


    private final double horizontalRatio;


    private final double verticalRatio;

    AnchorPoint(double horizontalRatio, double verticalRatio) {
        this.horizontalRatio = horizontalRatio;
        this.verticalRatio = verticalRatio;
    }

    public double getHorizontalRatio() {
        return horizontalRatio;
    }

    public double getVerticalRatio() {
        return verticalRatio;
    }
}