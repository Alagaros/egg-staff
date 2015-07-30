package com.dalthow.eggstaff.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.dalthow.eggstaff.base.Reference;

/**
 * Egg Staff
 *
 * 
 * @author Dalthow Game Studios 
 * @class ItemCaptureEgg.java
 * 
 **/

public class ItemCapturingEgg extends Item 
{
	// Constructor that adds data to the item.
	
	public ItemCapturingEgg()
	{
		super();
		
		setUnlocalizedName("capturingEgg");
		setTextureName(Reference.modId + ":" + "capturing egg");
		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(16);
	}
}
