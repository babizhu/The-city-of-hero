package game.fighter;

import game.fighter.cfg.NpcFighterTemplet;

public class NpcFighter extends FighterBase {

	public NpcFighter( NpcFighterTemplet templet ) {
		
		setName( templet.name );
		setHpMax( templet.hpBase );
		setHp( getHpMax() );
		setPhyAttack( templet.phyAttackBase );
		setPhyDefend( templet.phyDefendBase );
		setMagicAttack( templet.magicAttackBase );
		setMagicDefend( templet.magicDefendBase );
		
		setDodge( templet.dodge );
		setCrit( templet.crit );
		setCritMultiple( templet.critMultiple );
		setSpeed( templet.speed );
	}
	
	
}
