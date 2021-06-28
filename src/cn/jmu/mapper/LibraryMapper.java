package cn.jmu.mapper;

import java.util.List;

import cn.jmu.po.Library;

public interface LibraryMapper {
	
	public String getLibraryName(String branchID);

	public List<Library> findall();
}
