package cn.jmu.service;

import java.util.List;

import cn.jmu.po.Library;

public interface LibraryService {
	
	public String getLibraryName(String branchID);

	public List<Library> findall();
}
