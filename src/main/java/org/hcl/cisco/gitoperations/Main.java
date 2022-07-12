package org.hcl.cisco.gitoperations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the below options");
        System.out.println("1:clone, 2: push, 3:pull, 4: log, 0:Exit");
        int option = 0;
        do{
            option = scanner.nextInt();
            switch (option){
                case 4:
                    System.out.println("Case 4");
                case 0:
                    System.out.println("Exiting the process");
                    System.exit(1);
            }
        }while(option != 0);
    }
}
