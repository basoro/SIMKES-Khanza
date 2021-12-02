package simrskhanza;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
/**
 *
 * @author Thomas Otero H3R3T1C
 */
public class Update {
    private static String URL ;
    
    public static String getLatestVersion() throws Exception
    {
        URL= "https://raw.githubusercontent.com/basoro/SIMKES-Khanza/master/changelog.txt";
        String data = getData(URL);
        return data.substring(data.indexOf("[version]")+9,data.indexOf("[/version]"));
    }
    public static String getWhatsNew() throws Exception
    {
        URL= "https://raw.githubusercontent.com/basoro/SIMKES-Khanza/master/changelog.txt";
        String data = getData(URL);
        return data.substring(data.indexOf("[history]")+9,data.indexOf("[/history]"));
    }
    private static String getData(String address)throws Exception
    {
        URL url = new URL(address);
        
        InputStream html = null;

        html = url.openStream();
        
        int c = 0;
        StringBuffer buffer = new StringBuffer("");

        while(c != -1) {
            c = html.read();
            buffer.append((char)c);
        }
        return buffer.toString();
    }
}
