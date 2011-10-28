// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityDamageSource, EntityDamageSourceIndirect, EntityLiving, EntityPlayer, 
//            EntityArrow, Entity, EntityFireball

public class DamageSource
{

    public static DamageSource causeMobDamage(EntityLiving entityliving)
    {
        return new EntityDamageSource("mob", entityliving);
    }

    public static DamageSource causePlayerDamage(EntityPlayer entityplayer)
    {
        return new EntityDamageSource("player", entityplayer);
    }

    public static DamageSource causeArrowDamage(EntityArrow entityarrow, Entity entity)
    {
        return new EntityDamageSourceIndirect("arrow", entityarrow, entity);
    }

    public static DamageSource causeFireballDamage(EntityFireball entityfireball, Entity entity)
    {
        return new EntityDamageSourceIndirect("fireball", entityfireball, entity);
    }

    public static DamageSource causeThrownDamage(Entity entity, Entity entity1)
    {
        return new EntityDamageSourceIndirect("thrown", entity, entity1);
    }

    public boolean unblockable()
    {
        return isBlockable;
    }

    public float func_35533_c()
    {
        return hungerDamage;
    }

    public boolean canHarmInCreative()
    {
        return field_35544_o;
    }

    protected DamageSource(String s)
    {
        isBlockable = false;
        field_35544_o = false;
        hungerDamage = 0.3F;
        field_35546_m = s;
    }

    public Entity func_35526_e()
    {
        return getEntity();
    }

    public Entity getEntity()
    {
        return null;
    }

    private DamageSource func_35528_f()
    {
        isBlockable = true;
        hungerDamage = 0.0F;
        return this;
    }

    private DamageSource func_35531_g()
    {
        field_35544_o = true;
        return this;
    }

    public static DamageSource inFire = new DamageSource("inFire");
    public static DamageSource onFire = (new DamageSource("onFire")).func_35528_f();
    public static DamageSource lava = new DamageSource("lava");
    public static DamageSource inWall = (new DamageSource("inWall")).func_35528_f();
    public static DamageSource drown = (new DamageSource("drown")).func_35528_f();
    public static DamageSource starve = (new DamageSource("starve")).func_35528_f();
    public static DamageSource cactus = new DamageSource("cactus");
    public static DamageSource fall = new DamageSource("fall");
    public static DamageSource outOfWorld = (new DamageSource("outOfWorld")).func_35528_f().func_35531_g();
    public static DamageSource generic = (new DamageSource("generic")).func_35528_f();
    public static DamageSource explosion = new DamageSource("explosion");
    public static DamageSource magic = (new DamageSource("magic")).func_35528_f();
    private boolean isBlockable;
    private boolean field_35544_o;
    private float hungerDamage;
    public String field_35546_m;

}
