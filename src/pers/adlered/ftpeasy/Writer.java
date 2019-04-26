package pers.adlered.ftpeasy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Writer {
    public Writer(String text) {
        boolean processerHandled = false;
        if (text.indexOf("list") == 0) {
            processerHandled = true;
            Processers.list(text);
        }
        if (text.indexOf("retr") == 0) {
            processerHandled = true;
            Processers.retr(text);
        }
        if (text.indexOf("stor") == 0) {
            processerHandled = true;
            Processers.stor(text);
        }
        if (text.indexOf("rn") == 0) {
            processerHandled = true;
            Processers.rn(text);
        }
        if (!processerHandled) {
            switch (text) {
                case "help":
                    new Outputer("?", "FTPEasy Helper");
                    new Outputer("?", "* COMMAND MUST BE LOWER CASE *");
                    new Outputer("?", "list - List file/folder(s) of current path");
                    new Outputer("?", "cwd (path) - Redirect to another path");
                    new Outputer("?", "retr (file1) [file2] ... - Download file(s) from server into current client path");
                    new Outputer("?", "stor (localFilePath&Name) [remoteFilePath&Name] - Upload local file(s) into server");
                    new Outputer("?", "pwd - Get current path");
                    new Outputer("?", "mkd (dir) - Remove a directory");
                    new Outputer("?", "dele (file) - Remove a file");
                    new Outputer("?", "rn (filename) (targetFilename) - Rename file/folder");
                    new Outputer("?", "quit - Disconnect and exit client");
                    break;
                default:
                    String outputText = text;
                    text = text + "\r\n\r\n";
                    try {
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(Definer.outputStream, Definer.encode));
                        bufferedWriter.write(text);
                        bufferedWriter.flush();
                        new Outputer("COMMAND", outputText);
                    } catch (IOException ioe) {
                        new Outputer("ERROR", "IOException!");
                    }
                    break;
            }
        }
    }
}
