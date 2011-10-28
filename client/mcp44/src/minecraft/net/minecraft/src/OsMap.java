// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            EnumOS1

class OsMap
{

    static final int allOSes[]; /* synthetic field */

    static 
    {
        allOSes = new int[EnumOS1.values().length];
        try
        {
            allOSes[EnumOS1.linux.ordinal()] = 1;
        }
        catch(NoSuchFieldError nosuchfielderror) { }
        try
        {
            allOSes[EnumOS1.solaris.ordinal()] = 2;
        }
        catch(NoSuchFieldError nosuchfielderror1) { }
        try
        {
            allOSes[EnumOS1.windows.ordinal()] = 3;
        }
        catch(NoSuchFieldError nosuchfielderror2) { }
        try
        {
            allOSes[EnumOS1.macos.ordinal()] = 4;
        }
        catch(NoSuchFieldError nosuchfielderror3) { }
    }
}
