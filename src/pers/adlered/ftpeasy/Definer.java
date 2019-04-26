package pers.adlered.ftpeasy;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Definer {
    public static String saveFrom = null;

    public static String user = "anonymous";
    public static String pass = "";

    public static String host = "127.0.0.1";
    public static int port = 21;

    public static Socket socket = null;
    public static InputStream inputStream = null;
    public static OutputStream outputStream = null;

    public static String encode = "GBK";

    /**
     * Status
     * 0: Free
     * 1: Listing
     * 2: Downloading
     * 3: Uploading
     */
    public static int status = 0;

    public static String filename;

    public static boolean downloadLock = false;

    public static boolean uploadLock = false;
}
