// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, StructureBoundingBox, World

public abstract class StructureStart
{

    protected StructureStart()
    {
        components = new LinkedList();
    }

    public StructureBoundingBox getBoundingBox()
    {
        return boundingBox;
    }

    public void generateStructure(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        Iterator iterator = components.iterator();
        do
        {
            if(!iterator.hasNext())
            {
                break;
            }
            StructureComponent structurecomponent = (StructureComponent)iterator.next();
            if(structurecomponent.getBoundingBox().intersectsWith(structureboundingbox) && !structurecomponent.addComponentParts(world, random, structureboundingbox))
            {
                iterator.remove();
            }
        } while(true);
    }

    protected void updateBoundingBox()
    {
        boundingBox = StructureBoundingBox.getNewBoundingBox();
        StructureComponent structurecomponent;
        for(Iterator iterator = components.iterator(); iterator.hasNext(); boundingBox.resizeBoundingBoxTo(structurecomponent.getBoundingBox()))
        {
            structurecomponent = (StructureComponent)iterator.next();
        }

    }

    protected void markAvailableHeight(World world, Random random, int i)
    {
        world.getClass();
        int j = 63 - i;
        int k = boundingBox.getYSize() + 1;
        if(k < j)
        {
            k += random.nextInt(j - k);
        }
        int l = k - boundingBox.maxY;
        boundingBox.offset(0, l, 0);
        StructureComponent structurecomponent;
        for(Iterator iterator = components.iterator(); iterator.hasNext(); structurecomponent.getBoundingBox().offset(0, l, 0))
        {
            structurecomponent = (StructureComponent)iterator.next();
        }

    }

    public boolean isSizeableStructure()
    {
        return true;
    }

    protected LinkedList components;
    protected StructureBoundingBox boundingBox;
}
