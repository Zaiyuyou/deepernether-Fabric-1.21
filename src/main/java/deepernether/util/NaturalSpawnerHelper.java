package deepernether.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;

public class NaturalSpawnerHelper {
    public static BlockPos getRandomPosWithin(Level level, LevelChunk chunk) {
        // System.out.println("[DeeperNether] 修改后的生成坐标计算");
        BlockPos original = NaturalSpawner.getRandomPosWithin(level, chunk);
        
        if (level instanceof ServerLevel serverLevel && serverLevel.dimension() == Level.NETHER) {
            int x = original.getX();
            int z = original.getZ();
            //重新引入世界表面高度进行计算
            int m = level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z) + 1;
            int y = Mth.randomBetweenInclusive(level.random, 1, m);
            
            return new BlockPos(x, y, z);
        }
        return original;
    }
}