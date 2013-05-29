package game.battle.formula;

import game.fighter.FighterBase;

public enum Formula {
	/**
	 * 普通攻击的计算公式
	 * 无参数
	 */
	NormalAttackFormula {
		@Override
		public
		int run(FighterBase attacker, FighterBase defender, float[] arguments) {
//			int damage = 0;
//			if( attacker.getPhyAttack() != 0 ){
//				damage = attacker.getPhyAttack() - defender.getPhyDefend();
//			}
//			else{
//				damage = attacker.getMagicAttack() - defender.getMagicDefend();
//			}
//			return damage;
			
			return attacker.getPhyAttack() != 0 ? attacker.getPhyAttack() - defender.getPhyDefend() : attacker.getMagicAttack() - defender.getMagicDefend();
		}
	},
	
	/**
	 * 技能攻击伤害的计算公式
	 * 参数意义:	
	 * 		arguments[0] 普通伤害的倍数	
	 */
	SkillAttackFormula {
		@Override
		public
		int run(FighterBase attacker, FighterBase defender, float[] arguments) {
			throw new UnsupportedOperationException();
		}
	},
	/**
	 * 按比例恢复hp<br>
	 * 参数意义:
	 * 		arguments[0]==1则按照攻击者的hpMax进行计算，否则按照防守者的hpMax进行计算<br>
	 * 		arguments[1] 计算比例
	 * 
	 */
	HpFormula{

		@Override
		public int run( FighterBase attacker, FighterBase defender, float[] arguments ) {
			if( arguments[0] == 1 ){
				return (int) (attacker.getHpMax() * arguments[1]);
			}
			return (int) (defender.getHpMax() * arguments[1]);
		}		
	},
	/**
	 * 输入多少返回多少<br>
	 * 参数意义：arguments[0]为输入的数值
	 */
	DirectOutputFormula{

		@Override
		public int run(FighterBase attacker, FighterBase defender, float[] arguments) {
			return (int) arguments[0];
		}
		
	};
	public abstract int run( FighterBase attacker, FighterBase defender, float[] arguments );
}
