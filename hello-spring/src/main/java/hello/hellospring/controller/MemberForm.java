package hello.hellospring.controller;

/**
 * formData가 아마 JSON으로 올거고
 * 그걸 클래스로 매핑하려고 만든거.
 * /member/new에서 쏜 FormData는 이 클래스가 될것.
 */
public class MemberForm {
    // form태그의 name에 해당하는 name이 자동으로 매핑됨
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
