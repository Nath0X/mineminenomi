package xyz.pixelatedw.mineminenomi.events.devilfruits;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class DFWeaknessesEvents
{

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.getEntityLiving() instanceof LivingEntity)
		{
			LivingEntity entity = event.getEntityLiving();
			IDevilFruit props = DevilFruitCapability.get(entity);
			
			if(props.hasDevilFruit() && AbilityHelper.isAffectedByWater(entity) && !entity.isPotionActive(ModEffects.BUBBLY_CORAL))
			{				
				if(entity instanceof PlayerEntity && !((PlayerEntity) entity).abilities.isCreativeMode)
					entity.setMotion(entity.getMotion().x, entity.getMotion().y - 5, entity.getMotion().z);
				else if(entity instanceof GenericNewEntity)
					entity.setMotion(entity.getMotion().x, entity.getMotion().y - 5, entity.getMotion().z);
			}
		}
		
		if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IDevilFruit props = DevilFruitCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			ItemStack heldItem = player.getHeldItemMainhand();
			boolean updateDisabledAbilities = false;
			
			if(player.isServerWorld())
			{
				if (props.hasDevilFruit())
				{
					if (AbilityHelper.isNearbyKairoseki(player))
					{
						player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
						
						for (int i = 0; i < abilityProps.getEquippedAbilities().length; i++)
						{
							if (abilityProps.getEquippedAbility(i) != null && !abilityProps.getEquippedAbility(i).isDisabled())
							{		
								Ability ability = abilityProps.getEquippedAbility(i);
								if(ability instanceof ContinuousAbility)
									((ContinuousAbility) ability).stopContinuity(player);
								if(ability instanceof ChargeableAbility)
								{
									((ChargeableAbility) ability).setChargeTime(((ChargeableAbility) ability).getMaxChargeTime() / 20);
									ability.startCooldown();
									WyNetwork.sendTo(new SSyncAbilityDataPacket(abilityProps), player);
								}
								ability.startDisable();
							}
						}
					}
					else
					{
						for (int i = 0; i < abilityProps.getEquippedAbilities().length; i++)
						{
							if(abilityProps.getEquippedAbility(i) != null && abilityProps.getEquippedAbility(i).isDisabled())
							{
								abilityProps.getEquippedAbility(i).startCooldown();
							}
						}
					}
				}
			}
		}
	}
}
