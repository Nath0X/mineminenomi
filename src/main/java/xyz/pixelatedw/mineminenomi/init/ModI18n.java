package xyz.pixelatedw.mineminenomi.init;

import xyz.pixelatedw.wypi.WyRegistry;

public class ModI18n
{
	public static final String CATEGORY_GENERAL = WyRegistry.registerName("category.mmnmkeys", "Mine Mine no Mi Keys");
	public static final String KEY_PLAYER = WyRegistry.registerName("key.playerui", "Player Stats");
	public static final String KEY_COMBATMODE = WyRegistry.registerName("key.combatmode", "Combat Mode");
	public static final String KEY_COMBATSLOT1 = WyRegistry.registerName("key.combatslot.1", "Ability Slot 1");
	public static final String KEY_COMBATSLOT2 = WyRegistry.registerName("key.combatslot.2", "Ability Slot 2");
	public static final String KEY_COMBATSLOT3 = WyRegistry.registerName("key.combatslot.3", "Ability Slot 3");
	public static final String KEY_COMBATSLOT4 = WyRegistry.registerName("key.combatslot.4", "Ability Slot 4");
	public static final String KEY_COMBATSLOT5 = WyRegistry.registerName("key.combatslot.5", "Ability Slot 5");
	public static final String KEY_COMBATSLOT6 = WyRegistry.registerName("key.combatslot.6", "Ability Slot 6");
	public static final String KEY_COMBATSLOT7 = WyRegistry.registerName("key.combatslot.7", "Ability Slot 7");
	public static final String KEY_COMBATSLOT8 = WyRegistry.registerName("key.combatslot.8", "Ability Slot 8");

	public static final String FACTION_NAME = WyRegistry.registerName("faction.name", "Faction");
	public static final String FACTION_EMPTY = WyRegistry.registerName("faction.empty", "No Faction");
	public static final String FACTION_PIRATE = WyRegistry.registerName("faction.pirate", "Pirate");
	public static final String FACTION_MARINE = WyRegistry.registerName("faction.marine", "Marine");
	public static final String FACTION_BOUNTY_HUNTER = WyRegistry.registerName("faction.bounty_hunter", "Bounty Hunter");
	public static final String FACTION_REVOLUTIONARY = WyRegistry.registerName("faction.revolutionary", "Revolutionary");
	
	public static final String RACE_NAME = WyRegistry.registerName("race.name", "Race");
	public static final String RACE_EMPTY = WyRegistry.registerName("race.empty", "No Race");
	public static final String RACE_HUMAN = WyRegistry.registerName("race.human", "Human");
	public static final String RACE_FISHMAN = WyRegistry.registerName("race.fishman", "Fishman");
	public static final String RACE_CYBORG = WyRegistry.registerName("race.cyborg", "Cyborg");
	
	public static final String STYLE_NAME = WyRegistry.registerName("style.name", "Fighting Style");
	public static final String STYLE_EMPTY = WyRegistry.registerName("style.empty", "No Fighting Style");
	public static final String STYLE_SWORDSMAN = WyRegistry.registerName("style.swordsman", "Swordsman");
	public static final String STYLE_SNIPER = WyRegistry.registerName("style.sniper", "Sniper");
	public static final String STYLE_DOCTOR = WyRegistry.registerName("style.doctor", "Doctor");
	public static final String STYLE_ART_OF_WEATHER = WyRegistry.registerName("style.art_of_weather", "Art of Weather");
			
	public static final String GUI_ABILITIES = WyRegistry.registerName("gui.abilities", "Abilities");
	public static final String GUI_COLA = WyRegistry.registerName("gui.cola", "Cola");
	public static final String GUI_DORIKI = WyRegistry.registerName("gui.doriki", "Doriki");
	public static final String GUI_LEAVE = WyRegistry.registerName("gui.leave", "Leave");

	public static final String GUI_QUESTS = WyRegistry.registerName("gui.quests", "Quests");
	public static final String GUI_QUEST_PROGRESS = WyRegistry.registerName("gui.quests.progress", "Progress");
	public static final String GUI_QUEST_ACCEPT = WyRegistry.registerName("gui.quests.accept", "Accept this quest ?");
	
	public static final String GUI_CREW = WyRegistry.registerName("gui.crew", "Crew");
	public static final String GUI_CREW_JOLLY_ROGER = WyRegistry.registerName("gui.crew_jolly_roger", "Crew's Jolly Roger");
	public static final String GUI_CREW_MEMBERS = WyRegistry.registerName("gui.crew_members", "Crew's Members");
	public static final String GUI_CHANGE_JOLLY_ROGER = WyRegistry.registerName("gui.crew_change_jolly_roger", "Change Jolly Roger");
	
	public static final String GUI_ACCEPT = WyRegistry.registerName("gui.accept", "Accept");
	public static final String GUI_DECLINE = WyRegistry.registerName("gui.decline", "Decline");
	public static final String GUI_BUY = WyRegistry.registerName("gui.buy", "Buy");
	public static final String GUI_NAME = WyRegistry.registerName("gui.name", "Name");	
	public static final String GUI_PRICE = WyRegistry.registerName("gui.price", "Price");
	public static final String GUI_EMPTY = WyRegistry.registerName("gui.empty", "Empty");
	public static final String GUI_RED = WyRegistry.registerName("gui.red", "Red");
	public static final String GUI_GREEN = WyRegistry.registerName("gui.green", "Green");
	public static final String GUI_BLUE = WyRegistry.registerName("gui.blue", "Blue");
	public static final String GUI_BASE = WyRegistry.registerName("gui.base", "Base");
	public static final String GUI_BACKGROUND = WyRegistry.registerName("gui.background", "Background");
	public static final String GUI_DETAIL = WyRegistry.registerName("gui.detail", "Detail");
	public static final String GUI_FINISH = WyRegistry.registerName("gui.finish", "Finish");

	public static final String GUI_CLICK_TO_SKIP = WyRegistry.registerName("gui.click_to_skip", "Click to Skip");

	public static final String QUEST_NO_QUESTS_AVAILABLE = WyRegistry.registerName("quest.no_quests_available", "<%s> I don't have any quests for you at the moment.");
	public static final String QUEST_NO_TRIALS_AVAILABLE = WyRegistry.registerName("quest.no_trials_available", "<%s> I don't have any trials for you at the moment.");
	public static final String QUEST_TOO_MANY = WyRegistry.registerName("quest.too_many", "<%s> You cannot accept any more quests");
	public static final String QUEST_ALREADY_IN_PROGRESS = WyRegistry.registerName("quest.already_in_progress", "Already in progress!");
	public static final String QUEST_NO_OBJECTIVES_LEFT = WyRegistry.registerName("quest.no_objectives_left", "No objectives left!");
	
	public static final String TRADER_WELCOME_MESSAGE = WyRegistry.registerName("trader.welcome_message", "Welcome to my humble Shop! Fine traveler, please take whatever you need. I sell to all who need it.");
	public static final String TRADER_NO_PIRATE = WyRegistry.registerName("trader.no_pirate", "I don't trade to Pirate scum");
	public static final String TRADER_NO_MARINE = WyRegistry.registerName("trader.no_marine", "I don't support the Marines.");
	
	public static final String CREW_MESSAGE_ALREADY_IN_CREW = WyRegistry.registerName("crew.message.already_in_crew", "You're already in a Crew!");
	public static final String CREW_MESSAGE_BOUNTY_REQUIREMENT = WyRegistry.registerName("crew.message.bounty_requirement", "Bounty requirement not met!");
	public static final String CREW_MESSAGE_NEW_JOIN = WyRegistry.registerName("crew.message.new_join", "%s joined your crew.");
	public static final String CREW_MESSAGE_NEW_CREW = WyRegistry.registerName("crew.message.new_crew", "A new crew just formed, %s!");
	public static final String CREW_CAPTAIN = WyRegistry.registerName("gui.captain", "Captain");

	public static final String ABILITY_MESSAGE_NEED_SWORD = WyRegistry.registerName("ability.item.message.need_sword", "You need a sword to use this ability!");
	public static final String ABILITY_MESSAGE_ONLY_IN_ROOM = WyRegistry.registerName("ability.item.message.only_in_room", "%s can only be used inside ROOM!");
	public static final String ABILITY_MESSAGE_NEED_MEDIC_BAG = WyRegistry.registerName("ability.item.message.need_medic_bag", "You need a medic bag equipped to use this ability!");
	public static final String ABILITY_MESSAGE_NEED_CLIMA_TACT = WyRegistry.registerName("ability.item.message.need_clima_tact", "You need a clima tact to use this ability!");
	public static final String ABILITY_MESSAGE_NOT_ENOUGH_COLA = WyRegistry.registerName("ability.message.not_enough_cola", "Not enough Cola!");
	public static final String ABILITY_MESSAGE_NOT_ENOUGH_BLOCKS = WyRegistry.registerName("ability.message.not_enough_blocks", "Not enough blocks in the inventory!");
	public static final String ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE = WyRegistry.registerName("ability.message.not_zoan_form_single", "%s can only be used while %s is active !");
	public static final String ABILITY_MESSAGE_NOT_ZOAN_FORM_DOUBLE = WyRegistry.registerName("ability.message.not_zoan_form_double", "%s can only be used while %s or %s is active !");
	public static final String ABILITY_MESSAGE_ONLY_IN_AIR = WyRegistry.registerName("ability.message.only_in_air", "%s can only be used while airborne!");
	public static final String ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT = WyRegistry.registerName("ability.item.message.need_sorcery_clima_tact", "You need a Sorcery or higher grade clima tact to use this ability!");
	public static final String ABILITY_MESSAGE_ONE_HAKI_TYPE = WyRegistry.registerName("ability.message.one_haki_type", "%s can't be used while another same type haki is active!");
	public static final String ABILITY_MESSAGE_CANNOT_USE_HERE = WyRegistry.registerName("ability.message.cannot_use_here", "Cannot use abilites in a restricted area!");
	public static final String ABILITY_MESSAGE_SUVIVAL_ONLY = WyRegistry.registerName("ability.message.survival_only", "Cannot use this ability in creative!");

	public static final String ITEM_KAIROSEKI_ITEM = WyRegistry.registerName("item.kairoseki_item", "Kairoseki Item");
	public static final String ITEM_MESSAGE_NEED_KEY = WyRegistry.registerName("item.message.need_key", "You need a key!");
	public static final String ITEM_MESSAGE_POUCH_BELLY_GAINED = WyRegistry.registerName("item.message.pouch_belly_gained", "You've obtained %s belly !");
	
	public static void init()
	{
		// ItemGroups
		WyRegistry.registerName("itemGroup.devil_fruits", "Devil Fruits");
		WyRegistry.registerName("itemGroup.weapons", "Equipment");
		
		// Messages
		WyRegistry.registerName("death.attack.ability_projectile", "%1$s was killed by %2$s");
		
		// Special
		WyRegistry.registerName("scancode.0", "No Key");
	}
	
}
