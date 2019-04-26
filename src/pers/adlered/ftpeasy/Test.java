package pers.adlered.ftpeasy;

public class Test {
    public static void main(String[] args) {
        //FTPEasy.setHost("10.2.51.81");
        FTPEasy.setHost("10.2.51.81");
        FTPEasy.setPort(21);
        FTPEasy.setUser("anonymous");
        FTPEasy.setPass("");
        FTPEasy.connect();
        //FTPEasy.execute("list");
    }
}
