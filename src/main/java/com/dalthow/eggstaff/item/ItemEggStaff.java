/**
 * Egg Staff
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class ItemEggStaff.java
 * 
 **/

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

public class ItemEggStaff extends ItemSword
{
	// Declaration
	
	public int eggMetadata;
	
	
	// Constructor 
	
	public ItemEggStaff(ToolMaterial material)
	{
		super(material);
		
		setUnlocalizedName("eggStaff");
		setTextureName(Reference.modId + ":" + "egg staff");
		setCreativeTab(CreativeTabs.tabTools);
		setMaxDamage(10 - 1);
		setMaxStackSize(1);
	}
	
	
	// Creates a harmful explosion around the target
	
	public void doExplosion(World world, EntityLivingBase entityLiving)
	{
		world.createExplosion(entityLiving, entityLiving.posX, entityLiving.posY, entityLiving.posZ, 1.0F, false);
	}
	
	
	// Gets triggered when a entity is hit and it matches the type, does not work on players
	
    public boolean hitEntity(ItemStack itemStack, EntityLivingBase entityLiving, EntityLivingBase player)
    {
    	EntityPlayer playerObject = (EntityPlayer) player;
    	World worldObject = playerObject.worldObj;
    	
    	if(playerObject.inventory.hasItem(Main.itemCapturingEgg) || playerObject.capabilities.isCreativeMode)
    	{
			if(!worldObject.isRemote)
			{	
				eggMetadata = EntityList.getEntityID(entityLiving);
				String entityName = EntityList.getEntityString(entityLiving);
				
				if(entityName != null)
				{
					 if(((Boolean)Main.mobAllow.get(entityName)).booleanValue() == true)
				     {
						 if(eggMetadata > 0 && EntityList.entityEggs.containsKey(eggMetadata))
						 {
							 if(entityLiving instanceof EntityAnimal)
							 {
								 putInEgg(worldObject, itemStack, entityLiving, playerObject);
							 }
							
							 else
							 {
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
    
    
    // Puts the entity in a spawn egg and drops it on the previous location of the entity
    
    public void putInEgg(World world, ItemStack itemStack, EntityLivingBase entityLiving, EntityLivingBase player)
    {
    	EntityPlayer playerObject = (EntityPlayer) player;
    	
    	ItemStack itemEgg = new ItemStack(Items.spawn_egg, 1, eggMetadata);
		EntityItem entityEgg = new EntityItem(world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, itemEgg);
		
		if(Main.doExplosion.getBoolean(false) == true)
		{
			doExplosion(world, entityLiving);
		}
	
		entityLiving.setDead();

		if(!playerObject.capabilities.isCreativeMode)
		{
			playerObject.inventory.consumeInventoryItem(Main.itemCapturingEgg);
			itemStack.damageItem(1, player);
		}
		
		world.spawnEntityInWorld(entityEgg);
    }
    
    
    // Adds a tooltip to the item

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer entityPlayer, List list, boolean isValid)
    {
		list.add(((item.getMaxDamage() + 1) - item.getItemDamage()) + "/" + (item.getMaxDamage() + 1) + " " + "Uses remaining.");
    
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