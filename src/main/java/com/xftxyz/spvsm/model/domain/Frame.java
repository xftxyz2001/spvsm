package com.xftxyz.spvsm.model.domain;

/**
 * 页框类
 */
public class Frame {

	// 以下的属性都是public的，方便在Memory类中被访问
	public int frameNum; // 页框号
	public int beginAddress; // 该页框起始地址
	public boolean used; // 标志该页框是否使用

	// 该页框现在被那个进程使用，不是页框必须的信息，只是为了展示内存时更直观
	public String id;

	/**
	 * 构造函数
	 * 
	 * @param frameNum     页框号
	 * @param beginAddress 该页框起始地址
	 */
	public Frame(int frameNum, int beginAddress) {
		super();
		this.frameNum = frameNum;
		this.beginAddress = beginAddress;
		setUnused();
	}

	/**
	 * 设置该页框被使用
	 * 
	 * @param id 使用该页框的进程id
	 */
	public void setUsed(String id) {
		this.used = true;
		this.id = id;
	}

	/**
	 * 设置该页框未使用
	 */
	public void setUnused() {
		this.used = false;
		this.id = null;
	}
}
