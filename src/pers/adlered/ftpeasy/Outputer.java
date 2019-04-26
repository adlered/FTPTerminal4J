package pers.adlered.ftpeasy;

public class Outputer {
    public Outputer(String message) {
        System.out.println("[INFO] > FTPEasy > " + message);
    }

    public Outputer(String level, String message) {
        System.out.println("[" + level + "] > FTPEasy > " + message);
    }

    public Outputer(int text) {
        System.out.print((char)text);
    }

    public Outputer(String status, int text) {
        new Outputer(status, String.valueOf(text));
    }
}
