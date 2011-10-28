// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockFlower

public class WorldGenBigMushroom extends WorldGenerator
{

    public WorldGenBigMushroom(int i)
    {
        field_35266_a = -1;
        field_35266_a = i;
    }

    public WorldGenBigMushroom()
    {
        field_35266_a = -1;
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int l;
        int i1;
        boolean flag;
label0:
        {
            l = random.nextInt(2);
            if(field_35266_a >= 0)
            {
                l = field_35266_a;
            }
            i1 = random.nextInt(3) + 4;
            flag = true;
            if(j >= 1)
            {
                world.getClass();
                if(j + i1 + 1 <= 128)
                {
                    break label0;
                }
            }
            return false;
        }
        for(int j1 = j; j1 <= j + 1 + i1; j1++)
        {
            byte byte0 = 3;
            if(j1 == j)
            {
                byte0 = 0;
            }
            for(int j2 = i - byte0; j2 <= i + byte0 && flag; j2++)
            {
                for(int i3 = k - byte0; i3 <= k + byte0 && flag; i3++)
                {
                    if(j1 >= 0)
                    {
                        world.getClass();
                        if(j1 < 128)
                        {
                            int k3 = world.getBlockId(j2, j1, i3);
                            if(k3 != 0 && k3 != Block.leaves.blockID)
                            {
                                flag = false;
                            }
                            continue;
                        }
                    }
                    flag = false;
                }

            }

        }

        if(!flag)
        {
            return false;
        }
        if(!Block.mushroomBrown.canPlaceBlockAt(world, i, j, k))
        {
            return false;
        }
        world.setBlock(i, j - 1, k, Block.dirt.blockID);
        int k1 = j + i1;
        if(l == 1)
        {
            k1 = (j + i1) - 3;
        }
        for(int l1 = k1; l1 <= j + i1; l1++)
        {
            int k2 = 1;
            if(l1 < j + i1)
            {
                k2++;
            }
            if(l == 0)
            {
                k2 = 3;
            }
            for(int j3 = i - k2; j3 <= i + k2; j3++)
            {
                for(int l3 = k - k2; l3 <= k + k2; l3++)
                {
                    int i4 = 5;
                    if(j3 == i - k2)
                    {
                        i4--;
                    }
                    if(j3 == i + k2)
                    {
                        i4++;
                    }
                    if(l3 == k - k2)
                    {
                        i4 -= 3;
                    }
                    if(l3 == k + k2)
                    {
                        i4 += 3;
                    }
                    if(l == 0 || l1 < j + i1)
                    {
                        if((j3 == i - k2 || j3 == i + k2) && (l3 == k - k2 || l3 == k + k2))
                        {
                            continue;
                        }
                        if(j3 == i - (k2 - 1) && l3 == k - k2)
                        {
                            i4 = 1;
                        }
                        if(j3 == i - k2 && l3 == k - (k2 - 1))
                        {
                            i4 = 1;
                        }
                        if(j3 == i + (k2 - 1) && l3 == k - k2)
                        {
                            i4 = 3;
                        }
                        if(j3 == i + k2 && l3 == k - (k2 - 1))
                        {
                            i4 = 3;
                        }
                        if(j3 == i - (k2 - 1) && l3 == k + k2)
                        {
                            i4 = 7;
                        }
                        if(j3 == i - k2 && l3 == k + (k2 - 1))
                        {
                            i4 = 7;
                        }
                        if(j3 == i + (k2 - 1) && l3 == k + k2)
                        {
                            i4 = 9;
                        }
                        if(j3 == i + k2 && l3 == k + (k2 - 1))
                        {
                            i4 = 9;
                        }
                    }
                    if(i4 == 5 && l1 < j + i1)
                    {
                        i4 = 0;
                    }
                    if((i4 != 0 || j >= (j + i1) - 1) && !Block.opaqueCubeLookup[world.getBlockId(j3, l1, l3)])
                    {
                        world.setBlockAndMetadata(j3, l1, l3, Block.mushroomCapBrown.blockID + l, i4);
                    }
                }

            }

        }

        for(int i2 = 0; i2 < i1; i2++)
        {
            int l2 = world.getBlockId(i, j + i2, k);
            if(!Block.opaqueCubeLookup[l2])
            {
                world.setBlockAndMetadata(i, j + i2, k, Block.mushroomCapBrown.blockID + l, 10);
            }
        }

        return true;
    }

    private int field_35266_a;
}
