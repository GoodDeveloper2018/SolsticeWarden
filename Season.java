public enum Season {
    SPRING(3.03),
    SUMMER(3.04),
    AUTUMN(3.035),
    WINTER(3.01);
    private double Duration;
    Season(double Duration) {
        this.Duration = Duration;
    }
}
