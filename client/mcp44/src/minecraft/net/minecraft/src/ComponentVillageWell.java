// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentVillage, StructureBoundingBox, ComponentVillageStartPiece, StructureVillagePieces, 
//            Block, StructureComponent, World

public class ComponentVillageWell extends ComponentVillage
{

    public ComponentVillageWell(int i, Random random, int j, int k)
    {
        super(i);
        field_35103_b = -1;
        coordBaseMode = random.nextInt(4);
        switch(coordBaseMode)
        {
        case 0: // '\0'
        case 2: // '\002'
            boundingBox = new StructureBoundingBox(j, 64, k, (j + 6) - 1, 78, (k + 6) - 1);
            break;

        default:
            boundingBox = new StructureBoundingBox(j, 64, k, (j + 6) - 1, 78, (k + 6) - 1);
            break;
        }
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.minX - 1, boundingBox.maxY - 4, boundingBox.minZ + 1, 1, func_35012_c());
        StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.maxX + 1, boundingBox.maxY - 4, boundingBox.minZ + 1, 3, func_35012_c());
        StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.minX + 1, boundingBox.maxY - 4, boundingBox.minZ - 1, 2, func_35012_c());
        StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.minX + 1, boundingBox.maxY - 4, boundingBox.maxZ + 1, 0, func_35012_c());
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        if(field_35103_b < 0)
        {
            field_35103_b = getAverageGroundLevel(world, structureboundingbox);
            if(field_35103_b < 0)
            {
                return true;
            }
            boundingBox.offset(0, (field_35103_b - boundingBox.maxY) + 3, 0);
        }
        if(!field_35104_a);
        fillWithBlocks(world, structureboundingbox, 1, 0, 1, 4, 12, 4, Block.cobblestone.blockID, Block.waterMoving.blockID, false);
        placeBlockAtCurrentPosition(world, 0, 0, 2, 12, 2, structureboundingbox);
        placeBlockAtCurrentPosition(world, 0, 0, 3, 12, 2, structureboundingbox);
        placeBlockAtCurrentPosition(world, 0, 0, 2, 12, 3, structureboundingbox);
        placeBlockAtCurrentPosition(world, 0, 0, 3, 12, 3, structureboundingbox);
        placeBlockAtCurrentPosition(world, Block.fence.blockID, 0, 1, 13, 1, structureboundingbox);
        placeBlockAtCurrentPosition(world, Block.fence.blockID, 0, 1, 14, 1, structureboundingbox);
        placeBlockAtCurrentPosition(world, Block.fence.blockID, 0, 4, 13, 1, structureboundingbox);
        placeBlockAtCurrentPosition(world, Block.fence.blockID, 0, 4, 14, 1, structureboundingbox);
        placeBlockAtCurrentPosition(world, Block.fence.blockID, 0, 1, 13, 4, structureboundingbox);
        placeBlockAtCurrentPosition(world, Block.fence.blockID, 0, 1, 14, 4, structureboundingbox);
        placeBlockAtCurrentPosition(world, Block.fence.blockID, 0, 4, 13, 4, structureboundingbox);
        placeBlockAtCurrentPosition(world, Block.fence.blockID, 0, 4, 14, 4, structureboundingbox);
        fillWithBlocks(world, structureboundingbox, 1, 15, 1, 4, 15, 4, Block.cobblestone.blockID, Block.cobblestone.blockID, false);
        for(int i = 0; i <= 5; i++)
        {
            for(int j = 0; j <= 5; j++)
            {
                if(j == 0 || j == 5 || i == 0 || i == 5)
                {
                    placeBlockAtCurrentPosition(world, Block.gravel.blockID, 0, j, 11, i, structureboundingbox);
                    clearCurrentPositionBlocksUpwards(world, j, 12, i, structureboundingbox);
                }
            }

        }

        return true;
    }

    private final boolean field_35104_a = true;
    private int field_35103_b;
}
