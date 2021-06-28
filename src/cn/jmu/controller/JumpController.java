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
	
	//ע���û�ʱ������Ϣ�������������ͺ͵�λ
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
	//��ʾ������Ϣ����ת�����ظ�����Ϣ����
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
	//����ʾ���˽��浽���½������ת�����˼��ظ�����Ϣ��������Ĭ��ֵ�����д�����������
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
	//ͼ������������Ϣ�ļ��أ�ͬʱͨ���������ͺ͸��˵Ķ������ͣ������Ķ��������������
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
	//������Ϣ�ļ��أ����ݹؼ��ʵĲ�ͬ��δ��ɣ�����ɣ����ڣ����ֱ��ȡ��ͬ�Ľ���list��������
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
			//��ȡ�����е��鼮������Ϣ
			if(key.equals("borrow"))
			{
				if(borrows.get(i).getTime()==null)
				{
					libraryname.add(lname);
					books.add(book);
					bookDescribes.add(bookd);
					borrow.add(borrows.get(i));
				}
				//��ȡ���ڴ�������Ϣ
			}else if(key.equals("over"))
			{
				if(borrows.get(i).getTime()==null&&now.after(borrows.get(i).getReturntime()))
				{
					libraryname.add(lname);
					books.add(book);
					bookDescribes.add(bookd);
					borrow.add(borrows.get(i));
				}			
				//��ȡ�ѹ黹�Ľ�����Ϣ������ʷ��Ϣ
			}else if(key.equals("finish"))
			{
				if(borrows.get(i).getTime()!=null)
				{
					libraryname.add(lname);
					books.add(book);
					bookDescribes.add(bookd);
					borrow.add(borrows.get(i));
				}
				//��ȡ����ĳһ��������Ϣ�������Ϣ��ʾҳ��ʹ��
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
	//���ط�����Ϣ�����ݹؼ��ʲ�ͬ����ȡ��ͬ�ķ���
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ticketShowGo.do")
	private String ticketShowGo(Model m,HttpSession session,String key)
	{
		String rname=String.valueOf(session.getAttribute("name"));
		Reader r=this.readerService.findByName(rname);
		String userid=r.getUserid();
		List<Ticket> ticket=new ArrayList<>();
		List<Ticket> all=this.ticketService.findAll();
		//δ����ķ���
		if(key.equals("no"))
		{
			ticket=(List<Ticket>)session.getAttribute("ttips");
			//��ȡ�Ѵ���ķ���
		}else if(key.equals("yes"))
		{
			for(int i=0;i<all.size();i++)
			{
				if(all.get(i).isSettlestatus()==true)
				{
					ticket.add(all.get(i));
				}
			}
			//����һ��ֵ������ҳ���ж��Ƿ���ʾ֧������
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
		//���ط�����������
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
	//ע��
	@RequestMapping(value="/out.do")
	private String out(HttpSession session)
	{
		session.invalidate();
		return "login_out";
	}
	//��ʾ����ת
	@RequestMapping(value="/tipGo.do")
	private String tipGo()
	{
		return "/reader/tip";
	}
	
	//����Ա�鿴����
	@RequestMapping(value="/returnGo.do")
	private String returnGo(Model m)
	{
		//��ȡ�������ʵ����id
		List<Book> books=this.bookService.findByStatus(-1);
		List<String> bookIDs=new ArrayList<>();
		for(int i=0;i<books.size();i++)
		{
			bookIDs.add(books.get(i).getBookID());
		}
		//��ȡ�ȴ�����Ľ�����Ϣ�������Ƿ����ںͶ�������
		List<String> over=new ArrayList<>();
		List<String> names=new ArrayList<>();
		List<Borrow> borrows=new ArrayList<>();
		for (int i = 0; i < bookIDs.size(); i++) {
			Borrow b=this.borrowService.findByBookID(bookIDs.get(i));
			if(b.isReturnstatus()==false)
			{
				String overreturn="δ����";
				borrows.add(b);
				String name=this.readerService.findByUserID(b.getUserid()).getName();
				names.add(name);
				Date nowtime=new Date();
				if(nowtime.after(b.getReturntime()))
				{
					overreturn="����";
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
