package entity;

public class BookType {
	/**
	 * 图书分类
	 */
	private int tid;
	private String tname;//图书类型
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
}
