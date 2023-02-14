package ru.job4j.collection;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar calendar = Calendar.getInstance();
        User userIvan = new User("Ivan", 20, calendar);
        int hashCode1 = userIvan.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User userStepan = new User("Ivan", 20, calendar);
        int hashCode2 = userStepan.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        map.put(userIvan, new Object());
        map.put(userStepan, new Object());
        System.out.printf("userIvan - хешкод: %s, хэш: %s, бакет: %s",
                hashCode1, hash1, bucket1);
        System.out.printf("userStepan - хешкод: %s, хэш: %s, бакет: %s",
                hashCode2, hash2, bucket2);
    }
}
