// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.*;

// Referenced classes of package net.minecraft.src:
//            StructureStrongholdPieceWeight, ComponentStrongholdStraight, ComponentStrongholdPrison, ComponentStrongholdLeftTurn, 
//            ComponentStrongholdRightTurn, ComponentStrongholdRoomCrossing, ComponentStrongholdStairsStraight, ComponentStrongholdStairs, 
//            ComponentStrongholdCrossing, ComponentStrongholdLibrary, ComponentStrongholdStairs2, ComponentStrongholdCorridor, 
//            StructureBoundingBox, StructureStrongholdPieceWeight2, StructureStrongholdStones, ComponentStronghold, 
//            StructureComponent

public class StructureStrongholdPieces
{

    public StructureStrongholdPieces()
    {
    }

    public static void prepareStructurePieces()
    {
        structurePieceList = new ArrayList();
        StructureStrongholdPieceWeight astructurestrongholdpieceweight[] = pieceWeightArray;
        int i = astructurestrongholdpieceweight.length;
        for(int j = 0; j < i; j++)
        {
            StructureStrongholdPieceWeight structurestrongholdpieceweight = astructurestrongholdpieceweight[j];
            structurestrongholdpieceweight.instancesSpawned = 0;
            structurePieceList.add(structurestrongholdpieceweight);
        }

    }

    private static boolean canAddStructurePieces()
    {
        boolean flag = false;
        totalWeight = 0;
        for(Iterator iterator = structurePieceList.iterator(); iterator.hasNext();)
        {
            StructureStrongholdPieceWeight structurestrongholdpieceweight = (StructureStrongholdPieceWeight)iterator.next();
            if(structurestrongholdpieceweight.instancesLimit > 0 && structurestrongholdpieceweight.instancesSpawned < structurestrongholdpieceweight.instancesLimit)
            {
                flag = true;
            }
            totalWeight += structurestrongholdpieceweight.pieceWeight;
        }

        return flag;
    }

    private static ComponentStronghold getStrongholdComponentFromWeightedPiece(StructureStrongholdPieceWeight structurestrongholdpieceweight, List list, Random random, int i, int j, int k, int l, int i1)
    {
        Class class1 = structurestrongholdpieceweight.pieceClass;
        Object obj = null;
        if(class1 == (net.minecraft.src.ComponentStrongholdStraight.class))
        {
            obj = ComponentStrongholdStraight.func_35047_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdPrison.class))
        {
            obj = ComponentStrongholdPrison.func_35063_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdLeftTurn.class))
        {
            obj = ComponentStrongholdLeftTurn.func_35045_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdRightTurn.class))
        {
            obj = ComponentStrongholdRightTurn.func_35045_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdRoomCrossing.class))
        {
            obj = ComponentStrongholdRoomCrossing.func_35059_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdStairsStraight.class))
        {
            obj = ComponentStrongholdStairsStraight.func_35053_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdStairs.class))
        {
            obj = ComponentStrongholdStairs.getStrongholdStairsComponent(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdCrossing.class))
        {
            obj = ComponentStrongholdCrossing.func_35039_a(list, random, i, j, k, l, i1);
        } else
        if(class1 == (net.minecraft.src.ComponentStrongholdLibrary.class))
        {
            obj = ComponentStrongholdLibrary.func_35055_a(list, random, i, j, k, l, i1);
        }
        return ((ComponentStronghold) (obj));
    }

    private static ComponentStronghold func_35847_b(ComponentStrongholdStairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        if(!canAddStructurePieces()) {
           return null;
        } else {
           int var8 = 0;

           while(var8 < 5) {
              ++var8;
              int var9 = var2.nextInt(totalWeight);
              Iterator var10 = structurePieceList.iterator();

              while(var10.hasNext()) {
                 StructureStrongholdPieceWeight var11 = (StructureStrongholdPieceWeight)var10.next();
                 var9 -= var11.pieceWeight;
                 if(var9 < 0) {
                    if(!var11.canSpawnMoreStructuresOfType(var7) || var11 == var0.field_35038_a) {
                       break;
                    }

                    ComponentStronghold var12 = getStrongholdComponentFromWeightedPiece(var11, var1, var2, var3, var4, var5, var6, var7);
                    if(var12 != null) {
                       ++var11.instancesSpawned;
                       var0.field_35038_a = var11;
                       if(!var11.canSpawnMoreStructures()) {
                          structurePieceList.remove(var11);
                       }

                       return var12;
                    }
                 }
              }
           }

           StructureBoundingBox var13 = ComponentStrongholdCorridor.func_35051_a(var1, var2, var3, var4, var5, var6);
           if(var13 != null && var13.minY > 1) {
              return new ComponentStrongholdCorridor(var7, var2, var13, var6);
           } else {
              return null;
           }
        }
     }

    private static StructureComponent func_35848_c(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i, int j, int k, int l, int i1)
    {
        if(i1 > 50)
        {
            return null;
        }
        if(Math.abs(i - componentstrongholdstairs2.getBoundingBox().minX) > 112 || Math.abs(k - componentstrongholdstairs2.getBoundingBox().minZ) > 112)
        {
            return null;
        }
        ComponentStronghold componentstronghold = func_35847_b(componentstrongholdstairs2, list, random, i, j, k, l, i1 + 1);
        if(componentstronghold != null)
        {
            list.add(componentstronghold);
            componentstrongholdstairs2.field_35037_b.add(componentstronghold);
        }
        return componentstronghold;
    }

    static StructureComponent func_35850_a(ComponentStrongholdStairs2 componentstrongholdstairs2, List list, Random random, int i, int j, int k, int l, int i1)
    {
        return func_35848_c(componentstrongholdstairs2, list, random, i, j, k, l, i1);
    }

    static StructureStrongholdStones getStrongholdStones()
    {
        return field_35854_d;
    }

    private static final StructureStrongholdPieceWeight pieceWeightArray[];
    private static List structurePieceList;
    static int totalWeight = 0;
    private static final StructureStrongholdStones field_35854_d = new StructureStrongholdStones(null);

    static 
    {
        pieceWeightArray = (new StructureStrongholdPieceWeight[] {
            new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdStraight.class, 40, 0), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdPrison.class, 5, 5), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdLeftTurn.class, 20, 0), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdRightTurn.class, 20, 0), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdRoomCrossing.class, 10, 6), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdStairsStraight.class, 5, 10), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdStairs.class, 5, 10), new StructureStrongholdPieceWeight(net.minecraft.src.ComponentStrongholdCrossing.class, 5, 4), new StructureStrongholdPieceWeight2(net.minecraft.src.ComponentStrongholdLibrary.class, 10, 1)
        });
    }
}
