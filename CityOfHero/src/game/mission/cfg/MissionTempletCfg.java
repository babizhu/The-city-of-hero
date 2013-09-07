package game.mission.cfg;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissionTempletCfg {
private static final Map<Short,MissionTemplet> missions = new HashMap<Short, MissionTemplet>();
	
	static{
		
		
	}
	private static final String FILE = "resource/mission.xml";
	
		
	/**
	 * 通过配置表读取Npc战士模板
	 */
	public static void init(){
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();  
			List<?> list = root.getChildren( "mission" ); 
			
			for( int i = 0; i < list.size(); i++ ){
				Element element = (Element) list.get( i );
				
				MissionTemplet templet = new MissionTemplet( element );
			
				/*******************关闭打印****************************
							System.out.println( templet );
				********************************************************/
				
				MissionTemplet temp = missions.put( templet.getId(), templet );
				if( temp != null ){
					throw new RuntimeException( "主线任务" + temp.getId() + "重复了" );
				}
				
				
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
		System.out.println( "主线关卡配置表解析完毕" );
	}
	
	
	
	/**
	 * 通过id获取主线任务
	 * @param templetId
	 * @return
	 */
	public static MissionTemplet getTempletById( short templetId ){
		return  missions.get( templetId );
	}
	
	
	public static void main(String[] args) {
		init();
		System.out.println( getTempletById( (short) 1) );
		
	}

}
