package cn.jmu.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mysql.cj.Session;

import cn.jmu.po.Book;
import cn.jmu.po.BookDescribe;
import cn.jmu.po.BookType;
import cn.jmu.po.Borrow;
import cn.jmu.po.BorrowConditions;
import cn.jmu.po.Reader;
import cn.jmu.po.Ticket;
import cn.jmu.po.User;
import cn.jmu.service.AuthorService;
import cn.jmu.service.BookDescribeService;
import cn.jmu.service.BookService;
import cn.jmu.service.BookTypeService;
import cn.jmu.service.BorrowConditionsService;
import cn.jmu.service.BorrowService;
import cn.jmu.service.LibraryService;
import cn.jmu.service.PressService;
import cn.jmu.service.ReaderService;
import cn.jmu.service.ReaderTypeService;
import cn.jmu.service.TicketService;
import cn.jmu.service.UnitService;
import cn.jmu.service.UserService;
import cn.jmu.service.WritesService;

@Controller
@SessionAttributes("name")
public class ReaderController {
	

	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@Resource(name="readerServiceImpl")
	private ReaderService readerService;
	
	@Resource(name="readerTypeServiceImpl")
	private ReaderTypeService readerTypeService;
	
	@Resource(name="unitServiceImpl")
	private UnitService unitService;
	
	@Resource(name="bookDescribeServiceImpl")
	private BookDescribeService bookDescribeService;
	
	@Resource(name="writesServiceImpl")
	private WritesService writesService;
	
	@Resource(name="authorServiceImpl")
	private AuthorService authorService;
	

	@Resource(name="pressServiceImpl")
	private PressService pressService;
	
	@Resource(name="bookTypeServiceImpl")
	private BookTypeService bookTypeService;
	
	@Resource(name="libraryServiceImpl")
	private LibraryService libraryService;
	
	@Resource(name="bookServiceImpl")
	private BookService bookService;
	
	@Resource(name="borrowServiceImpl")
	private BorrowService borrowService;
	
	@Resource(name="borrowConditionsServiceImpl")
	private BorrowConditionsService borrowConditionsService;
	
	@Resource(name="ticketServiceImpl")
	private TicketService ticketService;
		
	//登录，会从数据库获取书籍信息，然后跳转图书显示页面。同时对逾期的借阅和罚款进行统计，便于提示
	@RequestMapping(value="/readerlogin.do")	
	public String login(String userid, String password,Model m,HttpSession session) {
		// TODO Auto-generated method stub
		String path="/reader/reader_login_success";
		String name=this.userService.login(userid, password);
		if(name==null||name.equals("")){
			m.addAttribute("loginfail","yes");
			path="login";
		}
		//将姓名存入session
		session.setAttribute("name", name);
		//获取图书相关信息，同时根据各外键获取其他表的属性，便于读者查看信息
		List<BookDescribe> books=this.bookDescribeService.findAll();
		m.addAttribute("books",books);
		List<String> authorIDs=new ArrayList<>();
		List<String> authornames=new ArrayList<>();
		List<String> publicnames =new ArrayList<>();
		List<String> booktypenames=new ArrayList<>();
		
		for(int i=0;i<books.size();i++)
		{
			String publicname=this.pressService.getPressName(books.get(i).getPublicID());
			String booktypename=this.bookTypeService.getByID(books.get(i).getSonID()).getCategoryname();
			authorIDs=this.writesService.getAuthorID(books.get(i).getISBN());
			String authorname=this.authorService.getAuthorName(authorIDs.get(0));
			authornames.add(authorname);
			publicnames.add(publicname);
			booktypenames.add(booktypename);
		}
		m.addAttribute("authornames",authornames);
		m.addAttribute("publicnames",publicnames);
		m.addAttribute("booktypenames",booktypenames);
		//获取逾期借阅和罚款，便于提示
		List<Borrow> borrow =this.borrowService.findByUserid(userid);
		List<Ticket> ticket=this.ticketService.findByUseid(userid);
		List<Borrow> btips=new ArrayList<>();
		List<Ticket> ttips=new ArrayList<>();
		Date now=new Date();
		for(int i=0;i<borrow.size();i++)
		{
			Borrow b=borrow.get(i);		
			if(b.getReturntime().before(now)&&b.isReturnstatus()==true)
			{
				btips.add(b);
			}
		}
		for(int i=0;i<ticket.size();i++)
		{
			Ticket t=ticket.get(i) ;
			if(t.isSettlestatus()==false)
			{
			  ttips.add(t);
			}
		}
		List<BookType> oldbd=this.bookTypeService.findall();
		List<BookType> newbd=new ArrayList<>();
		for(int i=0;i<oldbd.size();i++)
		{
			if(oldbd.get(i).getFatherID()==null)
			{
				newbd.add(oldbd.get(i));
			}
		}
		m.addAttribute("bd",newbd);
		session.setAttribute("btips", btips);
		session.setAttribute("ttips", ttips);
		return path;
	}
	
	//注册账户
	@RequestMapping(value="/addReader.do")
	public String addReader(String userid,String unitID,String password,String role,String name,String Email,String phone,String readertypeID){
		
		String path="/reader/register_success";
		User user=new User(userid, password, role, name, Email, phone);
		Reader reader=new Reader(userid, unitID, readertypeID, password, role, name, Email, phone, true, 0, 0, 0);
	    try{
	    	this.userService.addUser(user);
	    this.readerService.addReader(reader);}
	    catch(Exception e)
	    {
	    	path="/reader/register_failure";
	    }
		return path;
	}
	
	//图书描述的模糊查询，可进行书名和ISBN以及中图分类号进行查询
	@RequestMapping(value="/selectBookDescribe.do")
	public String selectBookDescribe(String key,Model m,HttpSession s)
	{
		if(key==null) key="";
		//根据关键词获取书籍，其他属性获取操作同登录时的获取
		List<BookDescribe> books=this.bookDescribeService.findByKeyword(key);
		try{
		Book b=this.bookService.findByBookID(key);
		BookDescribe bd=this.bookDescribeService.findByISBN(b.getISBN());
		books.add(bd);
		s.setAttribute("borrowbook", b);
		}
		catch(Exception e)
		{
			
		}
	
		m.addAttribute("books",books);
		List<String> authorIDs=new ArrayList<>();
		List<String> authornames=new ArrayList<>();
		List<String> publicnames =new ArrayList<>();
		List<String> booktypenames=new ArrayList<>();
		
		for(int i=0;i<books.size();i++)
		{
			String publicname=this.pressService.getPressName(books.get(i).getPublicID());
			String booktypename=this.bookTypeService.getByID(books.get(i).getSonID()).getCategoryname();
			authorIDs=this.writesService.getAuthorID(books.get(i).getISBN());
			String authorname=this.authorService.getAuthorName(authorIDs.get(0));
			authornames.add(authorname);
			publicnames.add(publicname);
			booktypenames.add(booktypename);
		}
		//获取中图表中根节点，每次模糊查询中图菜单返回到第一层
		List<BookType> oldbd=this.bookTypeService.findall();
		List<BookType> newbd=new ArrayList<>();
		for(int i=0;i<oldbd.size();i++)
		{
			if(oldbd.get(i).getFatherID()==null)
			{
				newbd.add(oldbd.get(i));
			}
		}
		m.addAttribute("bd",newbd);		
		m.addAttribute("authornames",authornames);
		m.addAttribute("publicnames",publicnames);
		m.addAttribute("booktypenames",booktypenames);
		return "/reader/reader_login_success";
	}
	
	//更新用户信息
	@RequestMapping(value="/updateReader.do")
    public String updateReader(String userid,String unitID,String password,String role,String name,String Email,String phone,String readertypeID,boolean borrowstatus,Integer fbooknumber,Integer cbooknumber,Integer booknumber,Model m){
		
		String flag="修改成功！";
		User user=new User(userid, password, role, name, Email, phone);
		Reader reader=new Reader(userid, unitID, readertypeID, password, role, name, Email, phone, borrowstatus, fbooknumber, cbooknumber, booknumber);
       try{
	    	this.userService.updateUser(user);
	        this.readerService.updateReader(reader);}
	    catch(Exception e)
	    {
	    	flag="修改失败！";
	    }
	    m.addAttribute("flag",flag);
	    //获取单位和读者类型的名字
	    String unitname=this.unitService.getUnitName(reader.getUnitID());
		String readertypename=this.readerTypeService.getReaderTypeName(reader.getReadertypeID());
		m.addAttribute("unitname",unitname);
		m.addAttribute("readertypename",readertypename);
		m.addAttribute("reader",reader);
		return "/reader/reader_show";
	}
	//借阅图书
	@RequestMapping(value="/borrowBook.do")
	private String borrowBook(String ISBN,Model m,HttpSession session,Integer borrownum,String borrowtypeID,Integer borrowtime){
		String failure=null;
		List<Ticket> t=(List<Ticket>)session.getAttribute("ttips");
		String name=String.valueOf(session.getAttribute("name"));
		Reader reader=this.readerService.findByName(name);
		//List<Book> books=new ArrayList<>();
		Book book=(Book) session.getAttribute("borrowbook");
		//books=this.bookService.findFreeBook(ISBN);
		//判断读者的借阅状态是否可借，判断读者是否有罚款
		if(reader.isBorrowstatus()==false||t.size()!=0)
		{
			failure="处于黑名单中";
			if(t.size()!=0)
			{
				failure="有罚单待处理";
			}
		}else{
			//判断该图书是否是实体书
		if(book==null)
		{
			failure="请寻找管理员扫码";
		}else
		{

		   int sum=0;
		   //判断该书的借阅类型，再根据用户的读者类型，获取读者此类书籍的借阅上限，进行比对，超过借阅最大数不可借阅
			if(borrowtypeID.equals("BT01"))
			{
				sum=borrownum-reader.getCbooknumber();
			}else if(borrowtypeID.equals("BT02"))
			{
				sum=borrownum-reader.getFbooknumber();
		    }else
		    {
		    	sum=borrownum-reader.getBooknumber()+reader.getCbooknumber()+reader.getFbooknumber();
		    }
			if(sum>0)
			{
				//借阅成功更改实体书的状态
				book.setReaderstatus(0);
				book.setDetails("借阅中");
				//借阅成功更改读者的借阅数量
				if(borrowtypeID.equals("BT01"))
				{
					reader.setCbooknumber(reader.getCbooknumber()+1);
				}else if(borrowtypeID.equals("BT02"))
				{
					reader.setFbooknumber(reader.getFbooknumber()+1);
				}
				reader.setBooknumber(reader.getBooknumber()+1);
				//获取当前时间，创建借阅详单
				Date borrowingtime = new Date(System.currentTimeMillis());

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(borrowingtime);
            calendar.add(Calendar.DATE, borrowtime);
                Date returntime=(Date) calendar.getTime();
                Borrow borrow=new Borrow(reader.getUserid(), book.getBookID(), borrowingtime, returntime, true, false, null);
                m.addAttribute("borrow",borrow);
                try{
                	this.bookService.updateBook(book);
                	this.readerService.updateReader(reader);
                	this.borrowService.addBorrow(borrow);
                }catch(Exception e)
                {
                	e.printStackTrace();
                }
			}else
			{
				failure="达到此类书籍借阅上限";
			}
		}
		}
		//借阅失败的原因
		m.addAttribute("failure",failure);
		return "/reader/reader_borrow_result";		
	}
	//续借图书
	@RequestMapping(value="/renewBook.do")
	private String renewBook(String bookID,String userid,Model m,HttpSession session)
	{
		String failure=null;
		//获取罚单，如果罚单存在，不可借阅
		Borrow borrow= this.borrowService.findByBookID(bookID);
		List<Ticket> t=(List<Ticket>)session.getAttribute("ttips");
		if(t.size()==0)
		{
		//获取阅读条件中的续借天数
		Book b=this.bookService.findByBookID(bookID);
		BookDescribe bd=this.bookDescribeService.findByISBN(b.getISBN());
		Reader r=this.readerService.findByUserID(userid);
		BorrowConditions bc=this.borrowConditionsService.selectByType(r.getReadertypeID(), bd.getBorrowtypeID());
		//根据续借天数，修改应归还时间
		Date oldtime=borrow.getReturntime();
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(oldtime);
        calendar.add(Calendar.DATE, bc.getRenewtime());
        Date newtime=calendar.getTime();
        borrow.setReturntime(newtime);
        //借过一次后，修改借阅状态为不可借，保证只能续借一次
        borrow.setRenewstatus(false);
        try{
        	this.borrowService.updateBorrow(borrow);
        }catch(Exception e){
        	e.printStackTrace();
        }
        }else
        {
        	failure="有罚款待处理";
        }
		m.addAttribute("borrow",borrow);
		m.addAttribute("failure",failure);
		return "/reader/reader_borrow_result";
	}
	
	//支付罚单
	@RequestMapping(value="/payFine.do")
	private String payFine(String fineid,HttpSession session){
		//获取罚单，并修改罚单完成状态
		Ticket t=this.ticketService.findById(fineid);
		t.setSettlestatus(true);
		try{
			this.ticketService.updateTicket(t);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//更新提示
		List<Ticket> oldtips=(List<Ticket>)session.getAttribute("ttips");
		List<Ticket> newtips=new ArrayList<>();
		for(int i=0;i<oldtips.size();i++)
		{			
			if(oldtips.get(i).getFineid().equals(t.getFineid()))
			{
				continue;
			}else
			{
				newtips.add(oldtips.get(i));
			}
		}
		session.setAttribute("ttips",newtips);
		return "/reader/tip";
	}
	//归还图书操作，并不立刻执行归还，需要等待管理员操作
	@RequestMapping(value="/returnBook.do")
	private String returnBook(String bookID,Model m)
	{
		//获取图书，修改状态为-1.
		Book book=this.bookService.findByBookID(bookID);
		book.setReaderstatus(-1);
		try{
			this.bookService.updateBook(book);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		m.addAttribute("return","已提交归还请求，请等待管理员确认！");
		return "/reader/tip";
	}
	//归还图书操作
	@RequestMapping(value="/doReturn.do")
	private String doReturn(Model m,String userid,String bookID,String over) throws ParseException
	{
		//更新借阅信息
		Date rt=new Date();
		Borrow br=this.borrowService.findOne(userid, bookID);
		br.setReturnstatus(true);
		br.setTime(rt);
		//更新实体书 状态
		Book bk=this.bookService.findByBookID(bookID);
		bk.setReaderstatus(0);
		bk.setDetails("可借阅");
		//更新读者信息
		Reader rd=this.readerService.findByUserID(userid);
		String btid=this.bookDescribeService.findByISBN(bk.getISBN()).getBorrowtypeID();
		if(btid.equals("BT01"))
		{
			rd.setCbooknumber(rd.getCbooknumber()-1);
		}else
		{
			rd.setFbooknumber(rd.getFbooknumber()-1);
		}
		rd.setBooknumber(rd.getBooknumber()-1);
		//数据库操作
		try{
			this.borrowService.Return(br);
			this.bookService.updateBook(bk);
			this.readerService.updateReader(rd);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//获取借阅类型
		String readertypeID=this.bookDescribeService.findByISBN(this.bookService.findByBookID(bookID).getISBN()).getBorrowtypeID();
		//根据日期和借阅类型计算罚金
		Date nowtime=new Date();
		Date returntime=this.borrowService.findOne(userid, bookID).getReturntime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    double startDateTime = dateFormat.parse(dateFormat.format(returntime)).getTime();
		double endDateTime = dateFormat.parse(dateFormat.format(nowtime)).getTime();
	    int day=(int)((endDateTime - startDateTime) / (1000 * 3600 * 24));
		double totalfineprice=0;
		if(readertypeID=="BT01")
		{
			totalfineprice=day*0.2;
		}else
		{
			totalfineprice=day*0.5;
		}
		//返回罚金
		m.addAttribute("totalfineprice",totalfineprice);
		//返回逾期情况，以便设置罚单
		m.addAttribute("over",over);
		return "/admin/return_result";
	}
	
	//点击中图索引的链接后，进行对图书分类号的模糊查询
	@RequestMapping(value="/selectByTree.do")
	private String selectBtTreeGo(String tkey,Model m)
	{
		//获取分类号，即为模糊查询的关键词
		List<BookType> bd=this.bookTypeService.getSon(tkey);
		List<BookType> bn=new ArrayList<>();
		//进行一次递归操作，依次向上获取父节点，直到根节点结束，获取一个分类号的list
		bn=addList(tkey, bn);
		m.addAttribute("bd",bd);
		m.addAttribute("bn",bn);
		List<BookDescribe> books=this.bookDescribeService.findByKeyword(tkey);
		m.addAttribute("books",books);
		//获取该分类下图书的信息，包括其他表的属性。
		List<String> authorIDs=new ArrayList<>();
		List<String> authornames=new ArrayList<>();
		List<String> publicnames =new ArrayList<>();
		List<String> booktypenames=new ArrayList<>();
		
		for(int i=0;i<books.size();i++)
		{
			String publicname=this.pressService.getPressName(books.get(i).getPublicID());
			String booktypename=this.bookTypeService.getByID(books.get(i).getSonID()).getCategoryname();
			authorIDs=this.writesService.getAuthorID(books.get(i).getISBN());
			String authorname=this.authorService.getAuthorName(authorIDs.get(0));
			authornames.add(authorname);
			publicnames.add(publicname);
			booktypenames.add(booktypename);
		}
		m.addAttribute("authornames",authornames);
		m.addAttribute("publicnames",publicnames);
		m.addAttribute("booktypenames",booktypenames);
		return "/reader/reader_login_success";
	}
	//服务于中图查询的递归，传入选择的分类号和一个空list，通过递归依次将父节点的数据存入list，直到根节点返回该list
	private List<BookType> addList(String id,List<BookType> L)
	{
		BookType bt=this.bookTypeService.getByID(id);
		L.add(bt);
		if(bt.getFatherID()!=null)
		{
			return addList(bt.getFatherID(),L);
		}else
		{
			return L;
		}
	}
}
