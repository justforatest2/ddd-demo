package domain.vo;

final public class Contact {
    /**
     * 联系人
     */
    private final String name;

    /**
     * 联系电话
     */
    private final Phone phone;

    /**
     * MyBatis映射需要
     */
    private Contact() {
        name = "";
        phone = null;
    }

    public Contact(String name, String phone) {
        if (name == null || name.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("联系人不能为空");
        }

        this.name = name;
        this.phone = new Phone(phone);
    }

    public String getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }
}
