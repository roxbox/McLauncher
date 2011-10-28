// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.IOException;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            IChunkProvider, PlayerList, EmptyChunk, ChunkCoordIntPair, 
//            World, ChunkCoordinates, Chunk, IChunkLoader, 
//            IProgressUpdate

public class ChunkProvider
    implements IChunkProvider
{

    public ChunkProvider(World world, IChunkLoader ichunkloader, IChunkProvider ichunkprovider)
    {
        droppedChunksSet = new HashSet();
        chunkMap = new PlayerList();
        chunkList = new ArrayList();
        world.getClass();
        emptyChunk = new EmptyChunk(world, new byte[256 * 128], 0, 0);
        worldObj = world;
        chunkLoader = ichunkloader;
        chunkProvider = ichunkprovider;
    }

    public boolean chunkExists(int i, int j)
    {
        return chunkMap.func_35575_b(ChunkCoordIntPair.chunkXZ2Int(i, j));
    }

    public void func_35391_d(int i, int j)
    {
        ChunkCoordinates chunkcoordinates = worldObj.getSpawnPoint();
        int k = (i * 16 + 8) - chunkcoordinates.posX;
        int l = (j * 16 + 8) - chunkcoordinates.posZ;
        char c = '\200';
        if(k < -c || k > c || l < -c || l > c)
        {
            droppedChunksSet.add(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j)));
        }
    }

    public Chunk loadChunk(int i, int j)
    {
        long l = ChunkCoordIntPair.chunkXZ2Int(i, j);
        droppedChunksSet.remove(Long.valueOf(l));
        Chunk chunk = (Chunk)chunkMap.getValueByKey(l);
        if(chunk == null)
        {
            int k = 0x1c9c3c;
            if(i < -k || j < -k || i >= k || j >= k)
            {
                return emptyChunk;
            }
            chunk = loadChunkFromFile(i, j);
            if(chunk == null)
            {
                if(chunkProvider == null)
                {
                    chunk = emptyChunk;
                } else
                {
                    chunk = chunkProvider.provideChunk(i, j);
                }
            }
            chunkMap.add(l, chunk);
            chunkList.add(chunk);
            if(chunk != null)
            {
                chunk.func_4143_d();
                chunk.onChunkLoad();
            }
            chunk.populateChunk(this, this, i, j);
        }
        return chunk;
    }

    public Chunk provideChunk(int i, int j)
    {
        Chunk chunk = (Chunk)chunkMap.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(i, j));
        if(chunk == null)
        {
            return loadChunk(i, j);
        } else
        {
            return chunk;
        }
    }

    private Chunk loadChunkFromFile(int i, int j)
    {
        if(chunkLoader == null)
        {
            return null;
        }
        try
        {
            Chunk chunk = chunkLoader.loadChunk(worldObj, i, j);
            if(chunk != null)
            {
                chunk.lastSaveTime = worldObj.getWorldTime();
            }
            return chunk;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    private void func_28063_a(Chunk chunk)
    {
        if(chunkLoader == null)
        {
            return;
        }
        try
        {
            chunkLoader.saveExtraChunkData(worldObj, chunk);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void func_28062_b(Chunk chunk)
    {
        if(chunkLoader == null)
        {
            return;
        }
        try
        {
            chunk.lastSaveTime = worldObj.getWorldTime();
            chunkLoader.saveChunk(worldObj, chunk);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        Chunk chunk = provideChunk(i, j);
        if(!chunk.isTerrainPopulated)
        {
            chunk.isTerrainPopulated = true;
            if(chunkProvider != null)
            {
                chunkProvider.populate(ichunkprovider, i, j);
                chunk.setChunkModified();
            }
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        int i = 0;
        for(int j = 0; j < chunkList.size(); j++)
        {
            Chunk chunk = (Chunk)chunkList.get(j);
            if(flag && !chunk.neverSave)
            {
                func_28063_a(chunk);
            }
            if(!chunk.needsSaving(flag))
            {
                continue;
            }
            func_28062_b(chunk);
            chunk.isModified = false;
            if(++i == 24 && !flag)
            {
                return false;
            }
        }

        if(flag)
        {
            if(chunkLoader == null)
            {
                return true;
            }
            chunkLoader.saveExtraData();
        }
        return true;
    }

    public boolean unload100OldestChunks()
    {
        for(int i = 0; i < 100; i++)
        {
            if(!droppedChunksSet.isEmpty())
            {
                Long long1 = (Long)droppedChunksSet.iterator().next();
                Chunk chunk1 = (Chunk)chunkMap.getValueByKey(long1.longValue());
                chunk1.onChunkUnload();
                func_28062_b(chunk1);
                func_28063_a(chunk1);
                droppedChunksSet.remove(long1);
                chunkMap.remove(long1.longValue());
                chunkList.remove(chunk1);
            }
        }

        for(int j = 0; j < 10; j++)
        {
            if(field_35392_h >= chunkList.size())
            {
                field_35392_h = 0;
                break;
            }
            Chunk chunk = (Chunk)chunkList.get(field_35392_h++);
            EntityPlayer entityplayer = worldObj.getClosestPlayer((double)(chunk.xPosition << 4) + 8D, 64D, (double)(chunk.zPosition << 4) + 8D, 288D);
            if(entityplayer == null)
            {
                func_35391_d(chunk.xPosition, chunk.zPosition);
            }
        }

        if(chunkLoader != null)
        {
            chunkLoader.func_814_a();
        }
        return chunkProvider.unload100OldestChunks();
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return (new StringBuilder()).append("ServerChunkCache: ").append(chunkMap.getNumHashElements()).append(" Drop: ").append(droppedChunksSet.size()).toString();
    }

    private Set droppedChunksSet;
    private Chunk emptyChunk;
    private IChunkProvider chunkProvider;
    private IChunkLoader chunkLoader;
    private PlayerList chunkMap;
    private List chunkList;
    private World worldObj;
    private int field_35392_h;
}
