/**
 * 
 */
package bootstrap;

import define.SystemCfg;
import game.mission.cfg.MissionTempletCfg;
import game.prop.cfg.PropTempletCfg;
import game.task.cfg.TaskTempletCfg;
import net.GameHandler;
import org.xsocket.connection.ConnectionUtils;
import org.xsocket.connection.IConnection.FlushMode;
import org.xsocket.connection.IHandler;
import org.xsocket.connection.Server;
import util.SystemTimer;
import util.UtilBase;

import javax.management.JMException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

/**
 * @author liukun
 * 2012-8-15
 */
public class GameServer extends Server{

	/**
	 * @param address       地址
	 * @param port          端口
	 * @param handler       处理句柄
	 * @throws UnknownHostException
	 * @throws IOException
	 */
    private GameServer(InetAddress address, int port, IHandler handler) throws IOException {
		super(address, port, handler);
		setFlushmode( FlushMode.ASYNC );
		setIdleTimeoutMillis( 1000000000 );
	}

	/**
	 * 初始化系统配置文件
	 */
	private void readAllCfg(){
		try{
			PropTempletCfg.init();
			TaskTempletCfg.init();
			MissionTempletCfg.init();
            System.out.println( "配置文件读取完毕\n" );
		}catch( Exception e ){
			e.printStackTrace();
			System.exit(0);
		}
	}
	public static void main(String[] args) throws IOException, JMException {
		
		System.out.println("编码集= "+System.getProperty("file.encoding"));
		System.out.println("编码集1= "+Charset.defaultCharset() );

		System.out.println( UtilBase.secondsToDateStr( SystemTimer.currentTimeSecond() ) + " The city of hero start now..." );
        System.out.println( UtilBase.secondsToDateStr( SystemTimer.currentTimeSecond() ) + " game version: " + SystemCfg.VERSION );

		InetAddress address = null;
		GameServer server = new GameServer( address, SystemCfg.PORT, new GameHandler() );
		server.readAllCfg();
		
		server.start();
		ConnectionUtils.registerMBean( server );   
		
        //System.out.println( "日志: " + server.getStartUpLogMessage() );
//		Map<String, Class> maps = server.getOptions();   

//         if( maps != null ){   
//                
//             for (Entry<String, Class> entry : maps.entrySet()) {   
//                 System.out.println("key= "+entry.getKey()+" value ="+entry.getValue().getName());   
//             }   
//         }           
         
	}
}
