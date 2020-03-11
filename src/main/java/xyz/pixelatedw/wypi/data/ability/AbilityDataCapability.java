package xyz.pixelatedw.wypi.data.ability;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.wypi.debug.WyDebug;

public class AbilityDataCapability
{
	@CapabilityInject(IAbilityData.class)
	public static final Capability<IAbilityData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>()
		{

			@Override
			public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				try
				{
					ListNBT unlockedAbilities = new ListNBT();
					for (int i = 0; i < instance.getUnlockedAbilities(AbilityCategory.ALL).size(); i++)
					{
						Ability ability = instance.getUnlockedAbilities(AbilityCategory.ALL).get(i);
						String name = WyHelper.getResourceName(ability.getName());
						CompoundNBT nbtAbility = new CompoundNBT();
						nbtAbility.putString("name", name);
						unlockedAbilities.add(nbtAbility);
					}
					props.put("unlocked_abilities", unlockedAbilities);
	
					ListNBT equippedAbilities = new ListNBT();
					for (int i = 0; i < instance.getEquippedAbilities(AbilityCategory.ALL).size(); i++)
					{
						Ability ability = instance.getEquippedAbilities(AbilityCategory.ALL).get(i);
						String name = WyHelper.getResourceName(ability.getName());
						CompoundNBT nbtAbility = new CompoundNBT();
						nbtAbility.putString("name", name);
						nbtAbility.putString("state", ability.getState().toString());
						equippedAbilities.add(nbtAbility);
					}
					props.put("equipped_abilities", equippedAbilities);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				return props;
			}

			@Override
			public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				try
				{
					instance.clearEquippedAbilities(AbilityCategory.ALL);
					instance.clearUnlockedAbilities(AbilityCategory.ALL);

					ListNBT unlockedAbilities = props.getList("unlocked_abilities", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < unlockedAbilities.size(); i++)
					{
						CompoundNBT nbtAbility = unlockedAbilities.getCompound(i);
						Ability ability = GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, nbtAbility.getString("name")));
						try
						{
							instance.addUnlockedAbility(ability.create());
						}
						catch(Exception e)
						{
							WyDebug.debug("Unregistered ability: " + nbtAbility.getString("name"));
						}
					}
	
					ListNBT equippedAbilities = props.getList("equipped_abilities", Constants.NBT.TAG_COMPOUND);
					List<Ability> activeAbilitiesUnlocked = instance.getUnlockedAbilities(AbilityCategory.ALL).parallelStream().filter(ability -> !(ability instanceof PassiveAbility)).collect(Collectors.toList());
					for (int i = 0; i < equippedAbilities.size(); i++)
					{
						CompoundNBT nbtAbility = equippedAbilities.getCompound(i);
						Ability ability = GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, nbtAbility.getString("name" + i)));
						activeAbilitiesUnlocked.forEach(abl -> 
						{
							if(abl.equals(ability))
							{
								Ability.State state = Ability.State.valueOf(nbtAbility.getString("state"));
								if(state == null)
									state = Ability.State.STANDBY;
								abl.setState(state);
								
								instance.addEquippedAbility(abl);
							}
						});			
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}

		}, AbilityDataBase::new);
	}

	public static IAbilityData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new AbilityDataBase());
	}

	public static LazyOptional<IAbilityData> getLazy(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null);
	}
}
