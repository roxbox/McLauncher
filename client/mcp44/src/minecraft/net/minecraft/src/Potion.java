// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityLiving, DamageSource, EntityPlayer, PotionHealth

public class Potion
{

    protected Potion(int i)
    {
        name = "";
        id = i;
        potionArray[i] = this;
    }

    public void performEffect(EntityLiving entityliving, int i)
    {
        if(id == potionRegeneration.id)
        {
            if(entityliving.health < 20)
            {
                entityliving.heal(1);
            }
        } else
        if(id == potionPoison.id)
        {
            if(entityliving.health > 1)
            {
                entityliving.attackEntityFrom(DamageSource.magic, 1);
            }
        } else
        if(id == potionHunger.id && (entityliving instanceof EntityPlayer))
        {
            ((EntityPlayer)entityliving).addExhaustion(0.025F * (float)(i + 1));
        } else
        if(id == potionHeal.id)
        {
            entityliving.heal(4 << i);
        } else
        if(id == potionHealDamage.id)
        {
            entityliving.attackEntityFrom(DamageSource.magic, 4 << i);
        }
    }

    public boolean isReady(int i, int j)
    {
        if(id == potionRegeneration.id || id == potionPoison.id)
        {
            int k = 25 >> j;
            if(k > 0)
            {
                return i % k == 0;
            } else
            {
                return true;
            }
        }
        return id == potionHunger.id;
    }

    public Potion setPotionName(String s)
    {
        name = s;
        return this;
    }

    public static final Potion potionArray[] = new Potion[32];
    public static final Potion potionNull = null;
    public static final Potion potionSpeed = (new Potion(1)).setPotionName("potion.moveSpeed");
    public static final Potion potionSlowdown = (new Potion(2)).setPotionName("potion.moveSlowdown");
    public static final Potion potionDigSpeed = (new Potion(3)).setPotionName("potion.digSpeed");
    public static final Potion potionDigSlow = (new Potion(4)).setPotionName("potion.digSlowDown");
    public static final Potion potionDamageBoost = (new Potion(5)).setPotionName("potion.damageBoost");
    public static final Potion potionHeal = (new PotionHealth(6)).setPotionName("potion.heal");
    public static final Potion potionHealDamage = (new PotionHealth(7)).setPotionName("potion.harm");
    public static final Potion potionJump = (new Potion(8)).setPotionName("potion.jump");
    public static final Potion potionConfusion = (new Potion(9)).setPotionName("potion.confusion");
    public static final Potion potionRegeneration = (new Potion(10)).setPotionName("potion.regeneration");
    public static final Potion potionResistance = (new Potion(11)).setPotionName("potion.resistance");
    public static final Potion potionFireReistance = (new Potion(12)).setPotionName("potion.fireResistance");
    public static final Potion potionWaterBreathing = (new Potion(13)).setPotionName("potion.waterBreathing");
    public static final Potion potionInvisibility = (new Potion(14)).setPotionName("potion.invisibility");
    public static final Potion potionBlindness = (new Potion(15)).setPotionName("potion.blindness");
    public static final Potion potionNightVision = (new Potion(16)).setPotionName("potion.nightVision");
    public static final Potion potionHunger = (new Potion(17)).setPotionName("potion.hunger");
    public static final Potion potionWeakness = (new Potion(18)).setPotionName("potion.weakness");
    public static final Potion potionPoison = (new Potion(19)).setPotionName("potion.poison");
    public static final Potion potionNull2 = null;
    public static final Potion potionNull3 = null;
    public static final Potion potionNull4 = null;
    public static final Potion potionNull5 = null;
    public static final Potion potionNull6 = null;
    public static final Potion potionNull7 = null;
    public static final Potion potionNull8 = null;
    public static final Potion potionNull9 = null;
    public static final Potion potionNull10 = null;
    public static final Potion potionNull11 = null;
    public static final Potion potionNull12 = null;
    public static final Potion potionNull13 = null;
    public final int id;
    private String name;

}
