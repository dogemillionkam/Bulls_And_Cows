package bullscows;

import java.security.spec.ECField;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String secretCodeLength = null;
        String possibleSymbols = null;
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Please, enter the secret code's length:");
            secretCodeLength = scanner.nextLine();
            if (secretCodeLength.contains("-") || secretCodeLength.contains("\\d+")) {
                throw new InputMismatchException();
            } else if (secretCodeLength.equals("0")) {
                throw new NoSuchFieldException();
            }
            System.out.println("Input the number of possible symbols in the code:");
            possibleSymbols = scanner.next();

            if (Integer.parseInt(possibleSymbols) < Integer.parseInt(secretCodeLength)) {
                throw new IllegalArgumentException();
            } else if(Integer.parseInt(possibleSymbols) > 36) {
                throw new InstantiationException();
            } else {
                BullsAndCows objectOne = new BullsAndCows(secretCodeLength, possibleSymbols);
                objectOne.bullsAndCowsCalculator();
            }
        } catch (IllegalArgumentException e) {
            System.out.printf("Error: it's not possible to generate a code with a length of %s with %s unique symbols.", secretCodeLength, possibleSymbols);
        } catch (InstantiationException e) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        } catch (InputMismatchException e) {
            System.out.printf("Error: \"%s\" isn't a valid number.", secretCodeLength);
        } catch (NoSuchFieldException e) {
            System.out.println("Error: the secret code length cannot be zero or a negative number.");
        }

    }

}