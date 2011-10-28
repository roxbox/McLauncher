// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Map;

// Referenced classes of package net.minecraft.src:
//            StatsSyncher

class ThreadStatSyncherSend extends Thread
{

    ThreadStatSyncherSend(StatsSyncher statssyncher, Map map)
    {
        syncher = statssyncher;
        field_27233_a = map;
    }

    public void run()
    {
        try
        {
            StatsSyncher.func_27412_a(syncher, field_27233_a, StatsSyncher.getUnsentDataFile(syncher), StatsSyncher.getUnsentTempFile(syncher), StatsSyncher.getUnsentOldFile(syncher));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            StatsSyncher.setBusy(syncher, false);
        }
    }

    final Map field_27233_a; /* synthetic field */
    final StatsSyncher syncher; /* synthetic field */
}
