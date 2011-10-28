// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            WorldGenClay, WorldGenSand, Block, WorldGenMinable, 
//            WorldGenFlowers, BlockFlower, WorldGenReed, WorldGenCactus, 
//            World, WorldGenerator, BiomeGenBase, WorldGenTallGrass, 
//            BlockTallGrass, WorldGenDeadBush, BlockDeadBush, WorldGenPumpkin, 
//            WorldGenLiquids

public class BiomeDecorator
{

    public BiomeDecorator(BiomeGenBase biomegenbase)
    {
        clayGen = new WorldGenClay(4);
        sandGen = new WorldGenSand(7, Block.sand.blockID);
        gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
        dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
        gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
        coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
        ironGen = new WorldGenMinable(Block.oreIron.blockID, 8);
        goldGen = new WorldGenMinable(Block.oreGold.blockID, 8);
        redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 7);
        diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 7);
        lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 6);
        plantYellowGen1 = new WorldGenFlowers(Block.plantYellow.blockID);
        plantYellowGen2 = new WorldGenFlowers(Block.plantYellow.blockID);
        mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
        mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
        reedGen = new WorldGenReed();
        cactusGen = new WorldGenCactus();
        treesPerChunk = 0;
        flowersPerChunk = 2;
        grassPerChunk = 1;
        deadBushPerChunk = 0;
        mushroomsPerChunk = 0;
        reedsPerChunk = 0;
        cactiPerChunk = 0;
        sandPerChunk = 1;
        sandPerChunk2 = 3;
        clayPerChunk = 1;
        biome = biomegenbase;
    }

    public void decorate(World world, Random random, int i, int j)
    {
        if(currentWorld != null)
        {
            throw new RuntimeException("Already decorating!!");
        } else
        {
            currentWorld = world;
            decoRNG = random;
            chunk_X = i;
            chunk_Z = j;
            decorate_do();
            currentWorld = null;
            decoRNG = null;
            return;
        }
    }

    private void decorate_do()
    {
        generateOres();
        for(int i = 0; i < sandPerChunk2; i++)
        {
            int i1 = chunk_X + decoRNG.nextInt(16) + 8;
            int i5 = chunk_Z + decoRNG.nextInt(16) + 8;
            sandGen.generate(currentWorld, decoRNG, i1, currentWorld.getTopSolidOrLiquidBlock(i1, i5), i5);
        }

        for(int j = 0; j < clayPerChunk; j++)
        {
            int j1 = chunk_X + decoRNG.nextInt(16) + 8;
            int j5 = chunk_Z + decoRNG.nextInt(16) + 8;
            clayGen.generate(currentWorld, decoRNG, j1, currentWorld.getTopSolidOrLiquidBlock(j1, j5), j5);
        }

        for(int k = 0; k < sandPerChunk; k++)
        {
            int k1 = chunk_X + decoRNG.nextInt(16) + 8;
            int k5 = chunk_Z + decoRNG.nextInt(16) + 8;
            sandGen.generate(currentWorld, decoRNG, k1, currentWorld.getTopSolidOrLiquidBlock(k1, k5), k5);
        }

        int l = treesPerChunk;
        if(decoRNG.nextInt(10) == 0)
        {
            l++;
        }
        for(int l1 = 0; l1 < l; l1++)
        {
            int l5 = chunk_X + decoRNG.nextInt(16) + 8;
            int k9 = chunk_Z + decoRNG.nextInt(16) + 8;
            WorldGenerator worldgenerator = biome.getRandomWorldGenForTrees(decoRNG);
            worldgenerator.func_517_a(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(currentWorld, decoRNG, l5, currentWorld.getHeightValue(l5, k9), k9);
        }

        for(int i2 = 0; i2 < flowersPerChunk; i2++)
        {
            int i6 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int l9 = decoRNG.nextInt(128);
            int j13 = chunk_Z + decoRNG.nextInt(16) + 8;
            plantYellowGen1.generate(currentWorld, decoRNG, i6, l9, j13);
            if(decoRNG.nextInt(4) == 0)
            {
                int j6 = chunk_X + decoRNG.nextInt(16) + 8;
                currentWorld.getClass();
                int i10 = decoRNG.nextInt(128);
                int k13 = chunk_Z + decoRNG.nextInt(16) + 8;
                plantYellowGen2.generate(currentWorld, decoRNG, j6, i10, k13);
            }
        }

        for(int j2 = 0; j2 < grassPerChunk; j2++)
        {
            int k6 = 1;
            int j10 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int l13 = decoRNG.nextInt(128);
            int i16 = chunk_Z + decoRNG.nextInt(16) + 8;
            (new WorldGenTallGrass(Block.tallGrass.blockID, k6)).generate(currentWorld, decoRNG, j10, l13, i16);
        }

        for(int k2 = 0; k2 < deadBushPerChunk; k2++)
        {
            int l6 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int k10 = decoRNG.nextInt(128);
            int i14 = chunk_Z + decoRNG.nextInt(16) + 8;
            (new WorldGenDeadBush(Block.deadBush.blockID)).generate(currentWorld, decoRNG, l6, k10, i14);
        }

        for(int l2 = 0; l2 < mushroomsPerChunk; l2++)
        {
            if(decoRNG.nextInt(4) == 0)
            {
                int i7 = chunk_X + decoRNG.nextInt(16) + 8;
                int l10 = chunk_Z + decoRNG.nextInt(16) + 8;
                int j14 = currentWorld.getHeightValue(i7, l10);
                mushroomBrownGen.generate(currentWorld, decoRNG, i7, j14, l10);
            }
            if(decoRNG.nextInt(8) == 0)
            {
                int j7 = chunk_X + decoRNG.nextInt(16) + 8;
                int i11 = chunk_Z + decoRNG.nextInt(16) + 8;
                currentWorld.getClass();
                int k14 = decoRNG.nextInt(128);
                mushroomRedGen.generate(currentWorld, decoRNG, j7, k14, i11);
            }
        }

        if(decoRNG.nextInt(4) == 0)
        {
            int i3 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int k7 = decoRNG.nextInt(128);
            int j11 = chunk_Z + decoRNG.nextInt(16) + 8;
            mushroomBrownGen.generate(currentWorld, decoRNG, i3, k7, j11);
        }
        if(decoRNG.nextInt(8) == 0)
        {
            int j3 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int l7 = decoRNG.nextInt(128);
            int k11 = chunk_Z + decoRNG.nextInt(16) + 8;
            mushroomRedGen.generate(currentWorld, decoRNG, j3, l7, k11);
        }
        for(int k3 = 0; k3 < reedsPerChunk; k3++)
        {
            int i8 = chunk_X + decoRNG.nextInt(16) + 8;
            int l11 = chunk_Z + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int l14 = decoRNG.nextInt(128);
            reedGen.generate(currentWorld, decoRNG, i8, l14, l11);
        }

        for(int l3 = 0; l3 < 10; l3++)
        {
            int j8 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int i12 = decoRNG.nextInt(128);
            int i15 = chunk_Z + decoRNG.nextInt(16) + 8;
            reedGen.generate(currentWorld, decoRNG, j8, i12, i15);
        }

        if(decoRNG.nextInt(32) == 0)
        {
            int i4 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int k8 = decoRNG.nextInt(128);
            int j12 = chunk_Z + decoRNG.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(currentWorld, decoRNG, i4, k8, j12);
        }
        for(int j4 = 0; j4 < cactiPerChunk; j4++)
        {
            int l8 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int k12 = decoRNG.nextInt(128);
            int j15 = chunk_Z + decoRNG.nextInt(16) + 8;
            cactusGen.generate(currentWorld, decoRNG, l8, k12, j15);
        }

        for(int k4 = 0; k4 < 50; k4++)
        {
            int i9 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int l12 = decoRNG.nextInt(decoRNG.nextInt(128 - 8) + 8);
            int k15 = chunk_Z + decoRNG.nextInt(16) + 8;
            (new WorldGenLiquids(Block.waterMoving.blockID)).generate(currentWorld, decoRNG, i9, l12, k15);
        }

        for(int l4 = 0; l4 < 20; l4++)
        {
            int j9 = chunk_X + decoRNG.nextInt(16) + 8;
            currentWorld.getClass();
            int i13 = decoRNG.nextInt(decoRNG.nextInt(decoRNG.nextInt(128 - 16) + 8) + 8);
            int l15 = chunk_Z + decoRNG.nextInt(16) + 8;
            (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(currentWorld, decoRNG, j9, i13, l15);
        }

    }

    protected void genStandardOre1(int i, WorldGenerator worldgenerator, int j, int k)
    {
        for(int l = 0; l < i; l++)
        {
            int i1 = chunk_X + decoRNG.nextInt(16);
            int j1 = decoRNG.nextInt(k - j) + j;
            int k1 = chunk_Z + decoRNG.nextInt(16);
            worldgenerator.generate(currentWorld, decoRNG, i1, j1, k1);
        }

    }

    protected void genStandardOre2(int i, WorldGenerator worldgenerator, int j, int k)
    {
        for(int l = 0; l < i; l++)
        {
            int i1 = chunk_X + decoRNG.nextInt(16);
            int j1 = decoRNG.nextInt(k) + decoRNG.nextInt(k) + (j - k);
            int k1 = chunk_Z + decoRNG.nextInt(16);
            worldgenerator.generate(currentWorld, decoRNG, i1, j1, k1);
        }

    }

    protected void generateOres()
    {
        currentWorld.getClass();
        genStandardOre1(20, dirtGen, 0, 128);
        currentWorld.getClass();
        genStandardOre1(10, gravelGen, 0, 128);
        currentWorld.getClass();
        genStandardOre1(20, coalGen, 0, 128);
        currentWorld.getClass();
        genStandardOre1(20, ironGen, 0, 128 / 2);
        currentWorld.getClass();
        genStandardOre1(2, goldGen, 0, 128 / 4);
        currentWorld.getClass();
        genStandardOre1(8, redstoneGen, 0, 128 / 8);
        currentWorld.getClass();
        genStandardOre1(1, diamondGen, 0, 128 / 8);
        currentWorld.getClass();
        currentWorld.getClass();
        genStandardOre2(1, lapisGen, 128 / 8, 128 / 8);
    }

    private World currentWorld;
    private Random decoRNG;
    private int chunk_X;
    private int chunk_Z;
    private BiomeGenBase biome;
    protected WorldGenerator clayGen;
    protected WorldGenerator sandGen;
    protected WorldGenerator gravelAsSandGen;
    protected WorldGenerator dirtGen;
    protected WorldGenerator gravelGen;
    protected WorldGenerator coalGen;
    protected WorldGenerator ironGen;
    protected WorldGenerator goldGen;
    protected WorldGenerator redstoneGen;
    protected WorldGenerator diamondGen;
    protected WorldGenerator lapisGen;
    protected WorldGenerator plantYellowGen1;
    protected WorldGenerator plantYellowGen2;
    protected WorldGenerator mushroomBrownGen;
    protected WorldGenerator mushroomRedGen;
    protected WorldGenerator reedGen;
    protected WorldGenerator cactusGen;
    protected int treesPerChunk;
    protected int flowersPerChunk;
    protected int grassPerChunk;
    protected int deadBushPerChunk;
    protected int mushroomsPerChunk;
    protected int reedsPerChunk;
    protected int cactiPerChunk;
    protected int sandPerChunk;
    protected int sandPerChunk2;
    protected int clayPerChunk;
}
