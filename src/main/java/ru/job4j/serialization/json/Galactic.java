package ru.job4j.serialization.json;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

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
        starSystem.setName("Solar system");
        starSystem.setCountOfPlanets(8);
        Planet earthPlanet = new Planet();
        earthPlanet.setName("Earth");
        earthPlanet.setAge(4_540_000_000L);
        earthPlanet.setLandArea(510_000_000);
        earthPlanet.setDistanceFromStar(149_000_000L);
        earthPlanet.setHabitable(true);
        starSystem.setHabitablePlanets(List.of(earthPlanet));
        milkyWay.habitableStarSystems = new StarSystem[1];
        milkyWay.habitableStarSystems[0] = starSystem;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(milkyWay);
        java.lang.System.out.println(result);

        /**
         * JSON Object from String
         */
        JSONObject jsonEarth = new JSONObject();
        jsonEarth.put("name", earthPlanet.getName());
        jsonEarth.put("age", earthPlanet.getAge());
        jsonEarth.put("landArea", earthPlanet.getLandArea());
        jsonEarth.put("distanceFromStar", earthPlanet.getDistanceFromStar());
        jsonEarth.put("habitable", earthPlanet.isHabitable());

        JSONObject jsonStarSystem = new JSONObject();
        jsonStarSystem.put("name", starSystem.getName());
        jsonStarSystem.put("countOfPlanets", starSystem.getCountOfPlanets());
        jsonStarSystem.put("habitablePlanets", new JSONArray(List.of(jsonEarth)));

        JSONObject jsonMilkyWay = new JSONObject("{"
                + "\"name\":\"Milky Way\","
                + "\"age\":\"10000000000\","
                + "\"knownSystems\":\"584\","
                + "}");
        jsonMilkyWay.put("habitableStarSystems", jsonStarSystem);
        System.out.println(jsonMilkyWay);
    }
}
