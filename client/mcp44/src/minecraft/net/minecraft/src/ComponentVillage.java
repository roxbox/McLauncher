// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            StructureComponent, StructureBoundingBox, StructureVillagePieces, World, 
//            ComponentVillageStartPiece

abstract class ComponentVillage extends StructureComponent
{

    protected ComponentVillage(int i)
    {
        super(i);
    }

    protected StructureComponent func_35077_a(ComponentVillageStartPiece componentvillagestartpiece, List list, Random random, int i, int j)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.minX - 1, boundingBox.minY + i, boundingBox.minZ + j, 1, func_35012_c());

        case 0: // '\0'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.minX - 1, boundingBox.minY + i, boundingBox.minZ + j, 1, func_35012_c());

        case 1: // '\001'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.minX + j, boundingBox.minY + i, boundingBox.minZ - 1, 2, func_35012_c());

        case 3: // '\003'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.minX + j, boundingBox.minY + i, boundingBox.minZ - 1, 2, func_35012_c());
        }
        return null;
    }

    protected StructureComponent func_35076_b(ComponentVillageStartPiece componentvillagestartpiece, List list, Random random, int i, int j)
    {
        switch(coordBaseMode)
        {
        case 2: // '\002'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.maxX + 1, boundingBox.minY + i, boundingBox.minZ + j, 3, func_35012_c());

        case 0: // '\0'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.maxX + 1, boundingBox.minY + i, boundingBox.minZ + j, 3, func_35012_c());

        case 1: // '\001'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.minX + j, boundingBox.minY + i, boundingBox.maxZ + 1, 0, func_35012_c());

        case 3: // '\003'
            return StructureVillagePieces.getNextStructureComponent(componentvillagestartpiece, list, random, boundingBox.minX + j, boundingBox.minY + i, boundingBox.maxZ + 1, 0, func_35012_c());
        }
        return null;
    }

    protected int getAverageGroundLevel(World world, StructureBoundingBox structureboundingbox)
    {
        int i = 0;
        int j = 0;
        for(int k = boundingBox.minZ; k <= boundingBox.maxZ; k++)
        {
            for(int l = boundingBox.minX; l <= boundingBox.maxX; l++)
            {
                if(structureboundingbox.isVecInside(l, 64, k))
                {
                    world.getClass();
                    i += Math.max(world.getTopSolidOrLiquidBlock(l, k), 63);
                    j++;
                }
            }

        }

        if(j == 0)
        {
            return -1;
        } else
        {
            return i / j;
        }
    }

    protected static boolean canVillageGoDeeper(StructureBoundingBox structureboundingbox)
    {
        return structureboundingbox != null && structureboundingbox.minY > 10;
    }
}
