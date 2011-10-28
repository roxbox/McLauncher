// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


public enum EnumMovingObjectType
{
    TILE,
    ENTITY;
/*
    public static EnumMovingObjectType[] values()
    {
        return (EnumMovingObjectType[])allMovingObjectTypes.clone();
    }

    public static EnumMovingObjectType valueOf(String s)
    {
        return (EnumMovingObjectType)Enum.valueOf(net.minecraft.src.EnumMovingObjectType.class, s);
    }

    private EnumMovingObjectType(String s, int i)
    {
        super(s, i);
    }

    public static final EnumMovingObjectType TILE;
    public static final EnumMovingObjectType ENTITY;
    private static final EnumMovingObjectType allMovingObjectTypes[]; /* synthetic field */
/*
    static 
    {
        TILE = new EnumMovingObjectType("TILE", 0);
        ENTITY = new EnumMovingObjectType("ENTITY", 1);
        allMovingObjectTypes = (new EnumMovingObjectType[] {
            TILE, ENTITY
        });
    }
*/
}
