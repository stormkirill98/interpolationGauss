package logic;

import java.text.DecimalFormat;

class Utility {
  static final double TOLERANCE = 1.0E-6;

  static String format(Double value){
    DecimalFormat decimalFormat = new DecimalFormat("##.####");
    return decimalFormat.format((value));
  }

  static boolean zero(Double value){
    return Math.abs(value) < TOLERANCE;
  }
}
