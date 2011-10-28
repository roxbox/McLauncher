// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            ItemBlock, Block

public class ItemVine extends ItemBlock
{

    public ItemVine(int i, boolean flag)
    {
        super(i);
        field_35436_a = Block.blocksList[getBlockID()];
        if(flag)
        {
            setMaxDamage(0);
            setHasSubtypes(true);
        }
    }

    public int getColorFromDamage(int i)
    {
        return field_35436_a.getBlockColor();
    }

    public int getIconFromDamage(int i)
    {
        return field_35436_a.getBlockTextureFromSideAndMetadata(0, i);
    }

    public int getPlacedBlockMetadata(int i)
    {
        return i;
    }

    private final Block field_35436_a;
}
