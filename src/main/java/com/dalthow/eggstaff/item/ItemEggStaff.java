package com.dalthow.eggstaff.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import com.dalthow.eggstaff.base.Main;
import com.dalthow.eggstaff.base.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Egg Staff
 *
 * @author Trevi Awater
 **/

public class ItemEggStaff extends ItemSword
{
	// Declaration.
	public int eggMetadata;


	// Constructor that adds data to the item.
	public ItemEggStaff(ToolMaterial material)
	{
		super(material);
		
		setUnlocalizedName("eggStaff");
		setTextureName(Reference.modId + ":" + "egg staff");
		setCreativeTab(CreativeTabs.tabTools);
		setMaxDamage(10 - 1);
		setMaxStackSize(1);
	}
	
	
	/**
     * Creates a harmful explosion around the target.
     *
     * @param world        The world object.
     * @param entityLiving From which mob the explosion should come from.
     */
	public void doExplosion(World world, EntityLivingBase entityLiving)
	{
		world.createExplosion(entityLiving, entityLiving.posX, entityLiving.posY, entityLiving.posZ, 1.0F, false);
	}

	/**
	 * Puts the entity in a spawn egg and drops it on the previous location of the entity.
	 *
	 * @param world        The world object.
	 * @param itemStack	   The Egg Staff.
	 * @param entityLiving The mob that should be put into a spawn egg.
	 * @param player 	   The player.
	 */
	public void putInEgg(World world, ItemStack itemStack, EntityLivingBase entityLiving, EntityLivingBase player)
	{
		// Casting the EntityLivingBase to an EntityPlayer.
		EntityPlayer playerObject = (EntityPlayer) player;

		// Creating the spawn egg.
		ItemStack itemEgg = new ItemStack(Items.spawn_egg, 1, eggMetadata);
		EntityItem entityEgg = new EntityItem(world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, itemEgg);

		if(Main.doExplosion.getBoolean(false))
		{
			doExplosion(world, entityLiving);
		}

		// Removing the mob from being ticked.
		entityLiving.setDead();

		// Consuming an egg and damaging the staff if the player is not in creative mode.
		if(!playerObject.capabilities.isCreativeMode)
		{
			playerObject.inventory.consumeInventoryItem(Main.itemCapturingEgg);
			itemStack.damageItem(1, player);
		}

		// Spawning the egg in the world.
		world.spawnEntityInWorld(entityEgg);
	}
	
	
	// Gets triggered when a entity is hit and it matches the type, does not work on players.
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLiving, EntityLivingBase player)
    {
    	// Casting the EntityLivingBase to an EntityPlayer.
    	EntityPlayer playerObject = (EntityPlayer) player;
    	World worldObject = playerObject.worldObj;

    	// Checking if the player has a capturing egg or is in creative mode.
    	if(playerObject.inventory.hasItem(Main.itemCapturingEgg) || playerObject.capabilities.isCreativeMode)
    	{
    		// Checking if we are on a server. If so ignore this.
			if(!worldObject.isRemote)
			{	
				eggMetadata = EntityList.getEntityID(entityLiving);
				String entityName = EntityList.getEntityString(entityLiving);

				// Checking if the entity is valid.
				if(entityName != null)
				{
					 if(((Boolean)Main.mobAllow.get(entityName)))
				     {
						 if(eggMetadata > 0 && EntityList.entityEggs.containsKey(eggMetadata))
						 {
							 // If the entity is an animal, then it can be captured without a level requirement.
							 if(entityLiving instanceof EntityAnimal)
							 {
								 putInEgg(worldObject, itemStack, entityLiving, playerObject);
							 }
							
							 else
							 {
								 // Checking if the user has enough experience or is in creative mode.
								 if(playerObject.experienceLevel >= Main.requiredLevelHostile || playerObject.capabilities.isCreativeMode)
								 {
									 putInEgg(worldObject, itemStack, entityLiving, playerObject);
									
									 return true;
								 }
								
								 return false;
							 }
						 }
				     } 
				}
			}
    	}
    	
        return true;
    }
    
    // Adds a tool-tip to the item.
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer entityPlayer, List list, boolean isValid)
    {
		// Shows the amount of uses left.
		list.add(((item.getMaxDamage() + 1) - item.getItemDamage()) + "/" + (item.getMaxDamage() + 1) + " " + "Uses remaining.");

		// Extra information when shift is pressed over an item.
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			list.clear();
		
			list.add("Egg Staff");
	    	list.add("Used to put animals and monsters in");
	    	list.add("their original spawn egg. consumes");
	    	list.add("capturing eggs. In order to capture");
	    	list.add("some of the hostile mobs you need to");
	    	list.add("be level 15 or higher.");
		}
    }
}