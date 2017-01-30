/*
 * This file is part of TJServer.
 * 
 * TJServer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * TJServer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package tera.gameserver.model.skillengine.effects;

import tera.gameserver.model.Character;
import tera.gameserver.templates.EffectTemplate;
import tera.gameserver.templates.SkillTemplate;

/**
 * @author Ronn
 */
public class ManaHealOverTimePercent extends ManaHealOverTime
{
	/**
	 * Constructor for ManaHealOverTimePercent.
	 * @param template EffectTemplate
	 * @param effector Character
	 * @param effected Character
	 * @param skill SkillTemplate
	 */
	public ManaHealOverTimePercent(EffectTemplate template, Character effector, Character effected, SkillTemplate skill)
	{
		super(template, effector, effected, skill);
	}
	
	/**
	 * Method getPower.
	 * @param effector Character
	 * @param effected Character
	 * @return int
	 */
	@Override
	protected int getPower(Character effector, Character effected)
	{
		return (effector.getMaxMp() / 100) * getTemplate().getPower();
	}
	
	/**
	 * Method isDone.
	 * @param effector Character
	 * @param effected Character
	 * @return boolean
	 */
	@Override
	protected boolean isDone(Character effector, Character effected)
	{
		final int limit = getTemplate().getLimit();
		
		if (limit == -1)
		{
			return super.isDone(effector, effected);
		}
		
		return effected.getCurrentMpPercent() >= limit;
	}
}