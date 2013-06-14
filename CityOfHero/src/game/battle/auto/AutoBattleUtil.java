package game.battle.auto;


import game.battle.IBattleUtil;
import game.battle.buff.BuffRunPoint;
import game.battle.formula.Formula;
import game.fighter.FighterBase;

import java.util.Comparator;

import util.RandomUtil;

public class AutoBattleUtil implements IBattleUtil {

	
	private static final IBattleUtil 	INSTANCE = new AutoBattleUtil();
	private AutoBattleUtil() {}
	public static final IBattleUtil getInstance(){
		return INSTANCE;
	}
	
	/**
	 * 出手顺序由速度确定
	 */
	private static final Comparator<FighterBase> speedComparator = new Comparator<FighterBase>(){
		@Override
		public int compare( FighterBase f1, FighterBase f2 ) {
			return f2.getSpeed() - f1.getSpeed();
		}
	};
	
	/**
	 * 判断攻击者是否命中<br>
	 * 公式				由防御者的闪避率决定，闪避率越低，越容易命中			
	 * @param attacker			攻击者
	 * @param defender			防御者
	 * @return
	 * 			true		命中
	 * 			false		未命中
	 * 
	 */
	private boolean isHit( FighterBase attacker, FighterBase defender ) {
		int r = RandomUtil.getRandomInt( 0, 100 );//随机值
		
		return r > defender.getDodge();
	}
	
	/**
	 *	是否暴击
	 *
	 * @param attacker
	 * @param defender
	 * @return 
	 */
	private boolean calcCrit( FighterBase attacker, FighterBase defender ) {
		
		int r = RandomUtil.getRandomInt( 0, 100 );//随机值
		
		return r < attacker.getDodge();
		
	}

	/**
	 * 根据公式计算普通攻击的伤害值
	 * @param attacker
	 * @param defender
	 * @param formula	计算公式
	 * @param arguments	相应参数，如不存在，请放入null
	 * @return
	 */
	@Override
	public AttackInfo calcAttackInfo( FighterBase attacker, FighterBase defender, Formula formula, float[] arguments ) {
		
		AttackInfo info = new AttackInfo();
		boolean isHit = isHit(attacker, defender);
		if( !isHit ){
			return info;
		}
		
		boolean crit  = calcCrit(attacker, defender);
		
		
		
		int damage = formula.run( attacker, defender, arguments );
		if( crit ){
			damage *= attacker.getCritMultiple();
		}
		info.setCrit( crit?1:0 );
		info.setHit(isHit);
		
		damage = defender.getBm().run( damage, BuffRunPoint.AFTER_DEFENDING );
		info.setDamage( damage );

		return info;
	}

	@Override
	public int calcCounterAttackDamage(FighterBase attacker, FighterBase defender) {
		throw new UnsupportedOperationException();
	}
	@Override
	public Comparator<FighterBase> getOrderComparator() {
		return speedComparator;
	}

	public static void main(String[] args) {
		float r = 100f / 133;
		System.out.println( r );
		
		r = (float)200 / 30;
		System.out.println( r );
		
		int damage = 301;
		System.out.println( damage);
	}
	
}
