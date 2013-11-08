package config_file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-5
 * Time: 下午5:30
 * To change this template use File | Settings | File Templates.
 */
public class TempletFile {
    private final String PATH = "D:\\work\\The-city-of-hero\\Tools\\source_templet\\";
    private final String templetPath;

    public TempletFile(String templetPath) {
        this.templetPath = PATH + templetPath;
    }

    public String getTempletStr() {
        StringBuilder sb = new StringBuilder();
        String data;
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader( templetPath ) );
            while( (data = reader.readLine() ) != null ) {
                sb.append( data );
                sb.append(System.getProperty( "line.separator" ) );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String file = "xTemplet.txt";
        TempletFile t = new TempletFile( file );
        System.out.println( t.getTempletStr() );

        String a = "fsafa#adfds";
        a = a.replace( "#a", "xxxxx" );
        System.out.println( a );
    }

}
