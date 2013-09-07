package game.events.all;

import game.battle.auto.AutoBattle;
import game.battle.auto.Formation;
import game.battle.auto.ParseBattleSituation;
import game.battle.formation.IFormation;
import game.events.EventBase;
import game.fighter.FighterBase;
import game.fighter.NpcFighter;
import game.fighter.cfg.NpcFighterTempletCfg;
import game.mission.cfg.MissionTempletCfg;
import user.UserInfo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 和客户端进行通信测试，专门用与测试的，不是正式的程序
 * @author liukun
 * 2013-1-22 下午6:17:37
 */
public class SendBattleSituation extends EventBase {

	IFormation aFormation;
	IFormation dFormation;
	
	void init( short missionId ){
		List<FighterBase> attackers = new ArrayList<FighterBase>();		
		
		FighterBase fighter = new NpcFighter( NpcFighterTempletCfg.getNpcById( 101101 ) );
		attackers.add( fighter );
		
		fighter = new NpcFighter( NpcFighterTempletCfg.getNpcById( 101108 ) );
		fighter.setPosition( (byte) 5 );
		attackers.add( fighter );
		
		fighter = new NpcFighter( NpcFighterTempletCfg.getNpcById( 101109 ) );
		attackers.add( fighter );
		fighter.setPosition( (byte) 6 );
		
		fighter = new NpcFighter( NpcFighterTempletCfg.getNpcById( 101102 ) );
		fighter.setPosition( (byte) 3 );		
		attackers.add( fighter );
		
		aFormation = new Formation( attackers, true, null );
//	
		
		//aFormation = MissionTempletCfg.getTempletById( missionId ).getFormationClone( 0 );
		dFormation = MissionTempletCfg.getTempletById( missionId ).getFormationCloneByWave( 2 );
	}
	@Override
	public void run(UserInfo user, ByteBuffer buf) throws IOException {
		short missionId = buf.getShort();
		init( missionId );
	
		
		AutoBattle battle = new AutoBattle( aFormation, dFormation );
		battle.run();
		ByteBuffer buffer = buildEmptyPackage( 2048 );
		ByteBuffer content = battle.getBattleSituation().getData().asReadOnlyBuffer();
		content.flip();
		buffer.put( content );
		System.out.println( "战况内容的长度为：" + content.position() );
		sendPackage( user.getCon(), buffer );
		init( missionId );		
		new ParseBattleSituation( aFormation, dFormation, battle.getBattleSituation() ).parse();
	}
	
	

}
