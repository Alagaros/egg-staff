package com.dalthow.eggstaff.handler;

import net.minecraft.item.Item;

import com.dalthow.eggstaff.base.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Egg Staff
 *
 * 
 * @author Dalthow Game Studios 
 * @class RegisterHandler.java;
 * 
 **/

public class RegisterHandler 
{
	/**
     * registerItem Registers a item so that it gets loaded in the game.
     *
     * @param {Item} item The item that should be registered.
     *
     * @return {void}
     */
	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, Reference.modId + "_" + item.getUnlocalizedName().substring(5));
	}
}
