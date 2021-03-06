// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


public class ChunkCoordIntPair
{

    public ChunkCoordIntPair(int i, int j)
    {
        chunkXPos = i;
        chunkZPos = j;
    }

    public static long chunkXZ2Int(int i, int j)
    {
        long l = i;
        long l1 = j;
        return l & 0xffffffffL | (l1 & 0xffffffffL) << 32;
    }

    public int hashCode()
    {
        long l = chunkXZ2Int(chunkXPos, chunkZPos);
        int i = (int)l;
        int j = (int)(l >> 32);
        return i ^ j;
    }

    public boolean equals(Object obj)
    {
        ChunkCoordIntPair chunkcoordintpair = (ChunkCoordIntPair)obj;
        return chunkcoordintpair.chunkXPos == chunkXPos && chunkcoordintpair.chunkZPos == chunkZPos;
    }

    public final int chunkXPos;
    public final int chunkZPos;
}
