package com.surendran.robotchallenge;

import com.surendran.robotchallenge.enums.Command;
import com.surendran.robotchallenge.exception.RobotChallengeException;
import com.surendran.robotchallenge.model.TableTop;
import com.surendran.robotchallenge.service.Executor;

import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_RED = "\033[1;91m";   // RED
    public static final String COLOR_CYAN = "\033[1;96m";  // CYAN

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Executor executor = new Executor();
        System.out.print("Please enter the Dimension of Square Table top: ");
        String input = scanner.nextLine();
        while (!input.matches("\\d+")) {
            printMessage("ERROR: Invalid input, Re-enter: ", COLOR_RED);
            input = scanner.nextLine();
        }
        int N = Integer.parseInt(input);
        executor.initialize(new TableTop(N, N));

        System.out.print("Do you like to see Output for all Commands (Y/N): ");
        String resp = scanner.nextLine();
        while (!("Y".equals(resp) || "N".equals(resp))) {
            printMessage("ERROR: Invalid input, Re-enter:", COLOR_RED);
            resp = scanner.nextLine();
        }
        boolean outPut4AllCommands = "Y".equals(resp);

        printMessage(String.format("Enter the execution command: <Command can be one of %s or EXIT >", Arrays.toString(Command.values())),null);
        boolean continueExecution = true;
        while (continueExecution) {
            String commandLine = scanner.nextLine();

            if ("EXIT".equals(commandLine)) {
                continueExecution = false;
            } else {
                try {
                    String output = executor.validateAndExecuteCommand(commandLine);
                    if (outPut4AllCommands) {
                        printMessage("OUTPUT: " + output, COLOR_CYAN);
                    } else if ("REPORT".equals(commandLine)) {
                        printMessage("OUTPUT: " + output, COLOR_CYAN);
                    }
                } catch (RobotChallengeException rce) {
                    printMessage("ERROR: " + rce.getMessage(), COLOR_RED);
                }
            }
        }

        scanner.close();
    }

    private static void printMessage(String message, String colour) {
        if (colour!=null) System.out.println(colour + message + COLOR_RESET);
        else System.out.println(message);
    }
}
