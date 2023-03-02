package ru.job4j.serialization.json;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class Galactic implements JsonSerializer<Galactic> {

    @SerializedName("planetName")
    private String name;
    private long age;

    private int knowSystems;

    private StarSystem[] habitableStarSystems;

    @Override
    public JsonElement serialize(Galactic galactic, Type type, JsonSerializationContext jsonSerializationContext) {
        return null;
    }

    public static void main(String[] args) {

        Galactic milkyWay = new Galactic();
        milkyWay.name = "Milky Way";
        milkyWay.age = 10_000_000_000L;
        milkyWay.knowSystems = 584;

        StarSystem starSystem = new StarSystem();
        starSystem.name = "Solar system";
        starSystem.countOfPlanets = 8;
        Planet earthPlanet = new Planet();
        earthPlanet.name = "Earth";
        earthPlanet.age = 4_540_000_000L;
        earthPlanet.landArea = 510_000_000;
        earthPlanet.distanceFromStar = 149_000_000L;
        earthPlanet.habitable = true;
        starSystem.habitablePlanets = new Planet[1];
        starSystem.habitablePlanets[0] = earthPlanet;
        milkyWay.habitableStarSystems = new StarSystem[1];
        milkyWay.habitableStarSystems[0] = starSystem;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(milkyWay);
        java.lang.System.out.println(result);
    }
}
