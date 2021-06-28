package cn.jmu.controller;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



import cn.jmu.po.Book;
import cn.jmu.po.BookDescribe;
import cn.jmu.po.BookType;
import cn.jmu.po.Borrow;
import cn.jmu.po.BorrowConditions;
import cn.jmu.po.Reader;
import cn.jmu.po.Ticket;
import cn.jmu.service.AuthorService;
import cn.jmu.service.BookDescribeService;
import cn.jmu.service.BookService;
import cn.jmu.service.BookTypeService;
import cn.jmu.service.BorrowConditionsService;
import cn.jmu.service.BorrowService;
import cn.jmu.service.BorrowTypeService;
import cn.jmu.service.LibraryService;
import cn.jmu.service.PressService;
import cn.jmu.service.ReaderService;
import cn.jmu.service.ReaderTypeService;
import cn.jmu.service.TicketService;
import cn.jmu.service.TicketTypeService;
import cn.jmu.service.UnitService;
import cn.jmu.service.WritesService;

@Controller
public class JumpController {
	
	@Resource(name="bookDescribeServiceImpl")
	private BookDescribeService bookDescribeService;
	
	@Resource(name="readerTypeServiceImpl")
	private ReaderTypeService readerTypeService;
	
	@Resource(name="unitServiceImpl")
	private UnitService unitService;
	
	@Resource(name="readerServiceImpl")
	private ReaderService readerService;
	
	@Resource(name="writesServiceImpl")
	private WritesService writesService;
	
	@Resource(name="authorServiceImpl")
	private AuthorService authorService;
	
	@Resource(name="pressServiceImpl")
	private PressService pressService;
	
	@Resource(name="bookTypeServiceImpl")
	private BookTypeService bookTypeService;
 
	@Resource(name="borrowTypeServiceImpl")
	private BorrowTypeService borrowTypeService;
	
	@Resource(name="borrowConditionsServiceImpl")
	private BorrowConditionsService borrowConditionsService;
	
	@Resource(name="borrowServiceImpl")
	private BorrowService borrowService;
	
	@Resource(name="bookServiceImpl")
	private BookService bookService;
	
	@Resource(name="libraryServiceImpl")
	private LibraryService libraryService;
	
	@Resource(name="ticketServiceImpl")
	private TicketService ticketService;
	
	@Resource(name="ticketTypeServiceImpl")
	private TicketTypeService ticketTypeService;
	
	//注册用户时加载信息，包括读者类型和单位
	@RequestMapping(value="/registerGo.do")
	public String registerGo(Model m)
	{
		List<String> readertypeID=readerTypeService.getAll();
		List<String> readertypename=new ArrayList<String>();
		for(int i=0;i<readertypeID.size();i++)
		{
			readertypename.add(this.readerTypeService.getReaderTypeName(readertypeID.get(i)));
		}
		m.addAttribute("getreadertype", readertypename);
		m.addAttribute("readertypeID",readertypeID);
		
		List<String> unitID=unitService.getAll();
		List<String> unitname=new ArrayList<String>();
		for(int i=0;i<unitID.size();i++)
		{
			unitname.add(this.unitService.getUnitName(unitID.get(i)));
		}
		m.addAttribute("getunit", unitname);
		m.addAttribute("unitID",unitID);
		return "/reader/register";
	}
	//显示个人信息的跳转，加载个人信息数据
	@RequestMapping(value="/showReader.do")
	public String showReader(Model m,HttpSession session) 
	{
		String name=String.valueOf(session.getAttribute("name"));
		Reader reader=this.readerService.findByName(name);
		String unitname=this.unitService.getUnitName(reader.getUnitID());
		String readertypename=this.readerTypeService.getReaderTypeName(reader.getReadertypeID());
		m.addAttribute("unitname",unitname);
		m.addAttribute("readertypename",readertypename);
		m.addAttribute("reader",reader);
	    
		return "/reader/reader_show";
	}
	//从显示个人界面到更新界面的跳转，除了加载个人信息便于设置默认值，还有传入其他属性
	@RequestMapping(value="/updateReaderGo.do")
	public String updateReaderGo(Model m,HttpSession session)
	{
		String name=String.valueOf(session.getAttribute("name"));
		Reader reader=this.readerService.findByName(name);
		List<String> readertypeID=readerTypeService.getAll();
		List<String> readertypename=new ArrayList<String>();
		for(int i=0;i<readertypeID.size();i++)
		{
			readertypename.add(this.readerTypeService.getReaderTypeName(readertypeID.get(i)));
		}
		m.addAttribute("getreadertype", readertypename);
		m.addAttribute("readertypeID",readertypeID);
		
		List<String> unitID=unitService.getAll();
		List<String> unitname=new ArrayList<String>();
		for(int i=0;i<unitID.size();i++)
		{
			unitname.add(this.unitService.getUnitName(unitID.get(i)));
		}
		m.addAttribute("reader",reader);
		m.addAttribute("getunit", unitname);
		m.addAttribute("unitID",unitID);
		return "/reader/reader_update";
	}
	//图书描述具体信息的加载，同时通过该书类型和个人的读者类型，加载阅读条件，方便借阅
	@RequestMapping(value="/booksShowGo.do")
	public String booksShowGo(String ISBN,Model m,HttpSession session)
	{
		String name=String.valueOf(session.getAttribute("name"));
		Reader reader=this.readerService.findByName(name);
		BookDescribe books=this.bookDescribeService.findByISBN(ISBN);
		m.addAttribute("books",books);
		String booktypename=this.bookTypeService.getByID(books.getSonID()).getCategoryname();
		m.addAttribute("booktypename",booktypename);
		List<String> authorIDs=this.writesService.getAuthorID(ISBN);
		List<String> authornames=new ArrayList<>();
		for(int i=0;i<authorIDs.size();i++)
		{
			String authorname=this.authorService.getAuthorName(authorIDs.get(i));
			authornames.add(authorname);
		}
		m.addAttribute("authornames",authornames);
		String publicname=this.pressService.getPressName(books.getPublicID());
		m.addAttribute("publicname",publicname);
		String borrowtypename=this.borrowTypeService.getBorrowTypeName(books.getBorrowtypeID());
		BorrowConditions borrowConditions=this.borrowConditionsService.selectByType(reader.getReadertypeID(), books.getBorrowtypeID());
		m.addAttribute("borrowtypename",borrowtypename);
		m.addAttribute("borrowConditions",borrowConditions);
		return "/reader/books_show";
	}
	//借阅信息的加载，根据关键词的不同（未完成，已完成，逾期），分别获取不同的借阅list，并返回
	@RequestMapping(value="/borrowShowGo.do")
	private String borrowShowGo(HttpSession session,String key,Model m)
	{
		Date now=new Date();
		String name=String.valueOf(session.getAttribute("name"));
		Reader reader=this.readerService.findByName(name);
		String userid=reader.getUserid();
		List<BookDescribe> bookDescribes=new ArrayList<>();
		List<Borrow> borrows=this.borrowService.findByUserid(userid);
		List<Borrow> borrow=new ArrayList<>();
		List<Book> books=new ArrayList<>();
		List<String> libraryname=new ArrayList<>();
		for(int i=0;i<borrows.size();i++)
		{
			String bookid=borrows.get(i).getBookID();
			Book book=this.bookService.findByBookID(bookid);
			BookDescribe bookd=this.bookDescribeService.findByISBN(book.getISBN());
			String lname=this.libraryService.getLibraryName(book.getBranchID());
			//获取借阅中的书籍借阅信息
			if(key.equals("borrow"))
			{
				if(borrows.get(i).getTime()==null)
				{
					libraryname.add(lname);
					books.add(book);
					bookDescribes.add(bookd);
					borrow.add(borrows.get(i));
				}
				//获取续期待还的信息
			}else if(key.equals("over"))
			{
				if(borrows.get(i).getTime()==null&&now.after(borrows.get(i).getReturntime()))
				{
					libraryname.add(lname);
					books.add(book);
					bookDescribes.add(bookd);
					borrow.add(borrows.get(i));
				}			
				//获取已归还的借阅信息，即历史信息
			}else if(key.equals("finish"))
			{
				if(borrows.get(i).getTime()!=null)
				{
					libraryname.add(lname);
					books.add(book);
					bookDescribes.add(bookd);
					borrow.add(borrows.get(i));
				}
				//获取具体某一个借阅信息，配合消息提示页面使用
			}else
			{
				if(borrows.get(i).getBookID().equals(key))
				{
					libraryname.add(lname);
					books.add(book);
					bookDescribes.add(bookd);
					borrow.add(borrows.get(i));
				}
			}
		}
		m.addAttribute("libraryname",libraryname);
		m.addAttribute("bookDescribes",bookDescribes);
		m.addAttribute("books",books);
		m.addAttribute("borrow",borrow);
		return "/reader/borrow_show";
	}
	//加载罚款信息，根据关键词不同，获取不同的罚单
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ticketShowGo.do")
	private String ticketShowGo(Model m,HttpSession session,String key)
	{
		String rname=String.valueOf(session.getAttribute("name"));
		Reader r=this.readerService.findByName(rname);
		String userid=r.getUserid();
		List<Ticket> ticket=new ArrayList<>();
		List<Ticket> all=this.ticketService.findAll();
		//未处理的罚单
		if(key.equals("no"))
		{
			ticket=(List<Ticket>)session.getAttribute("ttips");
			//获取已处理的罚单
		}else if(key.equals("yes"))
		{
			for(int i=0;i<all.size();i++)
			{
				if(all.get(i).isSettlestatus()==true)
				{
					ticket.add(all.get(i));
				}
			}
			//返回一个值，便于页面判断是否显示支付链接
			m.addAttribute("old","yes");
		}else
		{
			for(int i=0;i<all.size();i++)
			{
				if(all.get(i).getFineid().equals(key))
				{
					ticket.add(all.get(i));
				}
			}
		}
		//返回罚单类型名称
		List<String> tickettypename=new ArrayList<>();
		for(int i=0;i<ticket.size();i++)
		{
			String name=this.ticketTypeService.getTicketTypeName(ticket.get(i).getFinetypeid());
			tickettypename.add(name);
		}
		m.addAttribute("ticketname",tickettypename);
		m.addAttribute("ticket",ticket);
		return "/reader/ticket_show";
	}
	//注销
	@RequestMapping(value="/out.do")
	private String out(HttpSession session)
	{
		session.invalidate();
		return "login_out";
	}
	//提示的跳转
	@RequestMapping(value="/tipGo.do")
	private String tipGo()
	{
		return "/reader/tip";
	}
	
	//管理员查看请求
	@RequestMapping(value="/returnGo.do")
	private String returnGo(Model m)
	{
		//获取待处理的实体书id
		List<Book> books=this.bookService.findByStatus(-1);
		List<String> bookIDs=new ArrayList<>();
		for(int i=0;i<books.size();i++)
		{
			bookIDs.add(books.get(i).getBookID());
		}
		//获取等待处理的借阅信息，包括是否逾期和读者姓名
		List<String> over=new ArrayList<>();
		List<String> names=new ArrayList<>();
		List<Borrow> borrows=new ArrayList<>();
		for (int i = 0; i < bookIDs.size(); i++) {
			Borrow b=this.borrowService.findByBookID(bookIDs.get(i));
			if(b.isReturnstatus()==false)
			{
				String overreturn="未逾期";
				borrows.add(b);
				String name=this.readerService.findByUserID(b.getUserid()).getName();
				names.add(name);
				Date nowtime=new Date();
				if(nowtime.after(b.getReturntime()))
				{
					overreturn="逾期";
				}
				over.add(overreturn);
			}
		}
		m.addAttribute("over",over);
		m.addAttribute("rname",names);
		m.addAttribute("rborrow",borrows);
		return "/admin/return";
	}

}
