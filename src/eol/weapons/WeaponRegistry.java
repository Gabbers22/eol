package eol.weapons;

import java.util.HashMap;

public class WeaponRegistry {
    private static WeaponRegistry instance;
    private HashMap<String, Weapon> weapons = new HashMap<>();

    public static synchronized WeaponRegistry getInstance() {
        if (instance == null) {
            instance = new WeaponRegistry();
        }
        return instance;
    }

    private WeaponRegistry() {
        // Melee weapons
        weapons.put("starter_sword", new StarterSword());
        weapons.put("greatsword", new Greatsword());
        weapons.put("dagger", new DaggerSword());
        weapons.put("plasma_sword", new PlasmaSword());

        // Ranged Weapons
        weapons.put("starter_spell", new StarterSpell());
        weapons.put("storm_spell", new StormSpell());
        weapons.put("fire_spell", new FireSpell());
        weapons.put("beam_spell", new BeamSpell());
        weapons.put("light_cannon", new LightCannon());
    }

    public Weapon getWeaponById(String id) {
        return weapons.get(id);
    }
    
}
