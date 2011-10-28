// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.*;

// Referenced classes of package net.minecraft.src:
//            Packet, NetHandler

public class Packet43Experience extends Packet
{

    public Packet43Experience()
    {
    }

    public void readPacketData(DataInputStream datainputstream)
        throws IOException
    {
        experience = datainputstream.readByte();
        experienceLevel = datainputstream.readByte();
        experienceTotal = datainputstream.readShort();
    }

    public void writePacketData(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeByte(experience);
        dataoutputstream.writeByte(experienceLevel);
        dataoutputstream.writeShort(experienceTotal);
    }

    public void processPacket(NetHandler nethandler)
    {
        nethandler.handleExperience(this);
    }

    public int getPacketSize()
    {
        return 4;
    }

    public int experience;
    public int experienceTotal;
    public int experienceLevel;
}
