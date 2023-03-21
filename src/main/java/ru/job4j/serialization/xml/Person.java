package ru.job4j.serialization.xml;

public class Person {

    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String[] statuses;

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "<?xml version=\"1.1\" encoding=\"UTF-8\" ?>\n"
                + "<person sex=" + sex + " age=" + age + ">\n"
                + "    <contact phone=" + contact + "/>\n"
                + "    <statuses>\n"
                + getStatuses()
                + "    </statuses>\n"
                + "</person>";
    }

    private String getStatuses() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < statuses.length; i++) {
            stringBuilder.append("\t\t<status>");
            stringBuilder.append(statuses[i]);
            stringBuilder.append("</status>");
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        System.out.println(person);
    }
}
