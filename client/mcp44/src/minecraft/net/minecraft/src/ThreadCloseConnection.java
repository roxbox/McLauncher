// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            NetworkManager

class ThreadCloseConnection extends Thread
{

    ThreadCloseConnection(NetworkManager networkmanager)
    {
        networkManager = networkmanager;
    }

    public void run()
    {
        try
        {
            Thread.sleep(2000L);
            if(NetworkManager.isRunning(networkManager))
            {
                NetworkManager.getWriteThread(networkManager).interrupt();
                networkManager.networkShutdown("disconnect.closed", new Object[0]);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    final NetworkManager networkManager; /* synthetic field */
}
