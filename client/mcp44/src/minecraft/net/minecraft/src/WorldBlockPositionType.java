// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            WorldClient

class WorldBlockPositionType
{

    public WorldBlockPositionType(WorldClient worldclient, int i, int j, int k, int l, int i1)
    {
        worldClient = worldclient;
        posX = i;
        posY = j;
        posZ = k;
        acceptCountdown = 80;
        blockID = l;
        metadata = i1;
    }

    int posX;
    int posY;
    int posZ;
    int acceptCountdown;
    int blockID;
    int metadata;
    final WorldClient worldClient; /* synthetic field */
}
