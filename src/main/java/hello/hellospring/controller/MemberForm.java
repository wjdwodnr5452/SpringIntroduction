package hello.hellospring.controller;

public class MemberForm {

    private String name;

    public String getName() {
        return name;
    }

    // spring이 넘겨온 값을 setName을 통해 넣어준다.
    public void setName(String name) {
        this.name = name;
    }
}
