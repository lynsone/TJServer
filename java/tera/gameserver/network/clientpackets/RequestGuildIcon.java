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
package tera.gameserver.network.clientpackets;

import tera.gameserver.manager.GuildManager;
import tera.gameserver.model.GuildIcon;
import tera.gameserver.model.playable.Player;
import tera.gameserver.network.serverpackets.GuildIconInfo;

/**
 * @author Ronn
 */
public class RequestGuildIcon extends ClientPacket
{
	
	private Player player;
	
	private String name;
	
	/**
	 * Method finalyze.
	 * @see rlib.util.pools.Foldable#finalyze()
	 */
	@Override
	public void finalyze()
	{
		player = null;
		name = null;
	}
	
	@Override
	protected void readImpl()
	{
		player = owner.getOwner();
		readShort();
		name = readString();
	}
	
	@Override
	protected void runImpl()
	{
		if ((player == null) || (name == null))
		{
			return;
		}
		
		final GuildManager guildManager = GuildManager.getInstance();
		final GuildIcon icon = guildManager.getIcon(name);
		
		if ((icon == null) || !icon.hasIcon())
		{
			return;
		}
		
		player.sendPacket(GuildIconInfo.getInstance(icon), true);
	}
}
