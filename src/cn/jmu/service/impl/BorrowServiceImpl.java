package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.BorrowMapper;
import cn.jmu.po.Borrow;
import cn.jmu.service.BorrowService;

@Service
public class BorrowServiceImpl implements BorrowService {

	@Resource(name="borrowMapper")
	private BorrowMapper borrowMapper;
	
	@Override
	public int addBorrow(Borrow borrow) {
		// TODO Auto-generated method stub
		return this.borrowMapper.addBorrow(borrow);
	}

	@Override
	public int updateBorrow(Borrow borrow) {
		// TODO Auto-generated method stub
		return this.borrowMapper.updateBorrow(borrow);
	}

	@Override
	public List<Borrow> findAll() {
		// TODO Auto-generated method stub
		return this.borrowMapper.findAll();
	}

	@Override
	public List<Borrow> findByUserid(String userid) {
		// TODO Auto-generated method stub
		return this.borrowMapper.findByUserid(userid);
	}

	@Override
	public Borrow findOne(String userid, String bookID) {
		// TODO Auto-generated method stub
		return this.borrowMapper.findOne(userid, bookID);
	}

	@Override
	public Borrow findByBookID(String bookID) {
		// TODO Auto-generated method stub
		return this.borrowMapper.findByBookID(bookID);
	}

	@Override
	public int Return(Borrow borrow) {
		// TODO Auto-generated method stub
		return this.borrowMapper.Return(borrow);
	}

}
