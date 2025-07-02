package components;

/**
 * Enum defining anchor points for positioning components relative to their bounds.
 */
public enum AnchorPoint {
    TOP_LEFT(0.0, 0.0),      // Top-left corner
    TOP_CENTER(0.5, 0.0),    // Top center
    TOP_RIGHT(1.0, 0.0),     // Top-right corner
    CENTER_LEFT(0.0, 0.5),   // Left center
    CENTER(0.5, 0.5),        // Center
    CENTER_RIGHT(1.0, 0.5),  // Right center
    BOTTOM_LEFT(0.0, 1.0),   // Bottom-left corner
    BOTTOM_CENTER(0.5, 1.0), // Bottom center
    BOTTOM_RIGHT(1.0, 1.0);  // Bottom-right corner

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