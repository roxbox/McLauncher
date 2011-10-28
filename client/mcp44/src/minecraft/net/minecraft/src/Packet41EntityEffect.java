// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet41EntityEffect extends Packet
{

    public Packet41EntityEffect()
    {
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        entityId = datainputstream.readInt();
        effectId = datainputstream.readByte();
        field_35260_c = datainputstream.readByte();
        duration = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeInt(entityId);
        dataoutputstream.writeByte(effectId);
        dataoutputstream.writeByte(field_35260_c);
        dataoutputstream.writeShort(duration);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleEntityEffect(this);
    }

    public int getPacketSize()
    {
        return 8;
    }

    public int entityId;
    public byte effectId;
    public byte field_35260_c;
    public short duration;
}
