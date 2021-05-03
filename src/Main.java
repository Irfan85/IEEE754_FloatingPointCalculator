public class Main {
    // ********** SYSTEM CONFIG **********
    private static final int BIT_SYSTEM = 18;
    private static final int EXPONENT_BITS = 5;
    private static final int FRACTION_BITS = 12;
    // ********** SYSTEM CONFIG **********

    private static final int BIAS = (int) Math.pow(2, (EXPONENT_BITS - 1)) - 1;

    public static void main(String[] args) {
        String input = "13.75";
        decToBin(input);
    }

    public static void decToBin(String input) {
        String[] portions = input.split("\\.");

        int signBit = 0;

        String wholePortion;
        if (portions[0].charAt(0) != '-') {
            wholePortion = Integer.toBinaryString(Integer.parseInt(portions[0]));
        } else {
            wholePortion = Integer.toBinaryString(Integer.parseInt(portions[0].substring(1)));
            signBit = 1;
        }

        String fractionPortion = fractionToBinary(portions[1]);
        String combinedFraction = wholePortion.concat(fractionPortion).substring(1, FRACTION_BITS + 1);

        int exponent, quotient;
        String exponentSign = "+";

        if (!wholePortion.equals("0")) {
            exponent = wholePortion.length() - 1;
            quotient = exponent + BIAS;
        } else {
            int firstOnePos = 0;
            while (combinedFraction.charAt(firstOnePos) != '1') {
                firstOnePos++;
            }

            exponent = firstOnePos + 1;
            quotient = BIAS - exponent;
            exponentSign = "-";

            combinedFraction = combinedFraction.substring(exponent);
        }

        while (combinedFraction.length() < FRACTION_BITS) {
            combinedFraction = combinedFraction.concat("0");
        }

        String exponentPortion = Integer.toBinaryString(Math.abs(quotient));

        while (exponentPortion.length() < EXPONENT_BITS) {
            exponentPortion = "0".concat(exponentPortion);
        }

        System.out.println("Exponent Notation:");
        System.out.println("1." + combinedFraction + "*2^" + exponentSign + exponent);

        System.out.println("Full Binary Notation:");
        System.out.println(signBit + " " + exponentPortion + " " + combinedFraction);

        String binString = String.valueOf(signBit).concat(exponentPortion).concat(combinedFraction);

        System.out.println("Hex Notation:");
        System.out.println(floatingPointBinToHex(binString));
    }

    public static String fractionToBinary(String fractionPart) {
        String result = "";

        for (int i = 0; i < FRACTION_BITS; i++) {
            float fraction = 2 * Float.parseFloat("0." + fractionPart);

            String[] portions = String.valueOf(fraction).split("\\.");
            result = result.concat(portions[0]);
            fractionPart = portions[1];
        }

        return result;
    }

    public static String floatingPointBinToHex(String floatingPointBin) {
        String result = "";

        if ((BIT_SYSTEM % 4) != 0) {
            while ((floatingPointBin.length() % 4) != 0) {
                floatingPointBin = floatingPointBin.concat("0");
            }
        }

        for (int i = 0; i < BIT_SYSTEM; i += 4) {
            String hexDigit = floatingPointBin.substring(i, i + 4);

            int hexDigitValue = Integer.parseInt(hexDigit.charAt(0) + "") * 8 +
                    Integer.parseInt(hexDigit.charAt(1) + "") * 4 +
                    Integer.parseInt(hexDigit.charAt(2) + "") * 2 +
                    Integer.parseInt(hexDigit.charAt(3) + "");

            switch (hexDigitValue) {
                case 10:
                    result = result.concat("A");
                    break;
                case 11:
                    result = result.concat("B");
                    break;
                case 12:
                    result = result.concat("C");
                    break;
                case 13:
                    result = result.concat("D");
                    break;
                case 14:
                    result = result.concat("E");
                    break;
                case 15:
                    result = result.concat("F");
                    break;
                default:
                    result = result.concat(String.valueOf(hexDigitValue));
            }
        }

        return result;
    }
}
