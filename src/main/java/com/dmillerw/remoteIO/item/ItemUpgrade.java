package com.dmillerw.remoteIO.item;

import com.dmillerw.remoteIO.core.CreativeTabRIO;
import com.dmillerw.remoteIO.lib.ItemStackReference;
import com.dmillerw.remoteIO.lib.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import java.util.List;

public class ItemUpgrade extends Item {

	public Icon[] icons;
	
	public ItemUpgrade(int id) {
		super(id);
		
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setCreativeTab(CreativeTabRIO.tab);
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return Upgrade.values()[stack.getItemDamage()] == Upgrade.RANGE_WITHER;
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return this.icons[damage];
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (Upgrade upgrade : Upgrade.values()) {
			if ((upgrade.recipeComponents != null || upgrade == Upgrade.BLANK) && upgrade.enabled) {
				list.add(upgrade.toItemStack());
			}
		}
	}

	@Override
	public void registerIcons(IconRegister register) {
		this.icons = new Icon[Upgrade.values().length];
		
		for (Upgrade upgrade : Upgrade.values()) {
			this.icons[upgrade.ordinal()] = register.registerIcon(ModInfo.RESOURCE_PREFIX + "upgrade/" + upgrade.texture);
		}
	}

    @Override
    public String getItemDisplayName(ItemStack stack) {
        return super.getItemDisplayName(stack) + " " + I18n.getString("upgrade.word");
    }

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "upgrade." + Upgrade.values()[stack.getItemDamage()].texture;
	}	
	
	public static enum Upgrade {
		BLANK(
			"blank", 
			null,
			new boolean[3]
		), 
		
		ITEM(
			"item", 
			new ItemStack[] {new ItemStack(Block.chest)},
			new boolean[] {
			        true,
			        true,
			        true
			}
		), 
				
		FLUID(
			"fluid",
			new ItemStack[] {new ItemStack(Item.bucketEmpty)},
			new boolean[] {
                    true,
                    false,
                    false
            }
		), 
				
		POWER_MJ(
			"powerBC",
			new ItemStack[0],
			new boolean[] {
                    true,
                    false,
                    false
            }
		), 
				
		RANGE_T1(
			"rangeT1",
			new ItemStack[] {new ItemStack(Item.glowstone)},
			new boolean[] {
                    true,
                    true,
                    true
            }
		), 
				
		CROSS_DIMENSIONAL(
			"dimension", 
			new ItemStack[] {new ItemStack(Block.obsidian), new ItemStack(Block.enderChest)},
			new boolean[] {
                    true,
                    true,
                    true
            }
		), 
				
		ISIDED_AWARE(
			"side", 
			new ItemStack[] {new ItemStack(Block.hopperBlock), Upgrade.ITEM.toItemStack()},
			new boolean[] {
                    true,
                    false,
                    false
            }
		), 
				
		REDSTONE(
			"redstone",
			new ItemStack[] {new ItemStack(Item.redstone)},
			new boolean[] {
                    true,
                    true,
                    true
            }
		), 
				
		CAMO(
			"camo",
			new ItemStack[] {ItemStackReference.COMPONENT_CAMO},
			new boolean[] {
                    true,
                    true,
                    true
            }
		), 
				
		LOCK(
			"lock",
			new ItemStack[] {ItemStackReference.COMPONENT_LOCK, new ItemStack(Block.chest)},
			new boolean[] {
                    true,
                    true,
                    true
            }
		), 
				
		POWER_RF(
			"powerRF", 
			new ItemStack[0],
	        new boolean[] {
                    true,
                    true,
                    true
            }
		), 
				
		POWER_EU(
			"powerEU",
			new ItemStack[0],
	        new boolean[] {
                    true,
                    true,
                    true
            }
		),
		
		POWER_UE(
			"powerUE",
			null,
			new boolean[] {
                    false,
                    false,
                    false
            }
		),
		
		LINK_PERSIST(
			"persist",
			null,
			new boolean[] {
                    false,
                    false,
                    false
            }
		),
		
		RANGE_T2(
			"rangeT2",
			new ItemStack[0],
	        new boolean[] {
                    true,
                    true,
                    true
            }
		),
		
		RANGE_T3(
			"rangeT3",
			new ItemStack[0],
	        new boolean[] {
                    true,
                    true,
                    true
            }
		),
		
		RANGE_WITHER(
			"rangeWither",
			new ItemStack[0],
	        new boolean[] {
                    true,
                    true,
                    true
            }
		),

        AE(
            "ae",
            new ItemStack[0],
            new boolean[] {
                    true,
                    false,
                    false
            }
        );
		
		public String texture;
		
		public ItemStack[] recipeComponents;

		/** Simple boolean array.<br />Index 0: IO Block<br />Index 1: Remote Inventory<br />Index 2: Turtle Bridge */
		public boolean[] validMachines;
		
        public boolean enabled = true;

		private Upgrade(String texture, ItemStack[] recipeComponents, boolean[] validArray) {
			this.texture = texture;
			this.recipeComponents = recipeComponents;
			this.validMachines = validArray;
		}
		
		public ItemStack toItemStack() {
			return new ItemStack(ItemHandler.itemUpgrade, 1, this.ordinal());
		}
		
	}
	
}
