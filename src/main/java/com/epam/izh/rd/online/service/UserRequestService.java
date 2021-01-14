package com.epam.izh.rd.online.service;

import java.util.Scanner;

public class UserRequestService {
    private Scanner scanner;

    public UserRequestService() {
        scanner = new Scanner(System.in);
    }

    public int getIntNumber() {
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }
        return scanner.nextInt();
    }

    public String getText() {
        scanner.nextLine();
        return scanner.nextLine();
    }
}