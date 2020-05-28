package xyz.pixelatedw.mineminenomi.abilities.mogu;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

import java.util.UUID;

public class MoguHeavyPointAbility extends ZoanAbility
{
	public static final MoguHeavyPointAbility INSTANCE = new MoguHeavyPointAbility();

	private static final AttributeModifier ARMOR_MODIFIER = new AttributeModifier(UUID.fromString("0847f786-0a5a-4e83-9ea6-f924c259a798"), "Heavy Point Multiplier", 5, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier STRENGTH_MODIFIER = new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Heavy Point Multiplier", 3, AttributeModifier.Operation.ADDITION).setSaved(false);
	private static final AttributeModifier ATTACK_SPEED_MODIFIER = new AttributeModifier(UUID.fromString("1d78a133-8a0e-4b8f-8790-1360007d4741"), "Heavy Point Multiplier", 0.2f, AttributeModifier.Operation.ADDITION).setSaved(false);

	public MoguHeavyPointAbility()
	{
		super("Mogu Heavy Point", AbilityCategory.DEVIL_FRUIT, MoguHeavyZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a mole, which focuses on strength and digging speed, Allows the user to use 'Mogura Banana' and 'Mogura Tonpo'");
	}

	@Override
	protected boolean onStartContinuityEvent(PlayerEntity player) {
		player.getAttribute(SharedMonsterAttributes.ARMOR).applyModifier(ARMOR_MODIFIER);
		player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).applyModifier(STRENGTH_MODIFIER);
		player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier(ATTACK_SPEED_MODIFIER);

		return super.onStartContinuityEvent(player);
	}

	@Override
	protected boolean onEndContinuityEvent(PlayerEntity player) {
		player.getAttribute(SharedMonsterAttributes.ARMOR).removeModifier(ARMOR_MODIFIER);
		player.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).removeModifier(STRENGTH_MODIFIER);
		player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeModifier(ATTACK_SPEED_MODIFIER);

		return super.onEndContinuityEvent(player);
	}

}
