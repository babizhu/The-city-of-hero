package game.battle.auto;

import game.battle.formation.IFormation;
import game.fighter.FighterBase;
import game.fighter.NpcFighter;
import game.fighter.cfg.NpcFighterTempletCfg;
import game.mission.cfg.MissionTempletCfg;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class AutoBattleTest {

	IFormation aFormation;
	IFormation dFormation;
	
	void init(){
		List<FighterBase> attackers = new ArrayList<FighterBase>();		
		FighterBase fighter = new NpcFighter( NpcFighterTempletCfg.getNpcById( 101101 ) );
		attackers.add( fighter );
		fighter = new NpcFighter( NpcFighterTempletCfg.getNpcById( 101102 ) );
		fighter.setPosition( (byte) 3 );
		attackers.add( fighter );
		aFormation = new Formation( attackers, true, null );
//	
		short missionId = 2;
		//aFormation = MissionTempletCfg.getTempletById( missionId ).getFormationClone( 0 );
		dFormation = MissionTempletCfg.getTempletById( missionId ).getFormationCloneByWave( 0 );
//		System.out.println( dFormation );
		
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MissionTempletCfg.init();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() {
		
		int count = 1;//循环次数
		long begin = System.nanoTime();
		AutoBattle battle= null;
		for( int i = 0; i < count; i++ ){
			init();
		
			battle = new AutoBattle( aFormation, dFormation );
			battle.run();
		}
		
		System.out.println("总共用时" + (System.nanoTime() - begin) / 1000000000f + "秒");
		init();		
		new ParseBattleSituation( aFormation, dFormation, battle.getBattleSituation() ).parse();
	}

}
