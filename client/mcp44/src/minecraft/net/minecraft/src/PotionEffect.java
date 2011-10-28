// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.PrintStream;

// Referenced classes of package net.minecraft.src:
//            Potion, EntityLiving

public class PotionEffect
{

    public PotionEffect(int i, int j, int k)
    {
        potionID = i;
        duration = j;
        amplifier = k;
    }

    public void combine(PotionEffect potioneffect)
    {
        if(potionID != potioneffect.potionID)
        {
            System.err.println("This method should only be called for matching effects!");
        }
        if(potioneffect.amplifier >= amplifier)
        {
            amplifier = potioneffect.amplifier;
            duration = potioneffect.duration;
        }
    }

    public int getPotionID()
    {
        return potionID;
    }

    public int getDuration()
    {
        return duration;
    }

    public int getAmplifier()
    {
        return amplifier;
    }

    public boolean onUpdate(EntityLiving entityliving)
    {
        if(duration > 0)
        {
            if(Potion.potionArray[potionID].isReady(duration, amplifier))
            {
                performEffect(entityliving);
            }
            deincrementDuration();
        }
        return duration > 0;
    }

    private int deincrementDuration()
    {
        return --duration;
    }

    public void performEffect(EntityLiving entityliving)
    {
        if(duration > 0)
        {
            Potion.potionArray[potionID].performEffect(entityliving, amplifier);
        }
    }

    public int hashCode()
    {
        return potionID;
    }

    private int potionID;
    private int duration;
    private int amplifier;
}
