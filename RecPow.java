import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
* This program calculates a number in the fibonacci sequence
* based off of a chosen term number.
*
* @author  Alexander Matheson
* @version 1.0
* @since   2023-04-18
*/

public final class RecPow {
    /**
    * For style checker.
    *
    * @exception IllegalStateException Utility class.
    * @see IllegalStateException
    */
    private RecPow() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * Print messages.
    *
    * @param args Unused
    */
    public static void main(String[] args) {
        // Declare lists and variable.
        final ArrayList<String> inputList = new ArrayList<String>();
        final String separator = " ";

        try {
            // Choose a file to get input from.
            final File input = new File("input.txt");
            final Scanner scanInput = new Scanner(input);

            // Choose (or create) a file to print output to.
            final FileWriter output = new FileWriter("output.txt");

            // Loop to read each line of input file.
            while (scanInput.hasNextLine()) {
                // Add the next line.
                inputList.add(scanInput.nextLine());
            }

            // Create an array with all elements in the input list.
            final String[] inputArr = new String[inputList.size()];
            for (int location = 0; location < inputArr.length; location++) {
                inputArr[location] = inputList.get(location);
            }

            // Loop to send each line to function.
            for (String numStr : inputArr) {
                // Check for errors.
                try {
                    // Check if base or exponent are negative.
                    if (Integer.parseInt(numStr.split(separator)[0]) < 0
                            || Integer.parseInt(numStr.split(separator)[1])
                            < 0) {
                        System.out.println("Number must be positive.");
                    } else {
                        // Call function.
                        final int calculated =
                            power(Integer.parseInt(numStr.split(separator)[0]),
                                     Integer.parseInt(numStr.split(separator)
                                     [1]));

                        // Print to console and file.
                        System.out.println(calculated);
                        output.write(calculated + "\n");
                    }
                } catch (NumberFormatException err) {
                    System.out.println("Error, not a viable input.");
                }
            }

            // Close writer.
            output.close();

        } catch (IOException err) {
            // For when no input file is found.
            System.err.println("Error: " + err.getMessage());
        }
    }

    /**
    * This function calculates a number.
    *
    * @param base from file
    * @param exponent also from file
    * @return calculated number
    */
    public static int power(int base, int exponent) {
        // Detect if the exponent is less than one.
        if (exponent < 1) {
            return 1;
        } else {
            // Re-call the function.
            return base * power(base, exponent - 1);
        }
    }
}
