package cn.jmu.service;

import java.util.List;

import cn.jmu.po.Reader;

public interface ReaderService {

	public int addReader(Reader reader);
	public int deleteReader(String name);
	public int updateReader(Reader reader);
	public Reader findByName(String name);
	public List<Reader> findAll();
	public int checkReader(String name);
	public Reader findByUserID(String userID);
}
