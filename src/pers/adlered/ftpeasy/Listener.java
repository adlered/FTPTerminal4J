package pers.adlered.ftpeasy;

import java.util.Scanner;

public class Listener extends Thread {
    @Override
    public void run() {
        while (true) {
            new Writer(new Scanner(System.in).nextLine());
        }
    }
}
