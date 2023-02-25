package ru.job4j.question;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> map = new HashMap<>();
        previous.forEach(val -> map.put(val.getId(), val.getName()));

        for (User user : current) {
            Optional<String> name = Optional.ofNullable(map.remove(user.getId()));
            if (name.isEmpty()) {
                added++;
            } else if (!user.getName().equals(name.get())) {
                changed++;
            }
        }
        deleted = map.size();
        return new Info(added, changed, deleted);
    }
}
