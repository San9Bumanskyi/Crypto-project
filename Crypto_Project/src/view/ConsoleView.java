package view;

import entity.Result;

import java.util.Scanner;

import static constants.ApplicationCompletionConstants.EXCEPTION;
import static constants.ApplicationCompletionConstants.SUCCESS;

public class ConsoleView implements View {
    @Override
    public String[] getParameters() {
        Scanner scanner = new Scanner(System.in);
        String[] parameters = new String[3];

        System.out.println("Select an operation:");
        System.out.println("1. Encrypt with the Caesar method");
        System.out.println("2. Decipher by Caesar's method");
        System.out.println("3. Decipher by Caesar's method using brute force");
        String operation = scanner.nextLine();
        parameters[0] = operation;

        if (operation.equals("1")){
            System.out.println("Enter the encryption key:");
            String key = scanner.nextLine();
            parameters[1] = key;
        } else if (operation.equals("2")) {
            System.out.println("Enter the decryption key:");
            String key = scanner.nextLine();
            parameters[1] = key;
        } else {
            parameters[1] = "";
        }
        if (operation.equals("1") || operation.equals("2") || operation.equals("3")) {
            System.out.println("Select a file:");
            System.out.println("1. Default File");
            System.out.println("2. Custom File");
            String fileChoice = scanner.nextLine();
            if (fileChoice.equals("2")) {
                System.out.println("Enter the path to the file:");
                String filePath = scanner.nextLine();
                parameters[2] = filePath;
            } else {
                if (parameters[0].equals("1")){
                    parameters[2] = "D:\\JAVAR\\Projects\\Crypto_Project\\src\\Text.txt";
                } else {
                    parameters[2] = "D:\\JAVAR\\Projects\\Crypto_Project\\src\\Text_encoded.txt";
                }
            }
        }
        return parameters;
    }

    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()){
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }
    }
}
