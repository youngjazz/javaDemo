package concurrency.bookcode.ThreadPool;

/**
 * Created by zhangyang on 2017/9/4.
 */
public class Data {
	private int id;
	private String name;

	public Data(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Data{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
