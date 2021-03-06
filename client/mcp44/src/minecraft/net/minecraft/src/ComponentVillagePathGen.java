// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            ComponentVillageRoadPiece, StructureBoundingBox, ComponentVillageStartPiece, StructureComponent, 
//            StructureVillagePieces, MathHelper, World, Block

public class ComponentVillagePathGen extends ComponentVillageRoadPiece
{

    public ComponentVillagePathGen(int i, Random random, StructureBoundingBox structureboundingbox, int j)
    {
        super(i);
        coordBaseMode = j;
        boundingBox = structureboundingbox;
        field_35088_a = Math.max(structureboundingbox.getXSize(), structureboundingbox.getZSize());
    }

    public void buildComponent(StructureComponent structurecomponent, List list, Random random)
    {
        boolean flag = false;
        for(int i = random.nextInt(5); i < field_35088_a - 8; i += 2 + random.nextInt(5))
        {
            StructureComponent structurecomponent1 = func_35077_a((ComponentVillageStartPiece)structurecomponent, list, random, 0, i);
            if(structurecomponent1 != null)
            {
                i += Math.max(structurecomponent1.boundingBox.getXSize(), structurecomponent1.boundingBox.getZSize());
                flag = true;
            }
        }

        for(int j = random.nextInt(5); j < field_35088_a - 8; j += 2 + random.nextInt(5))
        {
            StructureComponent structurecomponent2 = func_35076_b((ComponentVillageStartPiece)structurecomponent, list, random, 0, j);
            if(structurecomponent2 != null)
            {
                j += Math.max(structurecomponent2.boundingBox.getXSize(), structurecomponent2.boundingBox.getZSize());
                flag = true;
            }
        }

        if(flag && random.nextInt(3) > 0)
        {
            switch(coordBaseMode)
            {
            case 2: // '\002'
                StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.minX - 1, boundingBox.minY, boundingBox.minZ, 1, func_35012_c());
                break;

            case 0: // '\0'
                StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.minX - 1, boundingBox.minY, boundingBox.maxZ - 2, 1, func_35012_c());
                break;

            case 3: // '\003'
                StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.maxX - 2, boundingBox.minY, boundingBox.minZ - 1, 2, func_35012_c());
                break;

            case 1: // '\001'
                StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.minX, boundingBox.minY, boundingBox.minZ - 1, 2, func_35012_c());
                break;
            }
        }
        if(flag && random.nextInt(3) > 0)
        {
            switch(coordBaseMode)
            {
            case 2: // '\002'
                StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.maxX + 1, boundingBox.minY, boundingBox.minZ, 3, func_35012_c());
                break;

            case 0: // '\0'
                StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.maxX + 1, boundingBox.minY, boundingBox.maxZ - 2, 3, func_35012_c());
                break;

            case 3: // '\003'
                StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.maxX - 2, boundingBox.minY, boundingBox.maxZ + 1, 0, func_35012_c());
                break;

            case 1: // '\001'
                StructureVillagePieces.getNextStructureComponentVillagePath((ComponentVillageStartPiece)structurecomponent, list, random, boundingBox.minX, boundingBox.minY, boundingBox.maxZ + 1, 0, func_35012_c());
                break;
            }
        }
    }

    public static StructureBoundingBox func_35087_a(ComponentVillageStartPiece componentvillagestartpiece, List list, Random random, int i, int j, int k, int l)
    {
        for(int i1 = 7 * MathHelper.getRandomIntegerInRange(random, 3, 5); i1 >= 7; i1 -= 7)
        {
            StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(i, j, k, 0, 0, 0, 3, 3, i1, l);
            if(StructureComponent.getIntersectingStructureComponent(list, structureboundingbox) == null)
            {
                return structureboundingbox;
            }
        }

        return null;
    }

    public boolean addComponentParts(World world, Random random, StructureBoundingBox structureboundingbox)
    {
        for(int i = boundingBox.minX; i <= boundingBox.maxX; i++)
        {
            for(int j = boundingBox.minZ; j <= boundingBox.maxZ; j++)
            {
                if(structureboundingbox.isVecInside(i, 64, j))
                {
                    int k = world.getTopSolidOrLiquidBlock(i, j) - 1;
                    world.setBlock(i, k, j, Block.gravel.blockID);
                }
            }

        }

        return true;
    }

    private int field_35088_a;
}
