// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            IChunkProvider, PlayerList, EmptyChunk, ChunkCoordIntPair, 
//            Chunk, NibbleArray, World, IProgressUpdate

public class ChunkProviderClient
    implements IChunkProvider
{

    public ChunkProviderClient(World world)
    {
        chunkMapping = new PlayerList();
        field_889_c = new ArrayList();
        world.getClass();
        blankChunk = new EmptyChunk(world, new byte[256 * 128], 0, 0);
        worldObj = world;
    }

    public boolean chunkExists(int i, int j)
    {
        if(this != null)
        {
            return true;
        } else
        {
            return chunkMapping.func_35575_b(ChunkCoordIntPair.chunkXZ2Int(i, j));
        }
    }

    public void func_539_c(int i, int j)
    {
        Chunk chunk = provideChunk(i, j);
        if(!chunk.getFalse())
        {
            chunk.onChunkUnload();
        }
        chunkMapping.remove(ChunkCoordIntPair.chunkXZ2Int(i, j));
        field_889_c.remove(chunk);
    }

    public Chunk loadChunk(int i, int j)
    {
        worldObj.getClass();
        byte abyte0[] = new byte[256 * 128];
        Chunk chunk = new Chunk(worldObj, abyte0, i, j);
        Arrays.fill(chunk.skylightMap.data, (byte)-1);
        chunkMapping.add(ChunkCoordIntPair.chunkXZ2Int(i, j), chunk);
        chunk.isChunkLoaded = true;
        return chunk;
    }

    public Chunk provideChunk(int i, int j)
    {
        Chunk chunk = (Chunk)chunkMapping.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(i, j));
        if(chunk == null)
        {
            return blankChunk;
        } else
        {
            return chunk;
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        return true;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return false;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
    }

    public String makeString()
    {
        return (new StringBuilder()).append("MultiplayerChunkCache: ").append(chunkMapping.getNumHashElements()).toString();
    }

    private Chunk blankChunk;
    private PlayerList chunkMapping;
    private List field_889_c;
    private World worldObj;
}
