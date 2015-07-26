/**
 * Egg Staff
 *
 * 
 * @Author Dalthow Game Studios 
 * @Class ItemCaptureEgg.java
 * 
 **/

package com.dalthow.eggstaff.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.dalthow.eggstaff.base.Reference;

public class ItemCapturingEgg extends Item 
{
	// Constructor 
	
	public ItemCapturingEgg()
	{
		super();
		setUnlocalizedName("capturingEgg");
		setTextureName(Reference.modId + ":" + "capturing egg");
		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(16);
	}
}
