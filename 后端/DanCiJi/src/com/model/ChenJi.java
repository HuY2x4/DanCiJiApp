package com.model;

public class ChenJi {
	private int daciku;//4  6//四六级
	private int whichGN;//哪一个功能里的，分别对应1,2,3
	private int chenji;
	private int xiaociku;//1 2 3 。。。。。200+//小词库，每个词库二十个单词
	public int getDaciku() {
		return daciku;
	}
	public void setDaciku(int daciku) {
		this.daciku = daciku;
	}
	public int getWhichGN() {
		return whichGN;
	}
	public void setWhichGN(int whichGN) {
		this.whichGN = whichGN;
	}
	public int getChenji() {
		return chenji;
	}
	public void setChenji(int chenji) {
		this.chenji = chenji;
	}
	public int getXiaociku() {
		return xiaociku;
	}
	public void setXiaociku(int xiaociku) {
		this.xiaociku = xiaociku;
	}
}
