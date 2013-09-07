package game.fighter.cfg;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 从配置表中初始化Npc Fighter模板
 * @author liukun
 *
 */
public class NpcFighterTempletCfg {
	private static final Map<Integer,NpcFighterTemplet> npcFighterTemplets = new HashMap<Integer, NpcFighterTemplet>();
	
	/**
	 * 此配置表必须先于MissionTempletCfg初始化，因此无需提前手动调用
	 */
	static{
		init();
		
	}
	private static final String FILE = "resource/npc.xml";
	
		
	/**
	 * 通过配置表读取Npc战士模板
	 */
	private static void init(){
		
		SAXBuilder builder = new SAXBuilder();    
		Document document;
		try {
			document = builder.build( FILE );
			Element root = document.getRootElement();  
			List<?> fighterList= root.getChildren( "npc" ); 
			
			for( int i = 0; i < fighterList.size(); i++ ){
				NpcFighterTemplet ft = new NpcFighterTemplet( (Element)fighterList.get(i) );
				NpcFighterTemplet temp = npcFighterTemplets.put( ft.templetId, ft );
				if( temp != null ){
					throw new RuntimeException( "npc战士模板" + ft.templetId + "重复了" );
				}
				
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
		System.out.println( "npc 战士配置文件解析完毕" );
	}
	
	
	/**
	 * 通过id获取战士的引用
	 * @param templetId
	 * @return
	 */
	public static NpcFighterTemplet getNpcById( int templetId ){
		return npcFighterTemplets.get( templetId );
	}
	public static void main(String[] args) {
		
		int id = 101101;
		System.out.println( getNpcById( id ) );
	}

}
