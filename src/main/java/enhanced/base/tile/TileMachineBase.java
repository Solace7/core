package enhanced.base.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileMachineBase extends TileBase implements IEnergyHandler {
	protected EnergyStorage storage;
	protected ForgeDirection facing;

	public TileMachineBase(int energy) {
		super();
		storage = new EnergyStorage(energy);
	}
	
	public TileMachineBase(int energy, int transferRate) {
		super();
		storage = new EnergyStorage(energy, transferRate);
	}
	
	public TileMachineBase(int energy, int extract, int receive) {
		super();
		storage = new EnergyStorage(energy, receive, extract);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return from != facing;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return from == facing ? 0 : storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return from == facing ? 0 : storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return from == facing ? 0 : storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return from == facing ? 0 : storage.getMaxEnergyStored();
	}
}
