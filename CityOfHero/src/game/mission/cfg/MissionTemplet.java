package game.mission.cfg;

import game.battle.Pet;
import game.battle.auto.Formation;
import game.battle.formation.IFormation;
import game.fighter.FighterBase;
import game.fighter.NpcFighter;
import game.fighter.cfg.NpcFighterTempletCfg;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

public class MissionTemplet {

	private final short					id;
	private final String				name;
	private final String				desc;
	private final List<IFormation>		formations;
	private final short					zoneId;
	
	public short getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
//	public List<IFormation> getFormations() {
//		return formations;
//	}
	/**
	 * 获取某一波的战士阵型的深度克隆数据，波数从0开始
	 */
	public IFormation getFormationCloneByWave( int wave ){		
		return new Formation( formations.get( wave ) );
	}
	
	public MissionTemplet( Element element ) {
		id = Short.parseShort( element.getChildText( "id" ) );
		name = element.getChildText( "name" );
		desc = element.getChildText( "desc" );
		zoneId = Short.parseShort( element.getChildText( "zone_id" ) );
		
		formations = parseFormation( element );
		
	}
	
	private List<IFormation> parseFormation( Element element ){
		List<IFormation> formations = new ArrayList<IFormation>();
		boolean isBottom = false;//NPC通常作为防守方出现
		for( int i = 0; i < 3; i++ ){
			String nodeName = "fight" + i;
			String content =  element.getChildText( nodeName );
			if( content == null || content.isEmpty() ){
				continue;
			}
			List<FighterBase> fightersList = parseFighterList( content );
			Pet pet = null;
			IFormation formation = new Formation( fightersList, isBottom, pet );
			
			formations.add( formation );
		}
		return formations;
	}
	
	private List<FighterBase> parseFighterList( String content ){
		List<FighterBase> list = new ArrayList<FighterBase>();
		String[] fighterArr = content.split( "\\|" );
		for( String s : fighterArr ){
			int fighterId = Integer.parseInt( s.split( "," )[0] );
			byte pos = Byte.parseByte( s.split( "," )[1] );
			
			if( pos < 0 || pos > Formation.TOTAL_COUNT ) {
				throw new RuntimeException( content + "错误，配置表中的战士位置必须满足: 0 <= pos <= " + Formation.TOTAL_COUNT );
			}
			
			NpcFighter f = new NpcFighter( NpcFighterTempletCfg.getNpcById( fighterId ) );
			f.setPosition( pos );
			list.add( f );
		}
		//按位置排序
		return list;
	}
	

	@Override
	public String toString() {
		return "MissionTemplet [id=" + id + ", name=" + name + ", desc=" + desc
				+ ", formations=" + formations + "]";
	}

	public short getZoneId() {
		return zoneId;
	}
	
	
}
