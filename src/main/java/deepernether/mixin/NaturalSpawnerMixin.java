package deepernether.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.NaturalSpawner;
import deepernether.util.NaturalSpawnerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NaturalSpawner.class)
public abstract class NaturalSpawnerMixin {
    
    @Redirect(
        method = "spawnCategoryForChunk(Lnet/minecraft/world/entity/MobCategory;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/LevelChunk;Lnet/minecraft/world/level/NaturalSpawner$SpawnPredicate;Lnet/minecraft/world/level/NaturalSpawner$AfterSpawnCallback;)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/NaturalSpawner;getRandomPosWithin(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/chunk/LevelChunk;)Lnet/minecraft/core/BlockPos;"
        )
    )
    private static BlockPos redirectGetRandomPosWithin(Level level, LevelChunk chunk) {
        return NaturalSpawnerHelper.getRandomPosWithin(level, chunk);
    }
}