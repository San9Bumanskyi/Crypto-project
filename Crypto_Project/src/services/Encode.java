package services;

import constants.CryptoAlphabet;
import entity.Result;
import exception.ApplicationException;
import repository.ResultCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encode implements Function{
    @Override
    public Result execute(String[] parameters) {
        try {
            String key = parameters[1];
            String filePath = parameters[2];

            String content = readFile(filePath);
            String encodedMessage = caesarEncrypt(content, Integer.parseInt(key));

            String outputFilePath = generateOutputFilePath(filePath);
            writeFile(outputFilePath, encodedMessage);

            System.out.println("Зашифроване повідомлення:\n" + encodedMessage);
            return new Result(ResultCode.OK);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("Encode operation finished with exception", e));
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

    private void writeFile(String filePath, String content) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(content);
        writer.close();
    }

    private String generateOutputFilePath(String inputFilePath) {
        int dotIndex = inputFilePath.lastIndexOf('.');
        String outputFilePath;
        if (dotIndex == -1) {
            outputFilePath = inputFilePath + "_encoded.txt";
        } else {
            outputFilePath = inputFilePath.substring(0, dotIndex) + "_encoded" + inputFilePath.substring(dotIndex);
        }
        return outputFilePath;
    }

    private String caesarEncrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        String alphabet = CryptoAlphabet.ALPHABET;
        int alphabetSize = alphabet.length();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int index = alphabet.indexOf(c);
            if (index != -1) {
                int newIndex = (index + shift) % alphabetSize;
                result.append(alphabet.charAt(newIndex));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}