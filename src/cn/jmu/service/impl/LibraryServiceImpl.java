package cn.jmu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jmu.mapper.LibraryMapper;
import cn.jmu.po.Library;
import cn.jmu.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Resource(name="libraryMapper")
	private LibraryMapper libraryMapper;
	
	@Override
	public String getLibraryName(String branchID) {
		// TODO Auto-generated method stub
		return this.libraryMapper.getLibraryName(branchID);
	}
	
	@Override
	public List<Library> findall(){
		return this.libraryMapper.findall();
	}

}
