package pers.adlered.ftpeasy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                String readText = new BufferedReader(new InputStreamReader(Definer.inputStream, Definer.encode)).readLine();
                if (readText == null) {
                    new Outputer("DISCONNECTED", "Server disconnected.");
                    new CloseConnections();
                    System.exit(0);
                }
                //Output to console via Outputer
                new Outputer("SERVER", readText);
                //Processing status code
                try {
                    int statusCode = Integer.parseInt(readText.split(" ")[0]);
                    //Passive Mode
                    if (statusCode == 227) {
                        readText = readText.split("\\(")[1];
                        readText = readText.split("\\)")[0];
                        String[] paramList = readText.split(",");
                        int calcPort = (Integer.parseInt(paramList[paramList.length - 2]) * 256) + Integer.parseInt(paramList[paramList.length - 1]);
                        new Downloader(Definer.host, calcPort).start();
                    }
                    if (statusCode == 230) {
                        new Outputer("Now logged in. You can type commands into terminal.");
                        new Outputer("Type \"help\" to get help.");
                    }
                } catch (Exception e) {
                }
            } catch (IOException ioe) {
                new Outputer("ERROR", "IOException!");
            }
        }
    }
}
