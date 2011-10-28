// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            Item, EntityPlayer, World, Block, 
//            ItemStack

public class ItemSeeds extends Item
{

    public ItemSeeds(int i, int j)
    {
        super(i);
        blockType = j;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if(l != 1)
        {
            return false;
        }
        if(!entityplayer.func_35190_e(i, j, k) || !entityplayer.func_35190_e(i, j + 1, k))
        {
            return false;
        }
        int i1 = world.getBlockId(i, j, k);
        if(i1 == Block.tilledField.blockID && world.isAirBlock(i, j + 1, k))
        {
            world.setBlockWithNotify(i, j + 1, k, blockType);
            itemstack.stackSize--;
            return true;
        } else
        {
            return false;
        }
    }

    private int blockType;
}
