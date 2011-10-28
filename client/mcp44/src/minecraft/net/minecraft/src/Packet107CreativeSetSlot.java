// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet107CreativeSetSlot extends Packet
{

    public Packet107CreativeSetSlot()
    {
    }

    public Packet107CreativeSetSlot(int i, int j, int k, int l)
    {
        slot = i;
        itemId = j;
        quantity = k;
        metadata = l;
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleCreativeSetSlot(this);
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        slot = datainputstream.readShort();
        itemId = datainputstream.readShort();
        quantity = datainputstream.readShort();
        metadata = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeShort(slot);
        dataoutputstream.writeShort(itemId);
        dataoutputstream.writeShort(quantity);
        dataoutputstream.writeShort(metadata);
    }

    public int getPacketSize()
    {
        return 8;
    }

    public int slot;
    public int itemId;
    public int quantity;
    public int metadata;
}
