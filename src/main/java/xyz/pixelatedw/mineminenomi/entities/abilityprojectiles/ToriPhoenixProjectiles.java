package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles;

import java.util.HashMap;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility.Data;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.CustomParticleData;

public class ToriPhoenixProjectiles
{
	public static HashMap<AbilityAttribute, ProjectileAbility.Data> projectiles = new HashMap<AbilityAttribute, ProjectileAbility.Data>();
	
	public static final EntityType PHOENIX_GOEN = WyRegistry.registerEntityType("phoenix_goen", PhoenixGoen::new);

	static
	{
		projectiles.put(ModAttributes.PHOENIX_GOEN, new Data(PHOENIX_GOEN, PhoenixGoen.class));
	}
	
	
	public static class PhoenixGoen extends ProjectileAbility
	{
		public PhoenixGoen(World world)
		{super(PHOENIX_GOEN, world);}
		
		public PhoenixGoen(EntityType type, World world)
		{super(type, world);}
		
		public PhoenixGoen(World world, double x, double y, double z)
		{super(PHOENIX_GOEN, world, x, y, z);}
		
		public PhoenixGoen(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(PHOENIX_GOEN, world, player, attr);		
		}
		
		@Override
		public void tick()
		{		
			if(this.world.isRemote)
			{
				CustomParticleData data = new CustomParticleData();
				data.setTexture(ModResources.BLUE_FLAME);
				data.setPosX(posX);
				data.setPosY(posY);
				data.setPosZ(posZ);
				
				data.setMaxAge(1);
				data.setScale(1.2F);
				
			//	ModMain.proxy.spawnParticles(world, data);
			}
			super.tick();
		}
	}
}
