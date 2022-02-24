package Util;

public class Converter {
    public static double encoderToInch(int ticks) {
        return (ticks/359) * Math.PI * 3;
    }
    public static int inchToEncoder(double inch) {
        return (int)(359*(inch/(3*Math.PI)));
    }
}
