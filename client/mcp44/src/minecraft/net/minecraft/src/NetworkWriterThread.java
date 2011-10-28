// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package net.minecraft.src:
//            NetworkManager

class NetworkWriterThread extends Thread
{

    NetworkWriterThread(NetworkManager networkmanager, String s)
    {
        super(s);
        netManager = networkmanager;
    }

    public void run()
    {
        synchronized(NetworkManager.threadSyncObject)
        {
            NetworkManager.numWriteThreads++;
        }
        try {
        while(NetworkManager.isRunning(netManager)) 
        {
            while(NetworkManager.sendNetworkPacket(netManager)) ;
            try
            {
                if(NetworkManager.getOutputStream(netManager) != null)
                {
                    NetworkManager.getOutputStream(netManager).flush();
                }
            }
            catch(IOException ioexception)
            {
                if(!NetworkManager.func_28138_e(netManager))
                {
                    NetworkManager.func_30005_a(netManager, ioexception);
                }
                ioexception.printStackTrace();
            }
            try
            {
                sleep(2L);
            }
            catch(InterruptedException interruptedexception) { }
        }
        } finally {
        synchronized(NetworkManager.threadSyncObject)
        {
            NetworkManager.numWriteThreads--;
        }
        }
    }

    final NetworkManager netManager; /* synthetic field */
}
