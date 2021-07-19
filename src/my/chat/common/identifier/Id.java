package my.chat.common.identifier;

public class Id {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println(name);
        this.name = name;
    }
}
