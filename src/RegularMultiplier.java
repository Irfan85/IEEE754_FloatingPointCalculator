public class RegularMultiplier {

    private static final int BIT_SYSTEM = 10;

    public static void main(String[] args) {
        // *** REMEMBER TO FORMAT THE MULTIPLICAND IN BIT_SYSTEM AND BIT_SYSTEM * 2 BIT FORMAT ***
        multiply("0000010001", "00000000000000010010");
    }

    public static void multiply(String multiplier, String multiplicand) {
        int[] multiplierRegister = new int[BIT_SYSTEM];
        for (int i = 0; i < multiplierRegister.length; i++) {
            multiplierRegister[i] = Integer.parseInt(multiplier.charAt(i) + "");
        }

        int[] multiplicandRegister = new int[BIT_SYSTEM * 2];
        for (int i = 0; i < multiplicandRegister.length; i++) {
            multiplicandRegister[i] = Integer.parseInt(multiplicand.charAt(i) + "");
        }

        int[] productRegister = new int[BIT_SYSTEM * 2];

        System.out.println("Iteration\tMultiplier\tMultiplicand\tProduct");
        System.out.println("0:\t\t\t\t" + registerArrayToString(multiplierRegister) + "\t" + registerArrayToString(multiplicandRegister) + "\t" + registerArrayToString(productRegister));
        System.out.println("__________________________________________________");

        for (int i = 0; i < multiplierRegister.length; i++) {
            if (multiplierRegister[multiplierRegister.length - 1] == 1) {
                // Add multiplicand to product
                productRegister = addBin(multiplicandRegister, productRegister);
            }

            System.out.println((i + 1) + ":\t\t\t\t" + registerArrayToString(multiplierRegister) + "\t" + registerArrayToString(multiplicandRegister) + "\t" + registerArrayToString(productRegister));

            // Multiplicand shift left
            for (int j = 0; j < multiplicandRegister.length; j++) {
                if (j != multiplicandRegister.length - 1) {
                    multiplicandRegister[j] = multiplicandRegister[j + 1];
                } else {
                    multiplicandRegister[j] = 0;
                }
            }

            System.out.println(" :\t\t\t\t" + registerArrayToString(multiplierRegister) + "\t" + registerArrayToString(multiplicandRegister) + "\t" + registerArrayToString(productRegister));

            // Multiplier shift right
            for (int j = multiplierRegister.length - 1; j >= 0; j--) {
                if (j != 0) {
                    multiplierRegister[j] = multiplierRegister[j - 1];
                } else {
                    multiplierRegister[j] = 0;
                }
            }

            System.out.println(" :\t\t\t\t" + registerArrayToString(multiplierRegister) + "\t" + registerArrayToString(multiplicandRegister) + "\t" + registerArrayToString(productRegister));
            System.out.println("__________________________________________________");
        }
    }

    public static void signedMultiply(String multiplier, String multiplicand) {
        int[] multiplierRegister = new int[BIT_SYSTEM];
        for (int i = 0; i < multiplierRegister.length; i++) {
            multiplierRegister[i] = Integer.parseInt(multiplier.charAt(i) + "");
        }

        int[] multiplicandRegister = new int[BIT_SYSTEM * 2];
        for (int i = 0; i < multiplicandRegister.length; i++) {
            multiplicandRegister[i] = Integer.parseInt(multiplicand.charAt(i) + "");
        }

        int[] productRegister = new int[BIT_SYSTEM * 2];

        System.out.println("Iteration\tMultiplier\tMultiplicand\tProduct");
        System.out.println("0:\t\t\t\t" + registerArrayToString(multiplierRegister) + "\t" + registerArrayToString(multiplicandRegister) + "\t" + registerArrayToString(productRegister));

        for (int i = 0; i < multiplierRegister.length - 1; i++) {
            if (multiplierRegister[multiplierRegister.length - 1] == 1) {
                // Add multiplicand to product
                productRegister = addBin(multiplicandRegister, productRegister);
            }

            // Multiplier shift right
            for (int j = multiplierRegister.length - 1; j >= 0; j--) {
                if (j != 0) {
                    multiplierRegister[j] = multiplierRegister[j - 1];
                } else {
                    multiplierRegister[j] = 0;
                }
            }

            // Multiplicand shift left
            for (int j = 0; j < multiplicandRegister.length; j++) {
                if (j != multiplicandRegister.length - 1) {
                    multiplicandRegister[j] = multiplicandRegister[j + 1];
                } else {
                    multiplicandRegister[j] = 0;
                }
            }


            System.out.println((i + 1) + ":\t\t\t\t" + registerArrayToString(multiplierRegister) + "\t" + registerArrayToString(multiplicandRegister) + "\t" + registerArrayToString(productRegister));
        }
    }

    public static int[] addBin(int[] bin1, int[] bin2) {
        int[] result = new int[bin1.length];
        int carry = 0;

        for (int i = bin1.length - 1; i >= 0; i--) {
            int[] bitRes = bitAdder(bin1[i], bin2[i], carry);
            result[i] = bitRes[0];
            carry = bitRes[1];
        }

        return result;
    }

    public static int[] bitAdder(int bit1, int bit2, int carry) {
        int[] result = new int[2];

        if (bit1 == 0 && bit2 == 0 && carry == 0) {
            return result;
        } else if ((bit1 == 0 && bit2 == 0 && carry == 1) || (bit1 == 0 && bit2 == 1 && carry == 0) || (bit1 == 1 && bit2 == 0 && carry == 0)) {
            result[0] = 1;
            return result;
        }else if ((bit1 == 0 && bit2 == 1 && carry == 1) || (bit1 == 1 && bit2 == 0 && carry == 1) || (bit1 == 1 && bit2 == 1 && carry == 0)) {
            result[1] = 1;
            return result;
        }else {
            result[0] = 1;
            result[1] = 1;
            return result;
        }
    }

    public static String registerArrayToString(int[] array){
        String s = "";
        for(int i : array){
            s = s.concat(i + "");
        }

        return s;
    }
}
