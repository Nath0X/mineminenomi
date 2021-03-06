package xyz.pixelatedw.mineminenomi.abilities.yomi;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yomi.SoulParadeParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class SoulParadeAbility extends ContinuousAbility
{
	public static final SoulParadeAbility INSTANCE = new SoulParadeAbility();
	public static final ParticleEffect PARTICLES = new SoulParadeParticleEffect();

	public SoulParadeAbility()
	{
		super("Soul Parade", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(15);

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if (!ItemsHelper.isSword(player.getHeldItemMainhand()))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD).getFormattedText());
			return false;
		}
		return true;
	}

	
	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 10, 3, false, false));
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 15.0);
		this.setMaxCooldown(cooldown);
		return true;
	}
}