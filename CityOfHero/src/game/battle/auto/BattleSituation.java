package game.battle.auto;

import game.battle.AttackType;
import game.fighter.FighterAttribute;
import game.fighter.FighterBase;

import java.nio.ByteBuffer;

public class BattleSituation  {
	
	private ByteBuffer	situation = null;
	
	public BattleSituation( int size ){
		situation = ByteBuffer.allocate( size );
	}
	
	/**
	 * 回合分隔符，标识一个回合开始
	 */
	public void putRoundFlag( ){
		situation.put( AttackType.BEGIN_ROUND.toNumber() );
	}

	/**
	 * 一次普通攻击的情况
	 * @param attacker      攻击者
	 * @param defender      防御者
	 * @param info          攻击信息
	 */
	public void putNormalAttack( FighterBase attacker, FighterBase defender, AttackInfo info ) {
		situation.put( AttackType.NORMAL_ATTACK.toNumber() ).put( attacker.getPosition() ).put( defender.getPosition() );
		put( info );
		
	}

	/**
	 * 放置反击信息
	 * @param damage    伤害值
	 */
	public void putCounterAttackDamage( int damage ) {
		situation.putInt( damage );
		
	}
	
	private void put(AttackInfo info) {

		situation.put( info.getRawData() );
		if( info.isHit() ){
			situation.putInt( info.getDamage() );
		}
	}

	/**
	 * 放置技能攻击的前缀信息
	 * @param attacker      攻击者
	 * @param skillId       技能id
     * @param  count        受到此次技能攻击所影响的战士，也包括本方的
	 */
	public void putSkillAttackPrefix( FighterBase attacker, int skillId, byte count ) {
		situation.put( AttackType.SKILL_ATTACK.toNumber() ).put( attacker.getPosition() ).putInt( skillId ).put( count );
	}

	/**
	 * 技能攻击中对敌人的攻击信息，仅局限于减血
	 * @param attribute     仅限于HEALTH_DOWN
	 * @param info          本次攻击的具体情况
	 */
	public void putSkillInfo( FighterAttribute attribute, AttackInfo info ) {
		situation.put( attribute.toNumber() );
		put( info );
		
	}

	/**
	 * 技能攻击中，除开对敌人攻击的，其他信息的记录，例如加自己的hp，降低对方的sp等信息
	 * @param attribute         除开HEALTH_DOWN 的其他属性改变
	 * @param numberToChange    改变值
	 */
	public void putSkillInfo(FighterAttribute attribute, int numberToChange) {
		situation.put( attribute.toNumber() ).putInt( numberToChange );
		
	}

	/**
	 * 配合技能攻击，单独放入受技能影响的战士位置
	 */
	public void putFighter(byte position) {
		situation.put(position);		
	}

	/**
	 * 配合技能攻击，单独放入战士受影响的属性个数
	 */
	public void putEffectCount( byte effectCount ) {
		situation.put( effectCount );
	}

	public ByteBuffer getData() {
		return situation;
	}

    public static void main(String[] args) {
        ByteBuffer buf =  ByteBuffer.allocate( 2 );
        buf.putInt( 3 );
    }
}
