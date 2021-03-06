package xyz.pixelatedw.mineminenomi.data.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.util.Constants;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.crew.Crew.Member;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.wypi.WyHelper;

public class ExtendedWorldData extends WorldSavedData
{
	private static final String IDENTIFIER = "mineminenomi";

	private HashMap<String, Long> issuedBounties = new HashMap<String, Long>();
	private List<String> devilFruitsInWorld = new ArrayList<String>();
	private List<int[][]> protectedAreas = new ArrayList<int[][]>();
	private HashMap<String, Crew> pirateCrews = new HashMap<String, Crew>();
	private HashMap<String, String> ateDevilFruits = new HashMap<String, String>();

	public static Map<World, ExtendedWorldData> loadedExtWorlds = new HashMap<>();
	
	public ExtendedWorldData()
	{
		super(IDENTIFIER);
	}

	public ExtendedWorldData(String identifier)
	{
		super(identifier);
	}

	public static ExtendedWorldData get(World world)
	{
		if (world == null)
			return null;
		
		ExtendedWorldData worldExt;

		if (loadedExtWorlds.containsKey(world))
		{
			worldExt = loadedExtWorlds.get(world);
			return worldExt;
		}

		if (world instanceof ServerWorld)
		{
			ServerWorld serverWorld = (ServerWorld) world;
			world.increaseMaxEntityRadius(50);
			ExtendedWorldData worldSavedData = serverWorld.getSavedData().get(ExtendedWorldData::new, IDENTIFIER);
			if (worldSavedData != null)
			{
				worldExt = worldSavedData;
			}
			else
			{
				worldExt = new ExtendedWorldData();
				serverWorld.getSavedData().set(worldExt);
			}
		}
		else
		{
			worldExt = new ExtendedWorldData();
		}

		loadedExtWorlds.put(world, worldExt);
		return worldExt;

	}

	@Override
	public void read(CompoundNBT nbt)
	{
		CompoundNBT bounties = nbt.getCompound("issuedBounties");
		this.issuedBounties.clear();
		bounties.keySet().stream().forEach(x ->
		{
			this.issuedBounties.put(x, bounties.getLong(x));
		});

		CompoundNBT devilFruits = nbt.getCompound("devilFruits");
		this.devilFruitsInWorld.clear();
		devilFruits.keySet().stream().forEach(x ->
		{
			this.devilFruitsInWorld.add(x);
		});

		CompoundNBT protectedAreas = nbt.getCompound("protectedAreas");
		this.protectedAreas.clear();
		for (int i = 0; i <= protectedAreas.keySet().size(); i++)
		{
			int[] minPos = protectedAreas.getIntArray("minPos_" + i);
			int[] maxPos = protectedAreas.getIntArray("maxPos_" + i);
			
			if(minPos.length == 3 && maxPos.length == 3)
			{
				this.protectedAreas.add(new int[][]
					{
							minPos, maxPos
					});
			}
		}
		
		this.pirateCrews.clear();
		ListNBT crews = nbt.getList("crews", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < crews.size(); i++)
		{
			CompoundNBT crewNBT = crews.getCompound(i);
			Crew crew = new Crew();
			crew.read(crewNBT);
			this.pirateCrews.put(WyHelper.getResourceName(crew.getName()), crew);
		}	
		
		CompoundNBT ateDevilFruits = nbt.getCompound("ateDevilFruits");
		this.ateDevilFruits.clear();
		ateDevilFruits.keySet().stream().forEach(x ->
		{
			this.ateDevilFruits.put(x, ateDevilFruits.getString(x));
		});
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt)
	{
		CompoundNBT bounties = new CompoundNBT();
		if (this.issuedBounties.size() > 0)
		{
			this.issuedBounties.entrySet().stream().forEach(x ->
			{
				bounties.putLong(x.getKey(), x.getValue());
			});
		}
		nbt.put("issuedBounties", bounties);

		CompoundNBT devilFruits = new CompoundNBT();
		if (this.devilFruitsInWorld.size() > 0)
		{
			this.devilFruitsInWorld.stream().forEach(x ->
			{
				devilFruits.putBoolean(x, true);
			});
		}
		nbt.put("devilFruits", devilFruits);

		CompoundNBT protectedAreas = new CompoundNBT();
		if (this.protectedAreas.size() > 0)
		{
			int i = 0;
			for (int[][] area : this.protectedAreas)
			{
				protectedAreas.putIntArray("minPos_" + i, area[0]);
				protectedAreas.putIntArray("maxPos_" + i, area[1]);
				i++;
			}
		}
		nbt.put("protectedAreas", protectedAreas);
		
		ListNBT crews = new ListNBT();
		for(Crew crew : this.pirateCrews.values())
		{
			crews.add(crew.write());
		}
		nbt.put("crews", crews);
		
		CompoundNBT ateDevilFruits = new CompoundNBT();
		if (this.ateDevilFruits.size() > 0)
		{
			this.ateDevilFruits.entrySet().stream().forEach(x ->
			{
				ateDevilFruits.putString(x.getKey(), x.getValue());
			});
		}
		nbt.put("ateDevilFruits", ateDevilFruits);
		
		return nbt;
	}
	
	public HashMap<String, String> getAteFruits()
	{
		return this.ateDevilFruits;
	}

	public void addAteDevilFruit(PlayerEntity player, AkumaNoMiItem df)
	{
		String key = DevilFruitHelper.getDevilFruitKey(df);
		this.addAteDevilFruit(player, key);
	}
	
	public void addAteDevilFruit(PlayerEntity player, String key)
	{
		this.ateDevilFruits.put(player.getDisplayName().getFormattedText(), key);
		this.markDirty();
	}
	
	public void removeAteDevilFruit(PlayerEntity player)
	{
		this.ateDevilFruits.remove(player.getDisplayName().getFormattedText());
		this.markDirty();
	}
	
	public List<Crew> getCrews()
	{
		return new ArrayList(this.pirateCrews.values());
	}
	
	@Nullable
	public Crew getCrewWithMember(UUID memId)
	{
		for(Crew crew : this.pirateCrews.values())
		{
			for(Member member : crew.getMembers())
			{
				if(member.getUUID().equals(memId))
					return crew;
			}
		}
		
		return null;
	}
	
	@Nullable
	public Crew getCrewWithCaptain(UUID capId)
	{
		return this.pirateCrews.values().stream().filter(crew -> crew.getCaptain() != null && crew.getCaptain().getUUID() == capId).findFirst().orElse(null);
	}
	
	public void removeCrew(Crew crew)
	{
		String key = WyHelper.getResourceName(crew.getName());
		if(this.pirateCrews.containsKey(key))
			this.pirateCrews.remove(key);
		this.markDirty();
	}
	
	public void addCrew(Crew crew)
	{
		String key = WyHelper.getResourceName(crew.getName());
		if(!this.pirateCrews.containsKey(key))
			this.pirateCrews.put(key, crew);
		this.markDirty();
	}
	
	public void removeCrewMember(Crew crew, LivingEntity entity)
	{
		crew.removeMember(entity.getUniqueID());
		this.markDirty();
	}
	
	public void addCrewMember(Crew crew, LivingEntity entity)
	{
		crew.addMember(entity);
		this.markDirty();
	}
	
	public void updateCrewJollyRoger(Crew crew, JollyRoger jollyRoger)
	{
		crew.setJollyRoger(jollyRoger);
		this.markDirty();
	}
	
	public boolean isInsideRestrictedArea(int posX, int posY, int posZ)
	{
		if (this.protectedAreas.size() <= 0)
			return false;

		for (int[][] area : this.protectedAreas)
		{
			int[] minPos = area[0];
			int[] maxPos = area[1];

			if (minPos.length <= 0 || maxPos.length <= 0)
				continue;

			if (posX > minPos[0] && posX < maxPos[0] && posY > minPos[1] && posY < maxPos[1] && posZ > minPos[2] && posZ < maxPos[2])
			{
				return true;
			}
		}

		return false;
	}

	public void addRestrictedArea(int[] minPos, int[] maxPos)
	{
		this.protectedAreas.add(new int[][] { minPos, maxPos });
		this.markDirty();
	}

	public void removeRestrictedArea(int midX, int midY, int midZ)
	{
		Iterator iterator = this.protectedAreas.iterator();
		while (iterator.hasNext())
		{
			int[][] area = (int[][]) iterator.next();
			int[] minPos = area[0];
			int[] maxPos = area[1];

			if (minPos.length <= 0 || maxPos.length <= 0)
				continue;

			int possibleMidX = (minPos[0] + maxPos[0]) / 2;
			int possibleMidY = (minPos[1] + maxPos[1]) / 2;
			int possibleMidZ = (minPos[2] + maxPos[2]) / 2;

			if (midX == possibleMidX && midY == possibleMidY && midZ == possibleMidZ)
				iterator.remove();
		}

		this.markDirty();
	}

	public List<int[][]> getAllRestrictions()
	{
		return this.protectedAreas;
	}

	public HashMap<String, Long> getAllBounties()
	{
		return this.issuedBounties;
	}

	public Object[] getRandomBounty()
	{
		int count = this.getAllBounties().size();
	
		if(count <= 0)
			return null;
		
		Object[] keys = this.getAllBounties().keySet().toArray();
		Object key = keys[new Random().nextInt(count)];
		
		long bounty = this.getAllBounties().get(key);
		
		return new Object[] { key, bounty };
	}
	
	public long getBounty(String uuid)
	{
		if (this.issuedBounties.containsKey(uuid.toLowerCase()))
			return this.issuedBounties.get(uuid.toLowerCase());

		return 0;
	}

	public void issueBounty(String uuid, long bounty)
	{
		if (this.issuedBounties.containsKey(uuid.toLowerCase()))
		{
			this.issuedBounties.remove(uuid.toLowerCase());
			this.issuedBounties.put(uuid.toLowerCase(), bounty);
		}
		else
			this.issuedBounties.put(uuid.toLowerCase(), bounty);

		this.markDirty();
	}
	
	public List<String> getDevilFruitsInWorld()
	{
		return this.devilFruitsInWorld;
	}

	public void removeDevilFruitInWorld(AkumaNoMiItem fruit)
	{
		String name = DevilFruitHelper.getDevilFruitKey(fruit);
		this.removeDevilFruitInWorld(name);
	}

	public void removeDevilFruitInWorld(String name)
	{
		if(!CommonConfig.instance.hasOneFruitPerWorldSimpleLogic())
			return;
		
		if (this.devilFruitsInWorld.contains(name))
		{
			this.devilFruitsInWorld.remove(name);
			this.markDirty();
		}
	}
	
	public void addDevilFruitInWorld(AkumaNoMiItem fruit)
	{
		String name = DevilFruitHelper.getDevilFruitKey(fruit);
		this.addDevilFruitInWorld(name);
	}

	public void addDevilFruitInWorld(String name)
	{
		if(!CommonConfig.instance.hasOneFruitPerWorldSimpleLogic())
			return;
		
		if (!this.devilFruitsInWorld.contains(name))
		{
			this.devilFruitsInWorld.add(name);
			this.markDirty();
		}
	}

	public boolean isDevilFruitInWorld(String name)
	{
		return this.devilFruitsInWorld.contains(name);
	}
}
