package cn.jmu.service;

import java.util.List;


import cn.jmu.po.Borrow;

public interface BorrowService {
	public int addBorrow(Borrow borrow);
	public int updateBorrow(Borrow borrow);
	public List<Borrow> findAll();
	public List<Borrow> findByUserid(String Userid);
	public Borrow findOne(String userid,String bookID);
	public Borrow findByBookID(String bookID);
	public int Return(Borrow borrow);
}
