package xyz.pixelatedw.mineminenomi.quests.swordsman.objectives;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DojoSenseiEntity;
import xyz.pixelatedw.wypi.quests.objectives.IEntityInteractObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class TalkWithSwordsmanSenseiObjective extends Objective implements IEntityInteractObjective
{

	public TalkWithSwordsmanSenseiObjective()
	{
		super("Talk with the Dojo Sensei");
	}

	@Override
	public boolean checkInteraction(PlayerEntity player, Entity entity)
	{
		if(!(entity instanceof DojoSenseiEntity))
			return false;
		
		return true;
	}
}