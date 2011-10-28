// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.lang.reflect.Constructor;
import java.util.*;

// Referenced classes of package net.minecraft.src:
//            World, ChunkPosition, EntityPlayer, MathHelper, 
//            ChunkCoordIntPair, EnumCreatureType, WorldChunkManager, BiomeGenBase, 
//            WeightedRandom, SpawnListEntry, ChunkCoordinates, EntityLiving, 
//            Material, EntitySpider, EntitySkeleton, EntitySheep, 
//            Pathfinder, PathEntity, PathPoint, BlockBed, 
//            EntityZombie

public final class SpawnerAnimals
{

    public SpawnerAnimals()
    {
    }

    protected static ChunkPosition getRandomSpawningPointInChunk(World world, int i, int j)
    {
        int k = i + world.rand.nextInt(16);
        world.getClass();
        int l = world.rand.nextInt(128);
        int i1 = j + world.rand.nextInt(16);
        return new ChunkPosition(k, l, i1);
    }

    public static final int performSpawning(World var0, boolean var1, boolean var2) {
        if(!var1 && !var2) {
           return 0;
        } else {
           eligibleChunksForSpawning.clear();

           int var3;
           int var6;
           for(var3 = 0; var3 < var0.playerEntities.size(); ++var3) {
              EntityPlayer var4 = (EntityPlayer)var0.playerEntities.get(var3);
              int var5 = MathHelper.floor_double(var4.posX / 16.0D);
              var6 = MathHelper.floor_double(var4.posZ / 16.0D);
              byte var7 = 8;

              for(int var8 = -var7; var8 <= var7; ++var8) {
                 for(int var9 = -var7; var9 <= var7; ++var9) {
                    eligibleChunksForSpawning.add(new ChunkCoordIntPair(var8 + var5, var9 + var6));
                 }
              }
           }

           var3 = 0;
           ChunkCoordinates var33 = var0.getSpawnPoint();
           EnumCreatureType[] var34 = EnumCreatureType.values();
           var6 = var34.length;

           for(int var35 = 0; var35 < var6; ++var35) {
              EnumCreatureType var36 = var34[var35];
              if((!var36.getPeacefulCreature() || var2) && (var36.getPeacefulCreature() || var1) && var0.countEntities(var36.getCreatureClass()) <= var36.getMaxNumberOfCreature() * eligibleChunksForSpawning.size() / 256) {
                 Iterator var37 = eligibleChunksForSpawning.iterator();

                 label91:
                 while(var37.hasNext()) {
                    ChunkCoordIntPair var10 = (ChunkCoordIntPair)var37.next();
                    BiomeGenBase var11 = var0.getWorldChunkManager().getBiomeGenAtChunkCoord(var10);
                    List var12 = var11.getSpawnableList(var36);
                    if(var12 != null && !var12.isEmpty()) {
                       SpawnListEntry var13 = (SpawnListEntry)WeightedRandom.func_35733_a(var0.rand, var12);
                       ChunkPosition var14 = getRandomSpawningPointInChunk(var0, var10.chunkXPos * 16, var10.chunkZPos * 16);
                       int var15 = var14.x;
                       int var16 = var14.y;
                       int var17 = var14.z;
                       if(!var0.isBlockNormalCube(var15, var16, var17) && var0.getBlockMaterial(var15, var16, var17) == var36.getCreatureMaterial()) {
                          int var18 = 0;

                          for(int var19 = 0; var19 < 3; ++var19) {
                             int var20 = var15;
                             int var21 = var16;
                             int var22 = var17;
                             byte var23 = 6;

                             for(int var24 = 0; var24 < 4; ++var24) {
                                var20 += var0.rand.nextInt(var23) - var0.rand.nextInt(var23);
                                var21 += var0.rand.nextInt(1) - var0.rand.nextInt(1);
                                var22 += var0.rand.nextInt(var23) - var0.rand.nextInt(var23);
                                if(canCreatureTypeSpawnAtLocation(var36, var0, var20, var21, var22)) {
                                   float var25 = (float)var20 + 0.5F;
                                   float var26 = (float)var21;
                                   float var27 = (float)var22 + 0.5F;
                                   if(var0.getClosestPlayer((double)var25, (double)var26, (double)var27, 24.0D) == null) {
                                      float var28 = var25 - (float)var33.posX;
                                      float var29 = var26 - (float)var33.posY;
                                      float var30 = var27 - (float)var33.posZ;
                                      float var31 = var28 * var28 + var29 * var29 + var30 * var30;
                                      if(var31 >= 576.0F) {
                                         EntityLiving var38;
                                         try {
                                            var38 = (EntityLiving)var13.entityClass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{var0});
                                         } catch (Exception var32) {
                                            var32.printStackTrace();
                                            return var3;
                                         }

                                         var38.setLocationAndAngles((double)var25, (double)var26, (double)var27, var0.rand.nextFloat() * 360.0F, 0.0F);
                                         if(var38.getCanSpawnHere()) {
                                            ++var18;
                                            var0.entityJoinedWorld(var38);
                                            creatureSpecificInit(var38, var0, var25, var26, var27);
                                            if(var18 >= var38.getMaxSpawnedInChunk()) {
                                               continue label91;
                                            }
                                         }

                                         var3 += var18;
                                      }
                                   }
                                }
                             }
                          }
                       }
                    }
                 }
              }
           }

           return var3;
        }
     }

    private static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType enumcreaturetype, World world, int i, int j, int k)
    {
        if(enumcreaturetype.getCreatureMaterial() == Material.water)
        {
            return world.getBlockMaterial(i, j, k).getIsLiquid() && !world.isBlockNormalCube(i, j + 1, k);
        } else
        {
            return world.isBlockNormalCube(i, j - 1, k) && !world.isBlockNormalCube(i, j, k) && !world.getBlockMaterial(i, j, k).getIsLiquid() && !world.isBlockNormalCube(i, j + 1, k);
        }
    }

    private static void creatureSpecificInit(EntityLiving entityliving, World world, float f, float f1, float f2)
    {
        if((entityliving instanceof EntitySpider) && world.rand.nextInt(100) == 0)
        {
            EntitySkeleton entityskeleton = new EntitySkeleton(world);
            entityskeleton.setLocationAndAngles(f, f1, f2, entityliving.rotationYaw, 0.0F);
            world.entityJoinedWorld(entityskeleton);
            entityskeleton.mountEntity(entityliving);
        } else
        if(entityliving instanceof EntitySheep)
        {
            ((EntitySheep)entityliving).setFleeceColor(EntitySheep.getRandomFleeceColor(world.rand));
        }
    }

    public static boolean performSleepSpawning(World world, List list)
    {
        boolean flag;
label0:
        {
            flag = false;
            Pathfinder pathfinder = new Pathfinder(world);
            Iterator iterator = list.iterator();
            do
            {
                EntityPlayer entityplayer;
                Class aclass[];
                do
                {
                    if(!iterator.hasNext())
                    {
                        break label0;
                    }
                    entityplayer = (EntityPlayer)iterator.next();
                    aclass = nightSpawnEntities;
                } while(aclass == null || aclass.length == 0);
                boolean flag1 = false;
                int i = 0;
label1:
                do
                {
label2:
                    {
                        int j;
                        int k;
                        int i1;
                        int j1;
label3:
                        {
                            if(i >= 20 || flag1)
                            {
                                break label1;
                            }
                            j = (MathHelper.floor_double(entityplayer.posX) + world.rand.nextInt(32)) - world.rand.nextInt(32);
                            k = (MathHelper.floor_double(entityplayer.posZ) + world.rand.nextInt(32)) - world.rand.nextInt(32);
                            int l = (MathHelper.floor_double(entityplayer.posY) + world.rand.nextInt(16)) - world.rand.nextInt(16);
                            if(l < 1)
                            {
                                l = 1;
                            } else
                            {
                                world.getClass();
                                if(l > 128)
                                {
                                    world.getClass();
                                    l = 128;
                                }
                            }
                            i1 = world.rand.nextInt(aclass.length);
                            for(j1 = l; j1 > 2 && !world.isBlockNormalCube(j, j1 - 1, k); j1--) { }
                            do
                            {
                                if(canCreatureTypeSpawnAtLocation(EnumCreatureType.monster, world, j, j1, k) || j1 >= l + 16)
                                {
                                    break;
                                }
                                world.getClass();
                                if(j1 >= 128)
                                {
                                    break;
                                }
                                j1++;
                            } while(true);
                            if(j1 < l + 16)
                            {
                                world.getClass();
                                if(j1 < 128)
                                {
                                    break label3;
                                }
                            }
                            j1 = l;
                            break label2;
                        }
                        float f = (float)j + 0.5F;
                        float f1 = j1;
                        float f2 = (float)k + 0.5F;
                        EntityLiving entityliving;
                        try
                        {
                            entityliving = (EntityLiving)aclass[i1].getConstructor(new Class[] {
                                net.minecraft.src.World.class
                            }).newInstance(new Object[] {
                                world
                            });
                        }
                        catch(Exception exception)
                        {
                            exception.printStackTrace();
                            return flag;
                        }
                        entityliving.setLocationAndAngles(f, f1, f2, world.rand.nextFloat() * 360F, 0.0F);
                        if(entityliving.getCanSpawnHere())
                        {
                            PathEntity pathentity = pathfinder.createEntityPathTo(entityliving, entityplayer, 32F);
                            if(pathentity != null && pathentity.pathLength > 1)
                            {
                                PathPoint pathpoint = pathentity.getPathEnd();
                                if(Math.abs((double)pathpoint.xCoord - entityplayer.posX) < 1.5D && Math.abs((double)pathpoint.zCoord - entityplayer.posZ) < 1.5D && Math.abs((double)pathpoint.yCoord - entityplayer.posY) < 1.5D)
                                {
                                    ChunkCoordinates chunkcoordinates = BlockBed.getNearestEmptyChunkCoordinates(world, MathHelper.floor_double(entityplayer.posX), MathHelper.floor_double(entityplayer.posY), MathHelper.floor_double(entityplayer.posZ), 1);
                                    if(chunkcoordinates == null)
                                    {
                                        chunkcoordinates = new ChunkCoordinates(j, j1 + 1, k);
                                    }
                                    entityliving.setLocationAndAngles((float)chunkcoordinates.posX + 0.5F, chunkcoordinates.posY, (float)chunkcoordinates.posZ + 0.5F, 0.0F, 0.0F);
                                    world.entityJoinedWorld(entityliving);
                                    creatureSpecificInit(entityliving, world, (float)chunkcoordinates.posX + 0.5F, chunkcoordinates.posY, (float)chunkcoordinates.posZ + 0.5F);
                                    entityplayer.wakeUpPlayer(true, false, false);
                                    entityliving.playLivingSound();
                                    flag = true;
                                    flag1 = true;
                                }
                            }
                        }
                    }
                    i++;
                } while(true);
            } while(true);
        }
        return flag;
    }

    public static void func_35957_a(World world, BiomeGenBase biomegenbase, int i, int j, int k, int l, Random random)
    {
        List list = biomegenbase.getSpawnableList(EnumCreatureType.creature);
        if(list.isEmpty())
        {
            return;
        }
        while(random.nextFloat() < biomegenbase.getSpawningChance()) 
        {
            SpawnListEntry spawnlistentry = (SpawnListEntry)WeightedRandom.func_35733_a(world.rand, list);
            int i1 = spawnlistentry.field_35591_b + random.nextInt((1 + spawnlistentry.field_35592_c) - spawnlistentry.field_35591_b);
            int j1 = i + random.nextInt(k);
            int k1 = j + random.nextInt(l);
            int l1 = j1;
            int i2 = k1;
            int j2 = 0;
            while(j2 < i1) 
            {
                boolean flag = false;
                for(int k2 = 0; !flag && k2 < 4; k2++)
                {
                    int l2 = world.getTopSolidOrLiquidBlock(j1, k1);
                    if(canCreatureTypeSpawnAtLocation(EnumCreatureType.creature, world, j1, l2, k1))
                    {
                        float f = (float)j1 + 0.5F;
                        float f1 = l2;
                        float f2 = (float)k1 + 0.5F;
                        EntityLiving entityliving;
                        try
                        {
                            entityliving = (EntityLiving)spawnlistentry.entityClass.getConstructor(new Class[] {
                                net.minecraft.src.World.class
                            }).newInstance(new Object[] {
                                world
                            });
                        }
                        catch(Exception exception)
                        {
                            exception.printStackTrace();
                            continue;
                        }
                        entityliving.setLocationAndAngles(f, f1, f2, random.nextFloat() * 360F, 0.0F);
                        world.entityJoinedWorld(entityliving);
                        creatureSpecificInit(entityliving, world, f, f1, f2);
                        flag = true;
                    }
                    j1 += random.nextInt(5) - random.nextInt(5);
                    for(k1 += random.nextInt(5) - random.nextInt(5); j1 < i || j1 >= i + k || k1 < j || k1 >= j + k; k1 = (i2 + random.nextInt(5)) - random.nextInt(5))
                    {
                        j1 = (l1 + random.nextInt(5)) - random.nextInt(5);
                    }

                }

                j2++;
            }
        }
    }

    private static Set eligibleChunksForSpawning = new HashSet();
    protected static final Class nightSpawnEntities[];

    static 
    {
        nightSpawnEntities = (new Class[] {
            net.minecraft.src.EntitySpider.class, net.minecraft.src.EntityZombie.class, net.minecraft.src.EntitySkeleton.class
        });
    }
}
