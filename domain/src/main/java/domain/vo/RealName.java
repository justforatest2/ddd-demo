package domain.vo;

public class RealName {
	private String name;

	public RealName(String name) {
		this.name = name;
		if (!(name.length() >= 2 && name.length() <= 4)) {
			throw new IllegalArgumentException("真实姓名有误");
		}
		// 是否在百家姓

	}
}
