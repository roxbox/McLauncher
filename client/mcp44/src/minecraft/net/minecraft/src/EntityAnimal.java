// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityCreature, World, Block, BlockGrass, 
//            MathHelper, AxisAlignedBB, DamageSource, NBTTagCompound, 
//            EntityPlayer

public abstract class EntityAnimal extends EntityCreature
{

    public EntityAnimal(World world)
    {
        super(world);
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        findNewPathTick = 60;
        return super.attackEntityFrom(damagesource, i);
    }

    protected float getBlockPathWeight(int i, int j, int k)
    {
        if(worldObj.getBlockId(i, j - 1, k) == Block.grass.blockID)
        {
            return 10F;
        } else
        {
            return worldObj.getLightBrightness(i, j, k) - 0.5F;
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(posX);
        int j = MathHelper.floor_double(boundingBox.minY);
        int k = MathHelper.floor_double(posZ);
        return worldObj.getBlockId(i, j - 1, k) == Block.grass.blockID && worldObj.getFullBlockLightValue(i, j, k) > 8 && super.getCanSpawnHere();
    }

    public int getTalkInterval()
    {
        return 120;
    }

    protected boolean canDespawn()
    {
        return false;
    }

    protected int func_36001_a(EntityPlayer entityplayer)
    {
        return 1 + worldObj.rand.nextInt(3);
    }
}
