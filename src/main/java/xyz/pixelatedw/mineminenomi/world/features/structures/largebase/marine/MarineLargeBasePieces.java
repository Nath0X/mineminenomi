package xyz.pixelatedw.mineminenomi.world.features.structures.largebase.marine;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper.StructureFaction;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModLootTables;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.debug.WyDebug;

public class MarineLargeBasePieces
{
	private static final ResourceLocation PRISON = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/prison");
	private static final ResourceLocation PRISON_STAIRS = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/stairs");
	private static final ResourceLocation BOTTOM0 = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/bottom0");
	private static final ResourceLocation BOTTOM1 = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/bottom1");
	private static final ResourceLocation BOTTOM2 = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/bottom2");
	private static final ResourceLocation BOTTOM3 = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/bottom3");
	private static final ResourceLocation UPPER_LOBBY = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/upper_lobby");
	private static final ResourceLocation OFFICE = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/office");
	private static final ResourceLocation ROOF = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/roof");
	private static final ResourceLocation FLAG = new ResourceLocation(APIConfig.PROJECT_ID, "large_base/marine/flag");

	private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
		.put(BOTTOM0, new BlockPos(0, 0, 0))
		.put(BOTTOM1, new BlockPos(0, 0, 20))
		.put(BOTTOM2, new BlockPos(-19, 0, 20))
		.put(BOTTOM3, new BlockPos(-19, 0, 0))
		.put(PRISON, new BlockPos(-11, -19, 4))
		.put(PRISON_STAIRS, new BlockPos(12, -4, 15))
		.put(UPPER_LOBBY, new BlockPos(-15, 26, 4))
		.put(OFFICE, new BlockPos(-13, 36, 6))
		.put(ROOF, new BlockPos(-12, 47, 7))
		.put(FLAG, new BlockPos(4, 48, -10))
		.build();
	
	private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
		.put(BOTTOM0, new BlockPos(0, 0, 0))
		.put(BOTTOM1, new BlockPos(0, 0, 0))
		.put(BOTTOM2, new BlockPos(0, 0, 0))
		.put(BOTTOM3, new BlockPos(0, 0, 0))
		.put(PRISON, new BlockPos(0, 0, 0))
		.put(PRISON_STAIRS, new BlockPos(0, 0, 0))
		.put(UPPER_LOBBY, new BlockPos(0, 0, 0))
		.put(OFFICE, new BlockPos(0, 0, 0))
		.put(ROOF, new BlockPos(0, 0, 0))
		.put(FLAG, new BlockPos(0, 0, 0))
		.build();

	public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components)
	{
		components.add(new Piece(templateManager, BOTTOM0, pos, BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, BOTTOM1, pos, BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, BOTTOM2, pos, BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, BOTTOM3, pos, BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, PRISON, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, PRISON_STAIRS, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, UPPER_LOBBY, pos, BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, OFFICE, pos, BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, ROOF, pos, BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, FLAG, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
	}

	public static class Piece extends TemplateStructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;
		private StructureProcessor processor;
		private BlockPos centerPosition;

		public Piece(TemplateManager template, CompoundNBT nbt)
		{
			super(ModFeatures.Pieces.MARINE_LARGE_BASE_PIECE, nbt);
			this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			int centerX = nbt.getShort("CenterX");
			int centerY = nbt.getShort("CenterY");
			int centerZ = nbt.getShort("CenterZ");
			this.centerPosition = new BlockPos(centerX, centerY, centerZ);
			this.processor = BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
			this.build(template);
		}

		public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, StructureProcessor proc)
		{
			super(ModFeatures.Pieces.MARINE_LARGE_BASE_PIECE, 0);
			this.rotation = Rotation.NONE;
			this.resourceLocation = res;
			BlockPos blockpos = POSITION_OFFSET.get(this.resourceLocation);
			this.centerPosition = pos;
			this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
			this.processor = proc;
			this.build(template);
		}

		@Override
		protected void readAdditional(CompoundNBT nbt)
		{
			super.readAdditional(nbt);
			nbt.putString("Template", this.resourceLocation.toString());
			nbt.putString("Rot", this.rotation.name());
			nbt.putInt("CenterX", this.centerPosition.getX());
			nbt.putInt("CenterY", this.centerPosition.getY());
			nbt.putInt("CenterZ", this.centerPosition.getZ());
		}

		private void build(TemplateManager templateManager)
		{
			Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
			this.setup(template, this.templatePosition, placementsettings);
		}

		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb)
		{
			if (function.equals("captain_chest") && WyHelper.randomDouble() < 0.75)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.MARINE_LARGE_BASE_CAPTAIN_CHEST, rand.nextLong());
				}
			}
			else if (function.equals("food_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.MARINE_LARGE_BASE_FOOD_CHEST, rand.nextLong());
				}
			}
			else if (function.equals("generic_chest") && WyHelper.randomDouble() < 0.5)
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.MARINE_LARGE_BASE_GENERIC_CHEST, rand.nextLong());
				}
			}
			else if (function.equals("lab_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.MARINE_LARGE_BASE_LAB_CHEST, rand.nextLong());
				}
			}
			
			// Spawners
			// 8 x3 Prisoner Spawners - 25%
			// 6 x2 Prisoner Spawners - 25%
			// 3 x1 Captain Spawner
			// 8 x3 Grunt Spawners
			// 3 x5 Grunt Spawners
			StructuresHelper.setupSpawners(function, world, pos, StructureFaction.MARINE);
			
			if(function.equalsIgnoreCase("prisoner_x3_spawn") && WyHelper.randomDouble() < 0.25)
			{
				world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
				TileEntity spawner = world.getTileEntity(pos);
				if (spawner instanceof CustomSpawnerTileEntity)
				{
					((CustomSpawnerTileEntity) spawner).setSpawnerLimit(3);
					((CustomSpawnerTileEntity) spawner).setSpawnerMob(ModEntities.PIRATE_WITH_SWORD);
				}
			}
			else if(function.equalsIgnoreCase("prisoner_x2_spawn") && WyHelper.randomDouble() < 0.25)
			{
				world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
				TileEntity spawner = world.getTileEntity(pos);
				if (spawner instanceof CustomSpawnerTileEntity)
				{
					((CustomSpawnerTileEntity) spawner).setSpawnerLimit(2);
					((CustomSpawnerTileEntity) spawner).setSpawnerMob(ModEntities.BANDIT_WITH_SWORD);
				}
			}
		}

		@Override
		public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox bb, ChunkPos chunkPos)
		{
			if(this.centerPosition == null)
			{
				WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
				return false;
			}
			
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
			BlockPos offset = POSITION_OFFSET.get(this.resourceLocation);
			this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
			
			BlockPos blockpos2 = this.templatePosition;
			int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.centerPosition.getX(), this.centerPosition.getZ());
			this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);

			boolean flag = super.addComponentParts(world, random, bb, chunkPos);
			
			this.templatePosition = blockpos2;
			return flag;
		}
	}
}
