/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.World
 *  org.bukkit.WorldCreator
 *  org.bukkit.generator.ChunkGenerator
 *  org.bukkit.generator.ChunkGenerator$BiomeGrid
 *  org.bukkit.generator.ChunkGenerator$ChunkData
 */
package vn.giakhanhvn.skysim.util;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;

public class BlankWorldCreator
extends WorldCreator {
    public BlankWorldCreator(String name) {
        super(name);
    }

    public ChunkGenerator generator() {
        return new ChunkGenerator(){

            public ChunkGenerator.ChunkData generateChunkData(World world, Random random, int x, int z, ChunkGenerator.BiomeGrid biome) {
                return this.createChunkData(world);
            }

            public byte[] generate(World world, Random random, int x, int z) {
                return new byte[32768];
            }

            public byte[][] generateBlockSections(World world, Random random, int x, int z, ChunkGenerator.BiomeGrid biomes) {
                return new byte[16][16];
            }

            public short[][] generateExtBlockSections(World world, Random random, int x, int z, ChunkGenerator.BiomeGrid biomes) {
                return new short[world.getMaxHeight() / 16][];
            }
        };
    }
}

