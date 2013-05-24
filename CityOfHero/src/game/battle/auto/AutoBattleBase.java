package game.battle.auto;

import game.battle.BattleBase;

public abstract class AutoBattleBase extends BattleBase {

	/**
	 * 返回战斗结束后需要获取的战斗信息
	 * @return
	 */
	public abstract BattleSituation getBattleSituation();
}
