// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            MapGenBase, ChunkCoordIntPair, StructureStart, StructureBoundingBox, 
//            IChunkProvider, World

public abstract class MapGenStructure extends MapGenBase
{

    public MapGenStructure()
    {
        coordMap = new HashMap();
    }

    public void generate(IChunkProvider ichunkprovider, World world, int i, int j, byte abyte0[])
    {
        super.generate(ichunkprovider, world, i, j, abyte0);
    }

    protected void recursiveGenerate(World world, int i, int j, int k, int l, byte abyte0[])
    {
        if(coordMap.containsKey(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j))))
        {
            return;
        }
        rand.nextInt();
        if(canSpawnStructureAtCoords(i, j))
        {
            StructureStart structurestart = getStructureStart(i, j);
            coordMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j)), structurestart);
        }
    }

    public boolean generateStructuresInChunk(World world, Random random, int i, int j)
    {
        int k = (i << 4) + 8;
        int l = (j << 4) + 8;
        boolean flag = false;
        Iterator iterator = coordMap.values().iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            StructureStart structurestart = (StructureStart)iterator.next();
            if(structurestart.isSizeableStructure() && structurestart.getBoundingBox().isInsideStructureBB(k, l, k + 15, l + 15))
            {
                structurestart.generateStructure(world, random, new StructureBoundingBox(k, l, k + 15, l + 15));
                flag = true;
            }
        } while(true);
        return flag;
    }

    protected abstract boolean canSpawnStructureAtCoords(int i, int j);

    protected abstract StructureStart getStructureStart(int i, int j);

    protected HashMap coordMap;
}
