// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EntityDamageSource, Entity

public class EntityDamageSourceIndirect extends EntityDamageSource
{

    public EntityDamageSourceIndirect(String s, Entity entity, Entity entity1)
    {
        super(s, entity);
        field_35553_n = entity1;
    }

    public Entity getEntity()
    {
        return field_35553_n;
    }

    private Entity field_35553_n;
}
