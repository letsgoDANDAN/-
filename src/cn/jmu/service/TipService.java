package cn.jmu.service;

import cn.jmu.po.Tip;

public interface TipService {
	
	public int getBookNumber(String ISBN,String branchID);
	public int addTip(Tip tip);
	public int updateTip(Tip tip);

}
