// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.awt.image.BufferedImage;

// Referenced classes of package net.minecraft.src:
//            World

public class IsoImageBuffer
{

    public IsoImageBuffer(World world, int i, int j)
    {
        field_1352_e = false;
        field_1351_f = false;
        field_1350_g = 0;
        field_1349_h = false;
        worldObj = world;
        setChunkPosition(i, j);
    }

    public void setChunkPosition(int i, int j)
    {
        field_1352_e = false;
        chunkX = i;
        chunkZ = j;
        field_1350_g = 0;
        field_1349_h = false;
    }

    public void setWorldAndChunkPosition(World world, int i, int j)
    {
        worldObj = world;
        setChunkPosition(i, j);
    }

    public BufferedImage field_1348_a;
    public World worldObj;
    public int chunkX;
    public int chunkZ;
    public boolean field_1352_e;
    public boolean field_1351_f;
    public int field_1350_g;
    public boolean field_1349_h;
}
