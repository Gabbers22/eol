package eol.weapons;

import java.util.HashMap;

public class WeaponRegistry {
    private static WeaponRegistry instance;
    private HashMap<String, Weapon> weapons = new HashMap<>();
    private HashMap<Integer, Weapon> unlockableKnightWeapons = new HashMap<>(); //wave, weapon
    private HashMap<Integer, Weapon> unlockableMageWeapons = new HashMap<>(); //wave, weapon

    public static synchronized WeaponRegistry getInstance() {
        if (instance == null) {
            instance = new WeaponRegistry();
        }
        return instance;
    }

    private WeaponRegistry() {
        // Melee weapons
        weapons.put("starter_sword", new StarterSword());

        Greatsword greatsword = new Greatsword();
        weapons.put("greatsword", greatsword);
        unlockableKnightWeapons.put(4, greatsword);

        DaggerSword dagger = new DaggerSword();
        weapons.put("dagger", dagger);
        unlockableKnightWeapons.put(8, dagger);

        PlasmaSword plasmaSword = new PlasmaSword();
        weapons.put("plasma_sword", plasmaSword);
        unlockableKnightWeapons.put(12, plasmaSword);

        // Ranged Weapons
        weapons.put("starter_spell", new StarterSpell());

        StormSpell stormSpell = new StormSpell();
        weapons.put("storm_spell", stormSpell);
        unlockableMageWeapons.put(4, stormSpell);

        FireSpell fireSpell = new FireSpell();
        weapons.put("fire_spell", fireSpell);
        unlockableMageWeapons.put(8, fireSpell);

        BeamSpell beamSpell = new BeamSpell();
        weapons.put("beam_spell", beamSpell);
        unlockableMageWeapons.put(12, beamSpell);

        weapons.put("light_cannon", new LightCannon());
    }

    public Weapon getWeaponById(String id) {
        return weapons.get(id);
    }

    public HashMap<Integer, Weapon> getKnightWeapons() {
        return unlockableKnightWeapons;
    }

    public HashMap<Integer, Weapon> getMageWeapons() {
        return unlockableMageWeapons;
    }
    
}
