package pers.adlered.ftpeasy;

public class CloseConnections {
    public CloseConnections() {
        try {
            Definer.socket.close();
            Definer.inputStream.close();
            Definer.outputStream.close();
        } catch (Exception e) {}
    }
}
