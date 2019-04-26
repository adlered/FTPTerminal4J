package pers.adlered.ftpeasy;

import com.sun.tools.corba.se.idl.ExceptionEntry;

import java.util.concurrent.TimeUnit;

public class Processers {
    public static void list(String command) {
        Definer.status = 1;
        new Writer("PASV");
        new Writer(command.replaceFirst("list", "LIST"));
    }

    public static void retr(String command) {
        String filename = command.replaceFirst("retr ", "");
        String[] filenames = filename.split(" ");
        for (int i = 0; i < filenames.length; i++) {
            while (Definer.downloadLock) {System.out.print("");} //If downloadLock is true, waiting to false to continue process
            System.out.println(">>> Downloading file " + filenames[i]);
            String[] dirAndFilename;
            if (filenames[i].indexOf("/") != -1) {
                dirAndFilename = filenames[i].split("/");
            } else {
                dirAndFilename = filenames[i].split("\\\\");
            }
            Definer.downloadLock = true;
            Definer.status = 2;
            new Writer("PASV");
            Definer.filename = dirAndFilename[dirAndFilename.length - 1];
            new Writer("RETR " + filenames[i]);
        }
    }

    public static void stor(String command) {
        Definer.status = 3;
        new Writer("PASV");
        String filename = command.replace("stor ", "");
        String[] filenames = filename.split(" ");
        Definer.saveFrom = filenames[0];
        String saveTo = filenames[filenames.length - 1];
        //Skip saveTo path
        System.out.println("<<< Uploading file " + Definer.saveFrom + " as " + saveTo);
        new Writer("STOR " + saveTo);
    }

    public static void rn(String command) {
        String oldName = command.split(" ")[1];
        String newName = command.split(" ")[2];
        new Writer("RNFR " + oldName);
        new Writer("RNTO " + newName);
    }
}
