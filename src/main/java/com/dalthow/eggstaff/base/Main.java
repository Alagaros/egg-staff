package com.dalthow.eggstaff.base;

import java.io.File;
import java.util.HashMap;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.util.EnumHelper;

import com.dalthow.eggstaff.handler.RecipeHandler;
import com.dalthow.eggstaff.handler.RegisterHandler;
import com.dalthow.eggstaff.item.ItemCapturingEgg;
import com.dalthow.eggstaff.item.ItemEggStaff;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;

/**
 * Egg Staff
 *
 * @author Trevi Awater
 **/

@Mod(modid = Reference.modId, name = Reference.name, version = Reference.version)
public class Main 
{
	// Declaration for items.
	public static Item itemEggStaff;
	public static Item itemCapturingEgg;

	// Declaration for tool materials .
	public static ToolMaterial materialStaff;

	// Declaration for the configuration file variables.
	public static Property recipeEggStaff;
	public static Property recipeCapturingEgg;
	public static Property doExplosion;
	
	public static HashMap <String, Boolean> mobAllow = new HashMap <String, Boolean>();
	
	public static int requiredLevelHostile;
	
	
	// Initialization that happens before any of the world is loaded.
	@Mod.EventHandler
	public void preInit(FMLInitializationEvent event)
	{
		// Creating the staff material.
		materialStaff = EnumHelper.addToolMaterial("materialStaff", 0, 20, 0.5F, -3.0F, 0);

		// Setting up the items.
		itemEggStaff = new ItemEggStaff(materialStaff);
		itemCapturingEgg  = new ItemCapturingEgg();
		
		// Creates a configuration file if there isn't one already.
		Configuration config = new Configuration(new File("config/" + Reference.modId + ".cfg"));
		
		config.load();
		
		requiredLevelHostile = config.get("Properties", "Level required for hostile mobs", 15).getInt();
		doExplosion = config.get("Properties", "Explosion on capture", true);
		recipeEggStaff = config.get("Recipes", "Egg Staff", true);
		recipeCapturingEgg = config.get("Recipes", "Capturing Egg", true);

		// Putting in the friendly mobs.
		mobAllow.put("Bat", Boolean.valueOf(config.get("Passive Mobs", "Bat", true).getBoolean(true)));
	    mobAllow.put("Pig", Boolean.valueOf(config.get("Passive Mobs", "Pig", true).getBoolean(true)));
	    mobAllow.put("Sheep", Boolean.valueOf(config.get("Passive Mobs", "Sheep", true).getBoolean(true)));
	    mobAllow.put("Cow", Boolean.valueOf(config.get("Passive Mobs", "Cow", true).getBoolean(true)));
	    mobAllow.put("Chicken", Boolean.valueOf(config.get("Passive Mobs", "Chicken", true).getBoolean(true)));
	    mobAllow.put("Squid", Boolean.valueOf(config.get("Passive Mobs", "Squid", true).getBoolean(true)));
	    mobAllow.put("Wolf", Boolean.valueOf(config.get("Passive Mobs", "Wolf", true).getBoolean(true)));
	    mobAllow.put("MushroomCow", Boolean.valueOf(config.get("Passive Mobs", "Mooshroom", true).getBoolean(true)));
	    mobAllow.put("Ozelot", Boolean.valueOf(config.get("Passive Mobs", "Ozelot", true).getBoolean(true)));
	    mobAllow.put("Villager", Boolean.valueOf(config.get("Passive Mobs", "Villager", false).getBoolean(false)));
	    mobAllow.put("EntityHorse", Boolean.valueOf(config.get("Passive Mobs", "Horse", false).getBoolean(false)));

	    // Putting in the hostile mobs.
	    mobAllow.put("Creeper", Boolean.valueOf(config.get("Hostile Mobs", "Creeper", true).getBoolean(true)));
	    mobAllow.put("Skeleton", Boolean.valueOf(config.get("Hostile Mobs", "Skeleton", true).getBoolean(true)));
	    mobAllow.put("Zombie", Boolean.valueOf(config.get("Hostile Mobs", "Zombie", true).getBoolean(true)));
	    mobAllow.put("Spider", Boolean.valueOf(config.get("Hostile Mobs", "Spider", true).getBoolean(true)));
	    mobAllow.put("Slime", Boolean.valueOf(config.get("Hostile Mobs", "Slime", false).getBoolean(false)));
	    mobAllow.put("PigZombie", Boolean.valueOf(config.get("Hostile Mobs", "Pig Zombie", true).getBoolean(true)));
	    mobAllow.put("Enderman", Boolean.valueOf(config.get("Hostile Mobs", "Enderman", false).getBoolean(false)));
	    mobAllow.put("CaveSpider", Boolean.valueOf(config.get("Hostile Mobs", "Cave Spider", true).getBoolean(true)));
	    mobAllow.put("Silverfish", Boolean.valueOf(config.get("Hostile Mobs", "Silverfish", true).getBoolean(true)));
	    mobAllow.put("Blaze", Boolean.valueOf(config.get("Hostile Mobs", "Blaze", false).getBoolean(false)));
	    mobAllow.put("LavaSlime", Boolean.valueOf(config.get("Hostile Mobs", "Magma Cube", false).getBoolean(false)));
	    mobAllow.put("Witch", Boolean.valueOf(config.get("Hostile Mobs", "Witch", true).getBoolean(true)));
	    mobAllow.put("Ghast", Boolean.valueOf(config.get("Hostile Mobs", "Ghast", false).getBoolean(false)));

		config.save();

		// Adding the custom recipe's to the game.
		RecipeHandler.addRecipes();

		// Registering the items in the item registry.
		RegisterHandler.registerItem(itemEggStaff);
		RegisterHandler.registerItem(itemCapturingEgg);
	}
}