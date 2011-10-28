// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            IChunkProvider, MapGenCaves, MapGenStronghold, MapGenVillage, 
//            MapGenMineshaft, MapGenRavine, NoiseGeneratorOctaves, World, 
//            WorldChunkManager, Block, BiomeGenBase, Chunk, 
//            MapGenBase, MathHelper, BlockSand, WorldGenLakes, 
//            WorldGenDungeons, SpawnerAnimals, IProgressUpdate

public class ChunkProviderGenerate
    implements IChunkProvider
{

    public ChunkProviderGenerate(World world, long l, boolean flag)
    {
        stoneNoise = new double[256];
        caveGenerator = new MapGenCaves();
        strongholdGenerator = new MapGenStronghold();
        villageGenerator = new MapGenVillage();
        mineshaftGenerator = new MapGenMineshaft();
        ravineGenerator = new MapGenRavine();
        unusedIntArray32x32 = new int[32][32];
        worldObj = world;
        mapFeaturesEnabled = flag;
        rand = new Random(l);
        noiseGen1 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves(rand, 10);
        noiseGen6 = new NoiseGeneratorOctaves(rand, 16);
        mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);
    }

    public void generateTerrain(int i, int j, byte abyte0[])
    {
        byte byte0 = 4;
        worldObj.getClass();
        int k = 128 / 8;
        worldObj.getClass();
        byte byte1 = 63;
        int l = byte0 + 1;
        worldObj.getClass();
        int i1 = 128 / 8 + 1;
        int j1 = byte0 + 1;
        biomesForGeneration = worldObj.getWorldChunkManager().func_35557_b(biomesForGeneration, i * 4 - 2, j * 4 - 2, l + 5, j1 + 5);
        field_4180_q = initializeNoiseField(field_4180_q, i * byte0, 0, j * byte0, l, i1, j1);
        for(int k1 = 0; k1 < byte0; k1++)
        {
            for(int l1 = 0; l1 < byte0; l1++)
            {
                for(int i2 = 0; i2 < k; i2++)
                {
                    double d = 0.125D;
                    double d1 = field_4180_q[((k1 + 0) * j1 + (l1 + 0)) * i1 + (i2 + 0)];
                    double d2 = field_4180_q[((k1 + 0) * j1 + (l1 + 1)) * i1 + (i2 + 0)];
                    double d3 = field_4180_q[((k1 + 1) * j1 + (l1 + 0)) * i1 + (i2 + 0)];
                    double d4 = field_4180_q[((k1 + 1) * j1 + (l1 + 1)) * i1 + (i2 + 0)];
                    double d5 = (field_4180_q[((k1 + 0) * j1 + (l1 + 0)) * i1 + (i2 + 1)] - d1) * d;
                    double d6 = (field_4180_q[((k1 + 0) * j1 + (l1 + 1)) * i1 + (i2 + 1)] - d2) * d;
                    double d7 = (field_4180_q[((k1 + 1) * j1 + (l1 + 0)) * i1 + (i2 + 1)] - d3) * d;
                    double d8 = (field_4180_q[((k1 + 1) * j1 + (l1 + 1)) * i1 + (i2 + 1)] - d4) * d;
                    for(int j2 = 0; j2 < 8; j2++)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for(int k2 = 0; k2 < 4; k2++)
                        {
                            worldObj.getClass();
                            worldObj.getClass();
                            int l2 = k2 + k1 * 4 << 11 | 0 + l1 * 4 << 7 | i2 * 8 + j2;
                            worldObj.getClass();
                            int i3 = 1 << 7;
                            double d14 = 0.25D;
                            double d15 = d10;
                            double d16 = (d11 - d10) * d14;
                            for(int j3 = 0; j3 < 4; j3++)
                            {
                                int k3 = 0;
                                if(i2 * 8 + j2 < byte1)
                                {
                                    k3 = Block.waterStill.blockID;
                                }
                                if(d15 > 0.0D)
                                {
                                    k3 = Block.stone.blockID;
                                }
                                abyte0[l2] = (byte)k3;
                                l2 += i3;
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

                }

            }

        }

    }

    public void replaceBlocksForBiome(int i, int j, byte abyte0[], BiomeGenBase abiomegenbase[])
    {
        worldObj.getClass();
        byte byte0 = 63;
        double d = 0.03125D;
        stoneNoise = noiseGen4.generateNoiseOctaves(stoneNoise, i * 16, j * 16, 0, 16, 16, 1, d * 2D, d * 2D, d * 2D);
        for(int k = 0; k < 16; k++)
        {
            for(int l = 0; l < 16; l++)
            {
                BiomeGenBase biomegenbase = abiomegenbase[l + k * 16];
                int i1 = (int)(stoneNoise[k + l * 16] / 3D + 3D + rand.nextDouble() * 0.25D);
                int j1 = -1;
                byte byte1 = biomegenbase.topBlock;
                byte byte2 = biomegenbase.fillerBlock;
                worldObj.getClass();
                for(int k1 = 127; k1 >= 0; k1--)
                {
                    worldObj.getClass();
                    int l1 = (l * 16 + k) * 128 + k1;
                    if(k1 <= 0 + rand.nextInt(5))
                    {
                        abyte0[l1] = (byte)Block.bedrock.blockID;
                        continue;
                    }
                    byte byte3 = abyte0[l1];
                    if(byte3 == 0)
                    {
                        j1 = -1;
                        continue;
                    }
                    if(byte3 != Block.stone.blockID)
                    {
                        continue;
                    }
                    if(j1 == -1)
                    {
                        if(i1 <= 0)
                        {
                            byte1 = 0;
                            byte2 = (byte)Block.stone.blockID;
                        } else
                        if(k1 >= byte0 - 4 && k1 <= byte0 + 1)
                        {
                            byte1 = biomegenbase.topBlock;
                            byte2 = biomegenbase.fillerBlock;
                        }
                        if(k1 < byte0 && byte1 == 0)
                        {
                            byte1 = (byte)Block.waterStill.blockID;
                        }
                        j1 = i1;
                        if(k1 >= byte0 - 1)
                        {
                            abyte0[l1] = byte1;
                        } else
                        {
                            abyte0[l1] = byte2;
                        }
                        continue;
                    }
                    if(j1 <= 0)
                    {
                        continue;
                    }
                    j1--;
                    abyte0[l1] = byte2;
                    if(j1 == 0 && byte2 == Block.sand.blockID)
                    {
                        j1 = rand.nextInt(4);
                        byte2 = (byte)Block.sandStone.blockID;
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
        rand.setSeed((long)i * 0x4f9939f508L + (long)j * 0x1ef1565bd5L);
        worldObj.getClass();
        byte abyte0[] = new byte[16 * 128 * 16];
        Chunk chunk = new Chunk(worldObj, abyte0, i, j);
        generateTerrain(i, j, abyte0);
        biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, i * 16, j * 16, 16, 16);
        replaceBlocksForBiome(i, j, abyte0, biomesForGeneration);
        caveGenerator.generate(this, worldObj, i, j, abyte0);
        if(mapFeaturesEnabled)
        {
            strongholdGenerator.generate(this, worldObj, i, j, abyte0);
            mineshaftGenerator.generate(this, worldObj, i, j, abyte0);
            villageGenerator.generate(this, worldObj, i, j, abyte0);
        }
        ravineGenerator.generate(this, worldObj, i, j, abyte0);
        chunk.generateSkylightMap();
        return chunk;
    }

    private double[] initializeNoiseField(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
        if(ad == null)
        {
            ad = new double[l * i1 * j1];
        }
        if(field_35388_l == null)
        {
            field_35388_l = new float[25];
            for(int k1 = -2; k1 <= 2; k1++)
            {
                for(int l1 = -2; l1 <= 2; l1++)
                {
                    float f = 10F / MathHelper.sqrt_float((float)(k1 * k1 + l1 * l1) + 0.2F);
                    field_35388_l[k1 + 2 + (l1 + 2) * 5] = f;
                }

            }

        }
        double d = 684.41200000000003D;
        double d1 = 684.41200000000003D;
        noise5 = noiseGen5.func_4109_a(noise5, i, k, l, j1, 1.121D, 1.121D, 0.5D);
        noise6 = noiseGen6.func_4109_a(noise6, i, k, l, j1, 200D, 200D, 0.5D);
        noise3 = noiseGen3.generateNoiseOctaves(noise3, i, j, k, l, i1, j1, d / 80D, d1 / 160D, d / 80D);
        noise1 = noiseGen1.generateNoiseOctaves(noise1, i, j, k, l, i1, j1, d, d1, d);
        noise2 = noiseGen2.generateNoiseOctaves(noise2, i, j, k, l, i1, j1, d, d1, d);
        i = k = 0;
        int i2 = 0;
        int j2 = 0;
        for(int k2 = 0; k2 < l; k2++)
        {
            for(int l2 = 0; l2 < j1; l2++)
            {
                float f1 = 0.0F;
                float f2 = 0.0F;
                float f3 = 0.0F;
                byte byte0 = 2;
                BiomeGenBase biomegenbase = biomesForGeneration[k2 + 2 + (l2 + 2) * (l + 5)];
                for(int i3 = -byte0; i3 <= byte0; i3++)
                {
                    for(int j3 = -byte0; j3 <= byte0; j3++)
                    {
                        BiomeGenBase biomegenbase1 = biomesForGeneration[k2 + i3 + 2 + (l2 + j3 + 2) * (l + 5)];
                        float f4 = field_35388_l[i3 + 2 + (j3 + 2) * 5] / (biomegenbase1.minHeight + 2.0F);
                        if(biomegenbase1.minHeight > biomegenbase.minHeight)
                        {
                            f4 /= 2.0F;
                        }
                        f1 += biomegenbase1.maxHeight * f4;
                        f2 += biomegenbase1.minHeight * f4;
                        f3 += f4;
                    }

                }

                f1 /= f3;
                f2 /= f3;
                f1 = f1 * 0.9F + 0.1F;
                f2 = (f2 * 4F - 1.0F) / 8F;
                double d2 = noise6[j2] / 8000D;
                if(d2 < 0.0D)
                {
                    d2 = -d2 * 0.29999999999999999D;
                }
                d2 = d2 * 3D - 2D;
                if(d2 < 0.0D)
                {
                    d2 /= 2D;
                    if(d2 < -1D)
                    {
                        d2 = -1D;
                    }
                    d2 /= 1.3999999999999999D;
                    d2 /= 2D;
                } else
                {
                    if(d2 > 1.0D)
                    {
                        d2 = 1.0D;
                    }
                    d2 /= 8D;
                }
                j2++;
                for(int k3 = 0; k3 < i1; k3++)
                {
                    double d3 = f2;
                    double d4 = f1;
                    d3 += d2 * 0.20000000000000001D;
                    d3 = (d3 * (double)i1) / 16D;
                    double d5 = (double)i1 / 2D + d3 * 4D;
                    double d6 = 0.0D;
                    worldObj.getClass();
                    double d7 = (((double)k3 - d5) * 12D * 128D) / 128D / d4;
                    if(d7 < 0.0D)
                    {
                        d7 *= 4D;
                    }
                    double d8 = noise1[i2] / 512D;
                    double d9 = noise2[i2] / 512D;
                    double d10 = (noise3[i2] / 10D + 1.0D) / 2D;
                    if(d10 < 0.0D)
                    {
                        d6 = d8;
                    } else
                    if(d10 > 1.0D)
                    {
                        d6 = d9;
                    } else
                    {
                        d6 = d8 + (d9 - d8) * d10;
                    }
                    d6 -= d7;
                    if(k3 > i1 - 4)
                    {
                        double d11 = (float)(k3 - (i1 - 4)) / 3F;
                        d6 = d6 * (1.0D - d11) + -10D * d11;
                    }
                    ad[i2] = d6;
                    i2++;
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
        rand.setSeed(worldObj.getWorldSeed());
        long l1 = (rand.nextLong() / 2L) * 2L + 1L;
        long l2 = (rand.nextLong() / 2L) * 2L + 1L;
        rand.setSeed((long)i * l1 + (long)j * l2 ^ worldObj.getWorldSeed());
        boolean flag = false;
        if(mapFeaturesEnabled)
        {
            strongholdGenerator.generateStructuresInChunk(worldObj, rand, i, j);
            mineshaftGenerator.generateStructuresInChunk(worldObj, rand, i, j);
            flag = villageGenerator.generateStructuresInChunk(worldObj, rand, i, j);
        }
        if(!flag && rand.nextInt(4) == 0)
        {
            int i1 = k + rand.nextInt(16) + 8;
            worldObj.getClass();
            int i2 = rand.nextInt(128);
            int i3 = l + rand.nextInt(16) + 8;
            (new WorldGenLakes(Block.waterStill.blockID)).generate(worldObj, rand, i1, i2, i3);
        }
        if(!flag && rand.nextInt(8) == 0)
        {
            int j1 = k + rand.nextInt(16) + 8;
            worldObj.getClass();
            int j2 = rand.nextInt(rand.nextInt(128 - 8) + 8);
            int j3 = l + rand.nextInt(16) + 8;
            worldObj.getClass();
            if(j2 < 63 || rand.nextInt(10) == 0)
            {
                (new WorldGenLakes(Block.lavaStill.blockID)).generate(worldObj, rand, j1, j2, j3);
            }
        }
        for(int k1 = 0; k1 < 8; k1++)
        {
            int k2 = k + rand.nextInt(16) + 8;
            worldObj.getClass();
            int k3 = rand.nextInt(128);
            int l3 = l + rand.nextInt(16) + 8;
            if(!(new WorldGenDungeons()).generate(worldObj, rand, k2, k3, l3));
        }

        biomegenbase.func_35477_a(worldObj, rand, k, l);
        SpawnerAnimals.func_35957_a(worldObj, biomegenbase, k + 8, l + 8, 16, 16, rand);
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

    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorOctaves noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private double field_4180_q[];
    private double stoneNoise[];
    private MapGenBase caveGenerator;
    public MapGenStronghold strongholdGenerator;
    public MapGenVillage villageGenerator;
    public MapGenMineshaft mineshaftGenerator;
    private MapGenBase ravineGenerator;
    private BiomeGenBase biomesForGeneration[];
    double noise3[];
    double noise1[];
    double noise2[];
    double noise5[];
    double noise6[];
    float field_35388_l[];
    int unusedIntArray32x32[][];
}
