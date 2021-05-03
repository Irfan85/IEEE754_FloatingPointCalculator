import java.util.Arrays;

public class OptimizedMultiplier {

    private static final int BIT_SYSTEM = 5;

    public static void main(String[] args) {
        // *** REMEMBER TO FORMAT THE MULTIPLICAND IN BIT_SYSTEM ***
        optimizedMultiply("10101", "01011");
    }

    public static void optimizedMultiply(String multiplier, String multiplicand){
        int[] multiplicandRegister = new int[BIT_SYSTEM];
        for (int i = 0; i < multiplicandRegister.length; i++) {
            multiplicandRegister[i] = Integer.parseInt(multiplicand.charAt(i) + "");
        }

        int[] productRegister = new int[BIT_SYSTEM * 2];
        for (int i = BIT_SYSTEM, k = 0; i < productRegister.length; i++) {
            productRegister[i] = Integer.parseInt(multiplier.charAt(k++) + "");
        }

        System.out.println("Iteration\t\tProduct");
        System.out.println(0 + "\t\t\t\t" + RegularMultiplier.registerArrayToString(productRegister));
        System.out.println("__________________________________________________");

        for(int i = 1; i <= BIT_SYSTEM; i++){
            if (productRegister[productRegister.length - 1] == 1) {
                // Add multiplicand to product
                int[] productLeft = Arrays.copyOfRange(productRegister, 0, BIT_SYSTEM);
                int[] leftRes = RegularMultiplier.addBin(multiplicandRegister, productLeft);

                System.arraycopy(leftRes, 0, productRegister, 0, leftRes.length);
            }

            System.out.println(" \t\t\t\t" + RegularMultiplier.registerArrayToString(productRegister));

            // Shift product register to right
            for(int j = productRegister.length - 1; j >= 0; j--){
                if(j != 0){
                    productRegister[j] = productRegister[j - 1];
                }else{
                    productRegister[j] = 0;
                }
            }

            System.out.println(i + ":\t\t\t\t" + RegularMultiplier.registerArrayToString(productRegister));
            System.out.println("__________________________________________________");
        }
    }

}
