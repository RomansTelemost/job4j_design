package ru.job4j.question;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Analyze {


    public static void main(String[] args) {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set previous = Set.of(u1, u2, u3);
        Set current = Set.of(u1, new User(2, "BB"), u3);
        diff(previous, current);
    }
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
