// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


public final class WorldSettings
{

    public WorldSettings(long l, int i, boolean flag)
    {
        field_35523_a = l;
        field_35521_b = i;
        field_35522_c = flag;
    }

    public long func_35518_a()
    {
        return field_35523_a;
    }

    public int func_35519_b()
    {
        return field_35521_b;
    }

    public boolean func_35520_c()
    {
        return field_35522_c;
    }

    private final long field_35523_a;
    private final int field_35521_b;
    private final boolean field_35522_c;
}
