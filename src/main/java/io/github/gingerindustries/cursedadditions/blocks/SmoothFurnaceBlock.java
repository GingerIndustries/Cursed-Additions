package io.github.gingerindustries.cursedadditions.blocks;

import java.util.Random;

import io.github.gingerindustries.cursedadditions.blocks.tileentities.SmoothFurnaceTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SmoothFurnaceBlock extends AbstractFurnaceBlock {

	public SmoothFurnaceBlock() {
		super(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F));
	}

	@Override
	public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
		return new SmoothFurnaceTileEntity();
	}

	@Override
	protected void openContainer(World p_220089_1_, BlockPos p_220089_2_, PlayerEntity p_220089_3_) {
		TileEntity tileentity = p_220089_1_.getBlockEntity(p_220089_2_);
	      if (tileentity instanceof SmoothFurnaceTileEntity) {
	          p_220089_3_.openMenu((INamedContainerProvider)tileentity);
	      }
	}
	
	// Copied verbatim from FurnaceBlock, used for particles/sound
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
		if (p_180655_1_.getValue(LIT)) {
			double d0 = (double)p_180655_3_.getX() + 0.5D;
			double d1 = (double)p_180655_3_.getY();
			double d2 = (double)p_180655_3_.getZ() + 0.5D;
			if (p_180655_4_.nextDouble() < 0.1D) {
				p_180655_2_.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
			}

			Direction direction = p_180655_1_.getValue(FACING);
			Direction.Axis direction$axis = direction.getAxis();
			@SuppressWarnings("unused")
			double d3 = 0.52D;
			double d4 = p_180655_4_.nextDouble() * 0.6D - 0.3D;
			double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
			double d6 = p_180655_4_.nextDouble() * 6.0D / 16.0D;
			double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
			p_180655_2_.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			p_180655_2_.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
		}
	}

}
