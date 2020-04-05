package xyz.pixelatedw.mineminenomi.abilities.yuki;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class KamakuraJussoshiAbility extends Ability
{
	public static final KamakuraJussoshiAbility INSTANCE = new KamakuraJussoshiAbility();

	public KamakuraJussoshiAbility()
	{
		super("Kamakura Jussoshi", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Like 'Kamakura', but creates a multi-layered snow barrier.");
		this.setMaxCooldown(20);

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		RayTraceResult mop = WyHelper.rayTraceBlocks(player);
		World world = player.world;
		if (player.isSneaking())
		{
			AbilityHelper.createEmptySphere(world, (int) player.posX, (int) player.posY, (int) player.posZ, 4, Blocks.SNOW_BLOCK, "air", "foliage", "liquid", "nogrief");
			AbilityHelper.createEmptySphere(world, (int) player.posX, (int) player.posY, (int) player.posZ, 6, Blocks.SNOW_BLOCK, "air", "foliage", "liquid", "nogrief");
			AbilityHelper.createEmptySphere(world, (int) player.posX, (int) player.posY, (int) player.posZ, 8, Blocks.SNOW_BLOCK, "air", "foliage", "liquid", "nogrief");
		}
		else if (mop != null)
		{
			AbilityHelper.createEmptySphere(world, (int) mop.getHitVec().x, (int) mop.getHitVec().y, (int) mop.getHitVec().z, 4, Blocks.SNOW_BLOCK, "air", "foliage", "liquid", "nogrief");
			AbilityHelper.createEmptySphere(world, (int) mop.getHitVec().x, (int) mop.getHitVec().y, (int) mop.getHitVec().z, 6, Blocks.SNOW_BLOCK, "air", "foliage", "liquid", "nogrief");
			AbilityHelper.createEmptySphere(world, (int) mop.getHitVec().x, (int) mop.getHitVec().y, (int) mop.getHitVec().z, 8, Blocks.SNOW_BLOCK, "air", "foliage", "liquid", "nogrief");
		}
		
		return true;
	}
}