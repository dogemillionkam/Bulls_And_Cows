package bullscows;

import java.util.*;

public class BullsAndCows {

    private final ArrayList<String> secretCodeArray = new ArrayList<>();
    private String forPrint;
    String[] userGuess;

    String numberInput;
    String possibleSymbols;
    static StringBuilder printNumber = new StringBuilder();

    Scanner scanner = new Scanner(System.in);

    BullsAndCows(String numberInput, String possibleSymbols) {
        this.numberInput = numberInput;
        this.possibleSymbols = possibleSymbols;
        generateRandomNumberNano();
    }

    public void bullsAndCowsCalculator() {


        int bulls = 0;
        int cows;
        int turnCounter = 1;
        System.out.println("Okay, let's start a game!");
        while (bulls != Integer.parseInt(numberInput)) {
            bulls = 0;
            cows = 0;
            System.out.println("Turn " + turnCounter + ":");
            userGuess = scanner.next().split("");
            for (int i = 0; i < secretCodeArray.size(); i++) {
                if (Objects.equals(secretCodeArray.get(i), userGuess[i])) {
                    ++bulls;
                } else if (secretCodeArray.contains(userGuess[i])) {
                    ++cows;
                }
            }
            if (bulls > 0 & cows > 0) {
                System.out.printf("Grade: %s bull(s) and %s cow(s).", bulls, cows);
            } else if (bulls > 0) {
                System.out.printf("Grade: %s bull(s).", bulls);
            } else if (cows > 0) {
                System.out.printf("Grade: %s cow(s).", cows);
            } else {
                System.out.print("Grade: None.");
            }
            System.out.println();
            ++turnCounter;
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }


    public void generateRandomNumberNano() {
        ArrayList<String> alphabetSubString = null;
        ArrayList<String> numbersSubString = null;
        if (Integer.parseInt(numberInput) > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %s because there aren't enough unique digits.", numberInput);
        } else {
            Random random = new Random();
            if (Integer.parseInt(possibleSymbols) >= 11) {
                int endIndex = Integer.parseInt(possibleSymbols) - 10;
                alphabetSubString = new ArrayList<>();
                String alphabet = "abcdefghijklmnopqrstuvwxyz";
                forPrint = "," + alphabet.substring(0, endIndex);
                String[] subAlpha = alphabet.substring(0, endIndex).split("");
                for (int i = 0; i < subAlpha.length; i++) {
                    alphabetSubString.add(i, subAlpha[i]);
                }

                numbersSubString = new ArrayList<>();
                String numbers = "0123456789";
                String[] numAlpha = numbers.split("");
                for (int i = 0; i < numAlpha.length; i++) {
                    numbersSubString.add(i, numAlpha[i]);
                }
            }
            while (printNumber.length() != Integer.parseInt(numberInput)) {
                if (Integer.parseInt(numberInput) < 11 & Integer.parseInt(possibleSymbols) < 11) {
                    String printNum = printNumber.toString();
                    String addition = String.valueOf((int) (random.nextDouble() * Integer.parseInt(possibleSymbols)));
                    if (!printNum.contains(addition)) {
                        printNumber.append(addition);
                    }
                } else {
                    if (numbersSubString.size() != 0) {
                        int numberIndex = (int) (random.nextDouble() * numbersSubString.size());
                        String addThisNumber = numbersSubString.get(numberIndex);
                        printNumber.append(addThisNumber);
                        numbersSubString.remove(numberIndex);
                    }
                    assert alphabetSubString != null;
                    if (alphabetSubString.size() != 0 && printNumber.length() != Integer.parseInt(numberInput)) {
                        int alphabetIndex = (int) (random.nextDouble() * alphabetSubString.size());
                        String addThisLetter = alphabetSubString.get(alphabetIndex); //0a2a
                        printNumber.append(addThisLetter);
                        alphabetSubString.remove(alphabetIndex);
                    }
                }

            }
            for (int i = 0; i < printNumber.length(); i++) {
                secretCodeArray.add(String.valueOf(printNumber.charAt(i)));
            }
        }
        if (Integer.parseInt(possibleSymbols) <= 10) {
            System.out.printf("The secret is prepared: %s (0-%s).\n", "*".repeat(Integer.parseInt(numberInput)), Integer.parseInt(possibleSymbols) - 1);
        } else {
            String onceMore = forPrint.length() > 0 ? forPrint.charAt(1) + "-" + forPrint.charAt(Integer.parseInt(possibleSymbols) - 10) : String.valueOf(forPrint.charAt(0));
            System.out.printf("The secret is prepared: %s (0-9, %s).\n", "*".repeat(Integer.parseInt(numberInput)), onceMore);

        }
    }
}

