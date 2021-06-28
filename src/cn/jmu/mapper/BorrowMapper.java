package cn.jmu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jmu.po.Borrow;

public interface BorrowMapper {
	public int addBorrow(Borrow borrow);
	public int updateBorrow(Borrow borrow);
	public List<Borrow> findAll();
	public List<Borrow> findByUserid(String userid);
	public Borrow findOne(@Param("userid")String userid,@Param("bookID")String bookID);
	public Borrow findByBookID(String bookID);
	public int Return(Borrow borrow);
}
