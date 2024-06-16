package services;

import constants.CryptoAlphabet;
import entity.Result;
import exception.ApplicationException;
import repository.ResultCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Decode implements Function{
    @Override
    public Result execute(String[] parameters) {
        try {
            String operation = parameters[0];
            String key = parameters[1];
            String filePath = parameters[2];

            String content = readFile(filePath);
            String decodedMessage;

            switch (operation) {
                case "2":
                    decodedMessage = caesarDecrypt(content, Integer.parseInt(key));
                    break;
                case "3":
                    decodedMessage = caesarBruteForce(content);
                    break;
                default:
                    throw new ApplicationException("Incorrect operation to decrypt.");
            }

            System.out.println("Decrypted Message:\n" + decodedMessage);
            return new Result(ResultCode.OK);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("Decode operation finished with exception", e));
        }
    }

    private String readFile(String filePath) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return content.toString().trim();
    }

    private String caesarDecrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        String alphabet = CryptoAlphabet.ALPHABET;
        int alphabetSize = alphabet.length();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = alphabet.indexOf(c);
            if (index != -1) {
                int newIndex = (index - shift) % alphabetSize;
                if (newIndex < 0) {
                    newIndex += alphabetSize;
                }
                result.append(alphabet.charAt(newIndex));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private String caesarBruteForce(String text) {
        String alphabet = CryptoAlphabet.ALPHABET;
        int alphabetSize = alphabet.length();
        String likelyDecryptedMessage = null;
        int maxMatchCount = 0;

        for (int shift = 1; shift < alphabetSize; shift++) {
            String decryptedText = caesarDecrypt(text, shift);
            int matchCount = countMatches(decryptedText);
            if (matchCount > maxMatchCount) {
                maxMatchCount = matchCount;
                likelyDecryptedMessage = decryptedText;
            }
        }
        return likelyDecryptedMessage;
    }

    private int countMatches(String text) {
        String[] keywords = {" the ", " and ", " to ", " of ", " a ", " in ", " that ", " is ", " was ", " he ", " for "};
        int count = 0;
        for (String keyword : keywords) {
            count += countOccurrences(text, keyword);
        }
        return count;
    }

    private int countOccurrences(String text, String keyword) {
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        return (int) pattern.splitAsStream(text).count() - 1;
    }
}
