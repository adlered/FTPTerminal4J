package pers.adlered.ftpeasy;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.Socket;

public class Downloader extends Thread {
    private String ip;
    private int port;

    public Downloader(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        int status = Definer.status;
        String filename = Definer.filename;
        String saveFrom = Definer.saveFrom;
        Definer.status = 0;
        Definer.filename = null;
        Definer.saveFrom = null;

        try {
            Socket socket = new Socket(ip, port);
            switch (status) {
                case 1:
                    //List mode
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Definer.encode));
                    System.out.println("=== Passive Mode Data ===");
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            bufferedReader.close();
                            break;
                        }
                        System.out.println(line);
                    }
                    System.out.println("=== ataD edoM evissaP ===");
                    break;
                case 2:
                    //Download mode
                    new Outputer("DOWNLOAD", "Downloading " + filename + " from server, please wait...");
                    InputStream inputStream = socket.getInputStream();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    File file = new File(filename);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    byte[] bytes = new byte[1];
                    while (bufferedInputStream.read(bytes) != -1) {
                        bufferedOutputStream.write(bytes, 0, bytes.length);
                        bufferedOutputStream.flush();
                    }
                    new Outputer("DOWNLOAD", "File " + filename + " downloaded into local path " + file.getAbsolutePath());
                    Definer.downloadLock = false;
                    break;
                case 3:
                    //Upload mode
                    File localFile = new File(saveFrom);
                    InputStream localStream = new FileInputStream(localFile);
                    byte[] data = new byte[(int)localFile.length()];
                    localStream.read(data);
                    localStream.close();
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(data);
                    outputStream.flush();
                    outputStream.close();
                    break;
            }
        } catch (Exception e) {
        }
    }
}
