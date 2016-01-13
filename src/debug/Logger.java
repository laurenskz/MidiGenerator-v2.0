package debug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Laurens on 12-1-2016.
 */
public class Logger {

    public static final String DEBUG_FILE = "debug.txt";
    private static Logger ourInstance = new Logger();

    public static Logger getLogger() {
        return ourInstance;
    }
    private PrintWriter printWriter;

    private Logger() {
        try {
            printWriter = new PrintWriter(new File(DEBUG_FILE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void d(DebugLevel debugLevel,String tag,String message){
        String toWrite = tag+"["+debugLevel + "]: " + message;
        System.out.println(toWrite);
        printWriter.println(toWrite);
    }

    public void d(String tag,String message){
        d(DebugLevel.INFO, tag, message);
    }

    public void close(){
        printWriter.close();
    }
}
