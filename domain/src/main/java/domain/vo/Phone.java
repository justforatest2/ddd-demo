package domain.vo;

final public class Phone {
	final private String phone;

	private Phone() {
		phone = "";
	}

	public Phone(String phone) {
//		phone必须是13、15、18开头
		if (!phone.startsWith("13")) {
			throw new IllegalArgumentException("电话号码有误");
		}
		if (phone.length() != 11) {
			throw new IllegalArgumentException("电话号码有误");
		}

		this.phone = phone;
	}

	public boolean isAdminPhone() {
		if (phone.equalsIgnoreCase("13860486986")) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Phone{" +
				"phone='" + phone + '\'' +
				'}';
	}
}
