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
		
	//��¼��������ݿ��ȡ�鼮��Ϣ��Ȼ����תͼ����ʾҳ�档ͬʱ�����ڵĽ��ĺͷ������ͳ�ƣ�������ʾ
	@RequestMapping(value="/readerlogin.do")	
	public String login(String userid, String password,Model m,HttpSession session) {
		// TODO Auto-generated method stub
		String path="/reader/reader_login_success";
		String name=this.userService.login(userid, password);
		if(name==null||name.equals("")){
			m.addAttribute("loginfail","yes");
			path="login";
		}
		//����������session
		session.setAttribute("name", name);
		//��ȡͼ�������Ϣ��ͬʱ���ݸ������ȡ����������ԣ����ڶ��߲鿴��Ϣ
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
		//��ȡ���ڽ��ĺͷ��������ʾ
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
	
	//ע���˻�
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
	
	//ͼ��������ģ����ѯ���ɽ���������ISBN�Լ���ͼ����Ž��в�ѯ
	@RequestMapping(value="/selectBookDescribe.do")
	public String selectBookDescribe(String key,Model m,HttpSession s)
	{
		if(key==null) key="";
		//���ݹؼ��ʻ�ȡ�鼮���������Ի�ȡ����ͬ��¼ʱ�Ļ�ȡ
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
		//��ȡ��ͼ���и��ڵ㣬ÿ��ģ����ѯ��ͼ�˵����ص���һ��
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
	
	//�����û���Ϣ
	@RequestMapping(value="/updateReader.do")
    public String updateReader(String userid,String unitID,String password,String role,String name,String Email,String phone,String readertypeID,boolean borrowstatus,Integer fbooknumber,Integer cbooknumber,Integer booknumber,Model m){
		
		String flag="�޸ĳɹ���";
		User user=new User(userid, password, role, name, Email, phone);
		Reader reader=new Reader(userid, unitID, readertypeID, password, role, name, Email, phone, borrowstatus, fbooknumber, cbooknumber, booknumber);
       try{
	    	this.userService.updateUser(user);
	        this.readerService.updateReader(reader);}
	    catch(Exception e)
	    {
	    	flag="�޸�ʧ�ܣ�";
	    }
	    m.addAttribute("flag",flag);
	    //��ȡ��λ�Ͷ������͵�����
	    String unitname=this.unitService.getUnitName(reader.getUnitID());
		String readertypename=this.readerTypeService.getReaderTypeName(reader.getReadertypeID());
		m.addAttribute("unitname",unitname);
		m.addAttribute("readertypename",readertypename);
		m.addAttribute("reader",reader);
		return "/reader/reader_show";
	}
	//����ͼ��
	@RequestMapping(value="/borrowBook.do")
	private String borrowBook(String ISBN,Model m,HttpSession session,Integer borrownum,String borrowtypeID,Integer borrowtime){
		String failure=null;
		List<Ticket> t=(List<Ticket>)session.getAttribute("ttips");
		String name=String.valueOf(session.getAttribute("name"));
		Reader reader=this.readerService.findByName(name);
		//List<Book> books=new ArrayList<>();
		Book book=(Book) session.getAttribute("borrowbook");
		//books=this.bookService.findFreeBook(ISBN);
		//�ж϶��ߵĽ���״̬�Ƿ�ɽ裬�ж϶����Ƿ��з���
		if(reader.isBorrowstatus()==false||t.size()!=0)
		{
			failure="���ں�������";
			if(t.size()!=0)
			{
				failure="�з���������";
			}
		}else{
			//�жϸ�ͼ���Ƿ���ʵ����
		if(book==null)
		{
			failure="��Ѱ�ҹ���Աɨ��";
		}else
		{

		   int sum=0;
		   //�жϸ���Ľ������ͣ��ٸ����û��Ķ������ͣ���ȡ���ߴ����鼮�Ľ������ޣ����бȶԣ�����������������ɽ���
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
				//���ĳɹ�����ʵ�����״̬
				book.setReaderstatus(0);
				book.setDetails("������");
				//���ĳɹ����Ķ��ߵĽ�������
				if(borrowtypeID.equals("BT01"))
				{
					reader.setCbooknumber(reader.getCbooknumber()+1);
				}else if(borrowtypeID.equals("BT02"))
				{
					reader.setFbooknumber(reader.getFbooknumber()+1);
				}
				reader.setBooknumber(reader.getBooknumber()+1);
				//��ȡ��ǰʱ�䣬���������굥
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
				failure="�ﵽ�����鼮��������";
			}
		}
		}
		//����ʧ�ܵ�ԭ��
		m.addAttribute("failure",failure);
		return "/reader/reader_borrow_result";		
	}
	//����ͼ��
	@RequestMapping(value="/renewBook.do")
	private String renewBook(String bookID,String userid,Model m,HttpSession session)
	{
		String failure=null;
		//��ȡ����������������ڣ����ɽ���
		Borrow borrow= this.borrowService.findByBookID(bookID);
		List<Ticket> t=(List<Ticket>)session.getAttribute("ttips");
		if(t.size()==0)
		{
		//��ȡ�Ķ������е���������
		Book b=this.bookService.findByBookID(bookID);
		BookDescribe bd=this.bookDescribeService.findByISBN(b.getISBN());
		Reader r=this.readerService.findByUserID(userid);
		BorrowConditions bc=this.borrowConditionsService.selectByType(r.getReadertypeID(), bd.getBorrowtypeID());
		//���������������޸�Ӧ�黹ʱ��
		Date oldtime=borrow.getReturntime();
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(oldtime);
        calendar.add(Calendar.DATE, bc.getRenewtime());
        Date newtime=calendar.getTime();
        borrow.setReturntime(newtime);
        //���һ�κ��޸Ľ���״̬Ϊ���ɽ裬��ֻ֤������һ��
        borrow.setRenewstatus(false);
        try{
        	this.borrowService.updateBorrow(borrow);
        }catch(Exception e){
        	e.printStackTrace();
        }
        }else
        {
        	failure="�з��������";
        }
		m.addAttribute("borrow",borrow);
		m.addAttribute("failure",failure);
		return "/reader/reader_borrow_result";
	}
	
	//֧������
	@RequestMapping(value="/payFine.do")
	private String payFine(String fineid,HttpSession session){
		//��ȡ���������޸ķ������״̬
		Ticket t=this.ticketService.findById(fineid);
		t.setSettlestatus(true);
		try{
			this.ticketService.updateTicket(t);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//������ʾ
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
	//�黹ͼ���������������ִ�й黹����Ҫ�ȴ�����Ա����
	@RequestMapping(value="/returnBook.do")
	private String returnBook(String bookID,Model m)
	{
		//��ȡͼ�飬�޸�״̬Ϊ-1.
		Book book=this.bookService.findByBookID(bookID);
		book.setReaderstatus(-1);
		try{
			this.bookService.updateBook(book);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		m.addAttribute("return","���ύ�黹������ȴ�����Աȷ�ϣ�");
		return "/reader/tip";
	}
	//�黹ͼ�����
	@RequestMapping(value="/doReturn.do")
	private String doReturn(Model m,String userid,String bookID,String over) throws ParseException
	{
		//���½�����Ϣ
		Date rt=new Date();
		Borrow br=this.borrowService.findOne(userid, bookID);
		br.setReturnstatus(true);
		br.setTime(rt);
		//����ʵ���� ״̬
		Book bk=this.bookService.findByBookID(bookID);
		bk.setReaderstatus(0);
		bk.setDetails("�ɽ���");
		//���¶�����Ϣ
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
		//���ݿ����
		try{
			this.borrowService.Return(br);
			this.bookService.updateBook(bk);
			this.readerService.updateReader(rd);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//��ȡ��������
		String readertypeID=this.bookDescribeService.findByISBN(this.bookService.findByBookID(bookID).getISBN()).getBorrowtypeID();
		//�������ںͽ������ͼ��㷣��
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
		//���ط���
		m.addAttribute("totalfineprice",totalfineprice);
		//��������������Ա����÷���
		m.addAttribute("over",over);
		return "/admin/return_result";
	}
	
	//�����ͼ���������Ӻ󣬽��ж�ͼ�����ŵ�ģ����ѯ
	@RequestMapping(value="/selectByTree.do")
	private String selectBtTreeGo(String tkey,Model m)
	{
		//��ȡ����ţ���Ϊģ����ѯ�Ĺؼ���
		List<BookType> bd=this.bookTypeService.getSon(tkey);
		List<BookType> bn=new ArrayList<>();
		//����һ�εݹ�������������ϻ�ȡ���ڵ㣬ֱ�����ڵ��������ȡһ������ŵ�list
		bn=addList(tkey, bn);
		m.addAttribute("bd",bd);
		m.addAttribute("bn",bn);
		List<BookDescribe> books=this.bookDescribeService.findByKeyword(tkey);
		m.addAttribute("books",books);
		//��ȡ�÷�����ͼ�����Ϣ����������������ԡ�
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
	//��������ͼ��ѯ�ĵݹ飬����ѡ��ķ���ź�һ����list��ͨ���ݹ����ν����ڵ�����ݴ���list��ֱ�����ڵ㷵�ظ�list
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
