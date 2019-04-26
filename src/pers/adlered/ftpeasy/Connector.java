package pers.adlered.ftpeasy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector {
    public Connector(String ip, int port) {
        try {
            Definer.socket = new Socket(ip, port);
            Definer.inputStream = Definer.socket.getInputStream();
            Definer.outputStream = Definer.socket.getOutputStream();
            new Outputer(ip + ":" + port + " connected.");
        } catch (UnknownHostException uhe) {
            new Outputer("ERROR", "Unknown hostname!");
        } catch (IOException ioe) {
            new Outputer("ERROR", "Unable to connect!");
        }
    }
}
