package pers.adlered.ftpeasy;

public class FTPEasy {
    public static void setUser(String user) {
        Definer.user = user;
    }

    public static void setPass(String pass) {
        Definer.pass = pass;
    }

    public static void setHost(String host) {
        Definer.host = host;
    }

    public static void setPort(int port) {
        Definer.port = port;
    }

    public static void setEncode(String defaultIsGBK) {
        Definer.encode = defaultIsGBK;
    }

    public static void execute(String command) {
        new Writer(command);
    }

    public static void connect() {
        connect(Definer.user, Definer.pass, Definer.host, Definer.port);
    }

    public static void connect(String user, String pass, String host, int port) {
        new Connector(host, port);
        new Reader().start();
        new Writer("USER " + user);
        try {
            Thread.sleep(200);
        } catch (Exception e) {
        }
        new Writer("PASS" + pass);
        new Listener().start();
    }
}
