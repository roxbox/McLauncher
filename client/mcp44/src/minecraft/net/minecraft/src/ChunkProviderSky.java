// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            IChunkProvider, MapGenCaves, NoiseGeneratorOctaves, Block, 
//            BiomeGenBase, Chunk, World, WorldChunkManager, 
//            MapGenBase, BlockSand, WorldGenLakes, WorldGenDungeons, 
//            WorldGenClay, WorldGenMinable, WorldGenerator, WorldGenFlowers, 
//            BlockFlower, WorldGenReed, WorldGenPumpkin, WorldGenCactus, 
//            WorldGenLiquids, IProgressUpdate

public class ChunkProviderSky
    implements IChunkProvider
{

    public ChunkProviderSky(World world, long l)
    {
        unusedSandNoise = new double[256];
        unusedGravelNoise = new double[256];
        stoneNoise = new double[256];
        caveGen = new MapGenCaves();
        field_28088_i = new int[32][32];
        worldObj = world;
        random = new Random(l);
        field_28086_k = new NoiseGeneratorOctaves(random, 16);
        field_28085_l = new NoiseGeneratorOctaves(random, 16);
        field_28084_m = new NoiseGeneratorOctaves(random, 8);
        field_28083_n = new NoiseGeneratorOctaves(random, 4);
        field_28082_o = new NoiseGeneratorOctaves(random, 4);
        field_28096_a = new NoiseGeneratorOctaves(random, 10);
        field_28095_b = new NoiseGeneratorOctaves(random, 16);
        field_28094_c = new NoiseGeneratorOctaves(random, 8);
    }

    public void generateTerrain(int i, int j, byte abyte0[], BiomeGenBase abiomegenbase[])
    {
        byte byte0 = 2;
        int k = byte0 + 1;
        worldObj.getClass();
        int l = 128 / 4 + 1;
        int i1 = byte0 + 1;
        field_28080_q = func_28073_a(field_28080_q, i * byte0, 0, j * byte0, k, l, i1);
label0:
        for(int j1 = 0; j1 < byte0; j1++)
        {
            int k1 = 0;
            do
            {
label1:
                {
                    if(k1 >= byte0)
                    {
                        continue label0;
                    }
                    int l1 = 0;
                    do
                    {
                        worldObj.getClass();
                        if(l1 >= 128 / 4)
                        {
                            break label1;
                        }
                        double d = 0.25D;
                        double d1 = field_28080_q[((j1 + 0) * i1 + (k1 + 0)) * l + (l1 + 0)];
                        double d2 = field_28080_q[((j1 + 0) * i1 + (k1 + 1)) * l + (l1 + 0)];
                        double d3 = field_28080_q[((j1 + 1) * i1 + (k1 + 0)) * l + (l1 + 0)];
                        double d4 = field_28080_q[((j1 + 1) * i1 + (k1 + 1)) * l + (l1 + 0)];
                        double d5 = (field_28080_q[((j1 + 0) * i1 + (k1 + 0)) * l + (l1 + 1)] - d1) * d;
                        double d6 = (field_28080_q[((j1 + 0) * i1 + (k1 + 1)) * l + (l1 + 1)] - d2) * d;
                        double d7 = (field_28080_q[((j1 + 1) * i1 + (k1 + 0)) * l + (l1 + 1)] - d3) * d;
                        double d8 = (field_28080_q[((j1 + 1) * i1 + (k1 + 1)) * l + (l1 + 1)] - d4) * d;
                        for(int i2 = 0; i2 < 4; i2++)
                        {
                            double d9 = 0.125D;
                            double d10 = d1;
                            double d11 = d2;
                            double d12 = (d3 - d1) * d9;
                            double d13 = (d4 - d2) * d9;
                            for(int j2 = 0; j2 < 8; j2++)
                            {
                                worldObj.getClass();
                                worldObj.getClass();
                                int k2 = j2 + j1 * 8 << 11 | 0 + k1 * 8 << 7 | l1 * 4 + i2;
                                worldObj.getClass();
                                int l2 = 1 << 7;
                                double d14 = 0.125D;
                                double d15 = d10;
                                double d16 = (d11 - d10) * d14;
                                for(int i3 = 0; i3 < 8; i3++)
                                {
                                    int j3 = 0;
                                    if(d15 > 0.0D)
                                    {
                                        j3 = Block.stone.blockID;
                                    }
                                    abyte0[k2] = (byte)j3;
                                    k2 += l2;
                                    d15 += d16;
                                }

                                d10 += d12;
                                d11 += d13;
                            }

                            d1 += d5;
                            d2 += d6;
                            d3 += d7;
                            d4 += d8;
                        }

                        l1++;
                    } while(true);
                }
                k1++;
            } while(true);
        }

    }

    public void replaceBlocksForBiome(int i, int j, byte abyte0[], BiomeGenBase abiomegenbase[])
    {
        double d = 0.03125D;
        unusedSandNoise = field_28083_n.generateNoiseOctaves(unusedSandNoise, i * 16, j * 16, 0, 16, 16, 1, d, d, 1.0D);
        unusedGravelNoise = field_28083_n.generateNoiseOctaves(unusedGravelNoise, i * 16, 109, j * 16, 16, 1, 16, d, 1.0D, d);
        stoneNoise = field_28082_o.generateNoiseOctaves(stoneNoise, i * 16, j * 16, 0, 16, 16, 1, d * 2D, d * 2D, d * 2D);
        for(int k = 0; k < 16; k++)
        {
            for(int l = 0; l < 16; l++)
            {
                BiomeGenBase biomegenbase = abiomegenbase[k + l * 16];
                int i1 = (int)(stoneNoise[k + l * 16] / 3D + 3D + random.nextDouble() * 0.25D);
                int j1 = -1;
                byte byte0 = biomegenbase.topBlock;
                byte byte1 = biomegenbase.fillerBlock;
                worldObj.getClass();
                for(int k1 = 127; k1 >= 0; k1--)
                {
                    worldObj.getClass();
                    int l1 = (l * 16 + k) * 128 + k1;
                    byte byte2 = abyte0[l1];
                    if(byte2 == 0)
                    {
                        j1 = -1;
                        continue;
                    }
                    if(byte2 != Block.stone.blockID)
                    {
                        continue;
                    }
                    if(j1 == -1)
                    {
                        if(i1 <= 0)
                        {
                            byte0 = 0;
                            byte1 = (byte)Block.stone.blockID;
                        }
                        j1 = i1;
                        if(k1 >= 0)
                        {
                            abyte0[l1] = byte0;
                        } else
                        {
                            abyte0[l1] = byte1;
                        }
                        continue;
                    }
                    if(j1 <= 0)
                    {
                        continue;
                    }
                    j1--;
                    abyte0[l1] = byte1;
                    if(j1 == 0 && byte1 == Block.sand.blockID)
                    {
                        j1 = random.nextInt(4);
                        byte1 = (byte)Block.sandStone.blockID;
                    }
                }

            }

        }

    }

    public Chunk loadChunk(int i, int j)
    {
        return provideChunk(i, j);
    }

    public Chunk provideChunk(int i, int j)
    {
        random.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
        worldObj.getClass();
        byte abyte0[] = new byte[16 * 128 * 16];
        Chunk chunk = new Chunk(worldObj, abyte0, i, j);
        field_28075_v = worldObj.getWorldChunkManager().loadBlockGeneratorData(field_28075_v, i * 16, j * 16, 16, 16);
        generateTerrain(i, j, abyte0, field_28075_v);
        replaceBlocksForBiome(i, j, abyte0, field_28075_v);
        caveGen.generate(this, worldObj, i, j, abyte0);
        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] func_28073_a(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if(ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        field_28090_g = field_28096_a.func_4109_a(field_28090_g, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        field_28089_h = field_28095_b.func_4109_a(field_28089_h, i, k, l, j1, 200D, 200D, 0.5D);
        d *= 2D;
        field_28093_d = field_28084_m.generateNoiseOctaves(field_28093_d, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        field_28092_e = field_28086_k.generateNoiseOctaves(field_28092_e, i, j, k, l, i1, j1, d, d1, d);
        field_28091_f = field_28085_l.generateNoiseOctaves(field_28091_f, i, j, k, l, i1, j1, d, d1, d);
        int k1 = 0;
        int l1 = 0;
        for(int i2 = 0; i2 < l; i2++)
        {
            for(int j2 = 0; j2 < j1; j2++)
            {
                double d2 = (field_28090_g[l1] + 256D) / 512D;
                if(d2 > 1.0D)
                {
                    d2 = 1.0D;
                }
                double d3 = field_28089_h[l1] / 8000D;
                if(d3 < 0.0D)
                {
                    d3 = -d3 * 0.29999999999999999D;
                }
                d3 = d3 * 3D - 2D;
                if(d3 > 1.0D)
                {
                    d3 = 1.0D;
                }
                d3 /= 8D;
                d3 = 0.0D;
                if(d2 < 0.0D)
                {
                    d2 = 0.0D;
                }
                d2 += 0.5D;
                d3 = (d3 * (double)i1) / 16D;
                l1++;
                double d4 = (double)i1 / 2D;
                for(int k2 = 0; k2 < i1; k2++)
                {
                    double d5 = 0.0D;
                    double d6 = (((double)k2 - d4) * 8D) / d2;
                    if(d6 < 0.0D)
                    {
                        d6 *= -1D;
                    }
                    double d7 = field_28092_e[k1] / 512D;
                    double d8 = field_28091_f[k1] / 512D;
                    double d9 = (field_28093_d[k1] / 10D + 1.0D) / 2D;
                    if(d9 < 0.0D)
                    {
                        d5 = d7;
                    } else
                    if(d9 > 1.0D)
                    {
                        d5 = d8;
                    } else
                    {
                        d5 = d7 + (d8 - d7) * d9;
                    }
                    d5 -= 8D;
                    int l2 = 32;
                    if(k2 > i1 - l2)
                    {
                        double d10 = (float)(k2 - (i1 - l2)) / ((float)l2 - 1.0F);
                        d5 = d5 * (1.0D - d10) + -30D * d10;
                    }
                    l2 = 8;
                    if(k2 < l2)
                    {
                        double d11 = (float)(l2 - k2) / ((float)l2 - 1.0F);
                        d5 = d5 * (1.0D - d11) + -30D * d11;
                    }
                    ad[k1] = d5;
                    k1++;
                }

            }

        }

        return ad;
    }

    public boolean chunkExists(int i, int j)
    {
        return true;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BlockSand.fallInstantly = true;
        int k = i * 16;
        int l = j * 16;
        BiomeGenBase biomegenbase = worldObj.getWorldChunkManager().getBiomeGenAt(k + 16, l + 16);
        random.setSeed(worldObj.getWorldSeed());
        long l1 = (random.nextLong() / 2L) * 2L + 1L;
        long l2 = (random.nextLong() / 2L) * 2L + 1L;
        random.setSeed((long)i * l1 + (long)j * l2 ^ worldObj.getWorldSeed());
        double d = 0.25D;
        if(random.nextInt(4) == 0)
        {
            int i1 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int l4 = random.nextInt(128);
            int i8 = l + random.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(worldObj, random, i1, l4, i8);
        }
        if(random.nextInt(8) == 0)
        {
            int j1 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int i5 = random.nextInt(random.nextInt(128 - 8) + 8);
            int j8 = l + random.nextInt(16) + 8;
            if(i5 < 64 || random.nextInt(10) == 0)
            {
                (new WorldGenLakes(Block.lavaStill.blockID)).generate(worldObj, random, j1, i5, j8);
            }
        }
        for(int k1 = 0; k1 < 8; k1++)
        {
            int j5 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int k8 = random.nextInt(128);
            int i13 = l + random.nextInt(16) + 8;
            (new WorldGenDungeons()).generate(worldObj, random, j5, k8, i13);
        }

        for(int i2 = 0; i2 < 10; i2++)
        {
            int k5 = k + random.nextInt(16);
            worldObj.getClass();
            int l8 = random.nextInt(128);
            int j13 = l + random.nextInt(16);
            (new WorldGenClay(32)).generate(worldObj, random, k5, l8, j13);
        }

        for(int j2 = 0; j2 < 20; j2++)
        {
            int l5 = k + random.nextInt(16);
            worldObj.getClass();
            int i9 = random.nextInt(128);
            int k13 = l + random.nextInt(16);
            (new WorldGenMinable(Block.dirt.blockID, 32)).generate(worldObj, random, l5, i9, k13);
        }

        for(int k2 = 0; k2 < 10; k2++)
        {
            int i6 = k + random.nextInt(16);
            worldObj.getClass();
            int j9 = random.nextInt(128);
            int l13 = l + random.nextInt(16);
            (new WorldGenMinable(Block.gravel.blockID, 32)).generate(worldObj, random, i6, j9, l13);
        }

        for(int i3 = 0; i3 < 20; i3++)
        {
            int j6 = k + random.nextInt(16);
            worldObj.getClass();
            int k9 = random.nextInt(128);
            int i14 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreCoal.blockID, 16)).generate(worldObj, random, j6, k9, i14);
        }

        for(int j3 = 0; j3 < 20; j3++)
        {
            int k6 = k + random.nextInt(16);
            worldObj.getClass();
            int l9 = random.nextInt(128 / 2);
            int j14 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreIron.blockID, 8)).generate(worldObj, random, k6, l9, j14);
        }

        for(int k3 = 0; k3 < 2; k3++)
        {
            int l6 = k + random.nextInt(16);
            worldObj.getClass();
            int i10 = random.nextInt(128 / 4);
            int k14 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreGold.blockID, 8)).generate(worldObj, random, l6, i10, k14);
        }

        for(int l3 = 0; l3 < 8; l3++)
        {
            int i7 = k + random.nextInt(16);
            worldObj.getClass();
            int j10 = random.nextInt(128 / 8);
            int l14 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreRedstone.blockID, 7)).generate(worldObj, random, i7, j10, l14);
        }

        for(int i4 = 0; i4 < 1; i4++)
        {
            int j7 = k + random.nextInt(16);
            worldObj.getClass();
            int k10 = random.nextInt(128 / 8);
            int i15 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreDiamond.blockID, 7)).generate(worldObj, random, j7, k10, i15);
        }

        for(int j4 = 0; j4 < 1; j4++)
        {
            int k7 = k + random.nextInt(16);
            worldObj.getClass();
            worldObj.getClass();
            int l10 = random.nextInt(128 / 8) + random.nextInt(128 / 8);
            int j15 = l + random.nextInt(16);
            (new WorldGenMinable(Block.oreLapis.blockID, 6)).generate(worldObj, random, k7, l10, j15);
        }

        d = 0.5D;
        int k4 = (int)((field_28094_c.func_806_a((double)k * d, (double)l * d) / 8D + random.nextDouble() * 4D + 4D) / 3D);
        int l7 = 0;
        if(random.nextInt(10) == 0)
        {
            l7++;
        }
        if(biomegenbase == BiomeGenBase.forest)
        {
            l7 += k4 + 5;
        }
        if(biomegenbase == BiomeGenBase.desert)
        {
            l7 -= 20;
        }
        if(biomegenbase == BiomeGenBase.plains)
        {
            l7 -= 20;
        }
        for(int i11 = 0; i11 < l7; i11++)
        {
            int k15 = k + random.nextInt(16) + 8;
            int i18 = l + random.nextInt(16) + 8;
            WorldGenerator worldgenerator = biomegenbase.getRandomWorldGenForTrees(random);
            worldgenerator.func_517_a(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(worldObj, random, k15, worldObj.getHeightValue(k15, i18), i18);
        }

        for(int j11 = 0; j11 < 2; j11++)
        {
            int l15 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int j18 = random.nextInt(128);
            int k20 = l + random.nextInt(16) + 8;
            (new WorldGenFlowers(Block.plantYellow.blockID)).generate(worldObj, random, l15, j18, k20);
        }

        if(random.nextInt(2) == 0)
        {
            int k11 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int i16 = random.nextInt(128);
            int k18 = l + random.nextInt(16) + 8;
            (new WorldGenFlowers(Block.plantRed.blockID)).generate(worldObj, random, k11, i16, k18);
        }
        if(random.nextInt(4) == 0)
        {
            int l11 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int j16 = random.nextInt(128);
            int l18 = l + random.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(worldObj, random, l11, j16, l18);
        }
        if(random.nextInt(8) == 0)
        {
            int i12 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int k16 = random.nextInt(128);
            int i19 = l + random.nextInt(16) + 8;
            (new WorldGenFlowers(Block.mushroomRed.blockID)).generate(worldObj, random, i12, k16, i19);
        }
        for(int j12 = 0; j12 < 10; j12++)
        {
            int l16 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int j19 = random.nextInt(128);
            int l20 = l + random.nextInt(16) + 8;
            (new WorldGenReed()).generate(worldObj, random, l16, j19, l20);
        }

        if(random.nextInt(32) == 0)
        {
            int k12 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int i17 = random.nextInt(128);
            int k19 = l + random.nextInt(16) + 8;
            (new WorldGenPumpkin()).generate(worldObj, random, k12, i17, k19);
        }
        int l12 = 0;
        if(biomegenbase == BiomeGenBase.desert)
        {
            l12 += 10;
        }
        for(int j17 = 0; j17 < l12; j17++)
        {
            int l19 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int i21 = random.nextInt(128);
            int l21 = l + random.nextInt(16) + 8;
            (new WorldGenCactus()).generate(worldObj, random, l19, i21, l21);
        }

        for(int k17 = 0; k17 < 50; k17++)
        {
            int i20 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int j21 = random.nextInt(random.nextInt(128 - 8) + 8);
            int i22 = l + random.nextInt(16) + 8;
            (new WorldGenLiquids(Block.waterMoving.blockID)).generate(worldObj, random, i20, j21, i22);
        }

        for(int l17 = 0; l17 < 20; l17++)
        {
            int j20 = k + random.nextInt(16) + 8;
            worldObj.getClass();
            int k21 = random.nextInt(random.nextInt(random.nextInt(128 - 16) + 8) + 8);
            int j22 = l + random.nextInt(16) + 8;
            (new WorldGenLiquids(Block.lavaMoving.blockID)).generate(worldObj, random, j20, k21, j22);
        }

        BlockSand.fallInstantly = false;
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
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }

    private Random random;
    private NoiseGeneratorOctaves field_28086_k;
    private NoiseGeneratorOctaves field_28085_l;
    private NoiseGeneratorOctaves field_28084_m;
    private NoiseGeneratorOctaves field_28083_n;
    private NoiseGeneratorOctaves field_28082_o;
    public NoiseGeneratorOctaves field_28096_a;
    public NoiseGeneratorOctaves field_28095_b;
    public NoiseGeneratorOctaves field_28094_c;
    private World worldObj;
    private double field_28080_q[];
    private double unusedSandNoise[];
    private double unusedGravelNoise[];
    private double stoneNoise[];
    private MapGenBase caveGen;
    private BiomeGenBase field_28075_v[];
    double field_28093_d[];
    double field_28092_e[];
    double field_28091_f[];
    double field_28090_g[];
    double field_28089_h[];
    int field_28088_i[][];
}
