package cn.jmu.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;




import cn.jmu.po.Author;
import cn.jmu.po.Book;
import cn.jmu.po.BookDescribe;
import cn.jmu.po.Borrow;

import cn.jmu.po.BorrowType;
import cn.jmu.po.Library;
import cn.jmu.po.Press;
import cn.jmu.po.Reader;
import cn.jmu.po.Ticket;
import cn.jmu.po.TicketType;

import cn.jmu.po.Writes;
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
import cn.jmu.service.UserService;
import cn.jmu.service.WritesService;


@Controller
@SessionAttributes("name")
public class AdminController {
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
	
	@Resource(name="borrowTypeServiceImpl")
	private BorrowTypeService borrowTypeService;
	
	@Resource(name="libraryServiceImpl")
	private LibraryService libraryService;
	
	@Resource(name="bookServiceImpl")
	private BookService bookService;
	
	@Resource(name="borrowConditionsServiceImpl")
	private BorrowConditionsService borrowConditionsService;
	
	@Resource(name="borrowServiceImpl")
	private BorrowService borrowService;
	
	@Resource(name="ticketServiceImpl")
	private TicketService ticketService;
	
	@Resource(name="ticketTypeServiceImpl")
	private TicketTypeService ticketTypeService;
	
	@RequestMapping(value="/adminlogin.do")	
	public String login(String userid, String password,Model m,HttpSession session) {
		// TODO Auto-generated method stub
		String path="admin/login_success";
		String name=this.userService.login(userid, password);
		if(name==null||name.equals("")){
			m.addAttribute("loginfail","yes");
			path="login";
		}
		session.setAttribute("name", name);
		List<BookDescribe> books=this.bookDescribeService.findAll();
		System.out.println(books);
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
		return path;
	}
	
	@RequestMapping(value="/admin/return.do")
	public String returndo(Model m)
	{
		String path="admin/login_success";
		List<BookDescribe> books=this.bookDescribeService.findAll();
		System.out.println(books);
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
		return path;
	}
	
	@RequestMapping(value="/admin/readyAddBookDescribe.do")
	public String readyAddBookDescribe(Model m){
		String path="admin/addBookDescribe";
		List<Author> authors = new ArrayList<>();
		List<Press> presses = new ArrayList<>();
		List<BorrowType> borrowTypes = new ArrayList<>();
		
		authors = this.authorService.findall();
		presses = this.pressService.findall();
		borrowTypes = this.borrowTypeService.findall();
		
		m.addAttribute("authors", authors);
		m.addAttribute("presses", presses);
		m.addAttribute("borrowTypes", borrowTypes);
		return path;
	}
	
	@RequestMapping(value="/admin/addBookDescribe.do")
	public String addBookDescribe(String ISBN, String sonID,String publicID,String borrowtypeID,String bookname,String authorID,double price,String introduce,String picture){
		String path="admin/check_success";
		BookDescribe bookDescribe = new BookDescribe(ISBN, sonID, publicID, borrowtypeID, bookname, price, introduce, picture);
		System.out.println(bookDescribe.toString());
		Writes writes = new Writes(ISBN, authorID);
		System.out.println(writes.toString());
		try {
			this.bookDescribeService.addBookDescribe(bookDescribe);
			this.writesService.addWrite(writes);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			path="admin/check_failure";
		}
		return path;
	}
	
	@RequestMapping(value="/admin/readyModifyBookDescribe.do")
	public String readyModifyBookDescribe(String ISBN,Model m)
	{
		String path="admin/updateBookDescribe";
		//System.out.println(ISBN);
		BookDescribe bookDescribe = this.bookDescribeService.findByISBN(ISBN);
		List<Press> presses = new ArrayList<>();
		List<BorrowType> borrowTypes = new ArrayList<>();
		
		presses = this.pressService.findall();
		borrowTypes = this.borrowTypeService.findall();
		
		m.addAttribute("bookDescribe", bookDescribe);
		m.addAttribute("presses", presses);
		m.addAttribute("borrowTypes", borrowTypes);
		return path;
	}
	
	@RequestMapping(value="/admin/updateBookDescribe.do")
	public String updateBookDescribe(String ISBN, String sonID, String publicID, String borrowtypeID,String bookname, double price,String introduce, String picture,Model m)
	{
		String path="admin/check_success";
		BookDescribe bookDescribe = new BookDescribe(ISBN, sonID, publicID, borrowtypeID, bookname, price, introduce, picture);
		System.out.println(bookDescribe.toString());
		this.bookDescribeService.updateBookDescribe(bookDescribe);
		try {
			//this.bookDescribeService.updateBookDescribe(bookDescribe);
		} catch (Exception e) {
			// TODO: handle exception
			path="admin/admin/check_failure";
		}
		return path;
	}
	
	@RequestMapping(value="/admin/DeleteBookDescribe.do")
	public String deleteBookDescribe(String ISBN)
	{
		String path="admin/check_success";
		try {
			this.writesService.deleteWrite(ISBN);
			this.bookService.deleteBookByISBN(ISBN);
			this.bookDescribeService.deleteBookDescribe(ISBN);
		} catch (Exception e) {
			// TODO: handle exception
			path="admin/admin/check_failure";
		}
		return path;
	}
	
	@RequestMapping(value="/admin/booksShowGo.do")
	public String booksShowGo(String ISBN,Model m)
	{
		List<Book> books = this.bookService.findByISBN(ISBN);
		m.addAttribute("books", books);
		return "admin/books_show";
	}

	@RequestMapping(value="/admin/readyModifyBook.do")
	public String readyUpdateBook(String bookID,Model m)
	{
		String path="admin/updateBook";
		Book book = this.bookService.findByBookID(bookID);
		List<Library> library=this.libraryService.findall();
		m.addAttribute("library",library);
		m.addAttribute("book", book);
		return path;
	}
	
	@RequestMapping(value="/admin/updateBook.do")
	public String updateBook(String bookID, String ISBN, String branchID, Integer readerstatus, String details){
		String path="admin/check_success";
		Book book = new Book(bookID, ISBN, branchID, readerstatus, details);
		System.out.println(book.toString());
		this.bookService.updateBook(book);
		try {
			//this.bookService.updateBook(book);
		} catch (Exception e) {
			// TODO: handle exception
			path="admin/admin/check_failure";
		}
		return path;
	}
	
	@RequestMapping(value="/admin/DeleteBook.do")
	public String deleteBook(String bookID){
		String path="admin/check_success";
		try {
			this.bookService.deleteBook(bookID);
		} catch (Exception e) {
			// TODO: handle exception
			path="admin/check_failure";
		}
		
		return path;
	}
	
	@RequestMapping(value="/admin/readyAddBook.do")
	public String readyAddBook(Model m){
		String path="admin/addBook";
		List<Library> libraries = this.libraryService.findall();
		m.addAttribute("libraries", libraries);
		return path;
	}
	
	@RequestMapping(value="/admin/addBook.do")
	public String addBook(String bookID, String ISBN, String branchID, Integer readerstatus, String details){
		String path="admin/check_success";
		Book book = new Book(bookID, ISBN, branchID, readerstatus, details);
		System.out.println(book.toString());
		this.bookService.addBook(book);
		try {
			//this.bookService.addBook(book);
		} catch (Exception e) {
			// TODO: handle exception
			path="admin/check_failure";
		}
		return path;
	}
	
	@RequestMapping(value="/admin/readyReader_showgo.do")
	public String readyReader_showgo(Model m)
	{
		String path="admin/reader_show";
		List<Reader> readers = this.readerService.findAll();
		m.addAttribute("readers", readers);
		return path;
	}
	
	@RequestMapping(value="/admin/readyUpdateUser.do")
	public String readyUpdateUser(String userID, Model m){
		Reader reader = this.readerService.findByUserID(userID);
		m.addAttribute("reader", reader);
		return "admin/updateUser";
	}
	
	@RequestMapping(value="/admin/updateUser.do")
	public String updateUser( String userID, boolean borrowstatus,int cbooknumber,int fbooknumber){
		String path="admin/check_success";
		Reader reader=this.readerService.findByUserID(userID);
		reader.setBorrowstatus(borrowstatus);
		reader.setCbooknumber(cbooknumber);
		reader.setFbooknumber(fbooknumber);
		reader.setBooknumber(reader.getBooknumber()-1);
		try {
			this.readerService.updateReader(reader);
		} catch (Exception e) {
			// TODO: handle exception
			path="admin/check_failure";
		}
		return path;
	}
	
	@RequestMapping(value="/admin/readyBorrow_showgo.do")
	public String readyBorrow_showgo(String userID, Model m){
		List<Borrow> borrows = this.borrowService.findByUserid(userID);
		m.addAttribute("borrows", borrows);
		return "admin/borrow_show";
	}
	
	@RequestMapping(value="/admin/readyUpdateBorrow.do")
	public String readyUpdateBorrow(String bookID, Model m){
		String path="admin/updateBorrow";
		Borrow borrow=this.borrowService.findByBookID(bookID);
		m.addAttribute("borrow", borrow);
		return path;
	}
	
	//修改借阅信息
	@RequestMapping(value="/admin/updateBorrow.do")
	public String updateBorrow(String userID, String bookID,String returntimes,boolean renewstatus,HttpSession s,boolean returnstatus) throws ParseException{
		String path="admin/check_success";
		Borrow b=(Borrow) s.getAttribute("b");
		Date borrowingtime=b.getBorrowingtime();
		Date time=b.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date returntime=sdf.parse(returntimes);
        System.out.println(returntime);
		Borrow borrow = new Borrow(bookID, userID, borrowingtime, returntime, renewstatus, returnstatus, time);
		try {
			this.borrowService.updateBorrow(borrow);
		} catch (Exception e) {
			// TODO: handle exception
			path="admin/check_failure";
		}
		return path;
	}
	
	@RequestMapping(value="/admin/readyTicket_showgo.do")
	public String readyTicket_showgo(String userID, Model m){
		String path="admin/ticket_show";
		List<Ticket> tickets=this.ticketService.findByUseid(userID);
		m.addAttribute("tickets", tickets);
		return path;
	}
	
	@RequestMapping(value="/admin/readyUpdateTicket.do")
	public String readyUpdateTicket(String fineID, Model m){
		Ticket ticket = this.ticketService.findById(fineID);
		m.addAttribute("ticket", ticket);
		return "admin/updateTicket";
	}
	
	@RequestMapping(value="/admin/updateTicket.do")
	public String updateTicket(String fineID, String finetypeID, String userID, double totalfineprice, String finedetails, boolean settlestatus){
		String path="admin/check_success";
		Ticket ticket = new Ticket(fineID, finetypeID, userID, totalfineprice, finedetails, settlestatus);
		this.ticketService.updateTicket(ticket);
		try {
			//this.ticketService.updateTicket(ticket);
		} catch (Exception e) {
			// TODO: handle exception
			path="admin/check_failure";
		}
		return path;
	}
	
	@RequestMapping(value="/admin/readyAddTicket.do")
	public String readyAddTicket(Model m){
		List<TicketType> ticketTypes = this.ticketTypeService.findall();
		m.addAttribute("ticketTypes", ticketTypes);
		return "admin/addTicket";
	}
	
	@RequestMapping(value="/admin/addTicket.do")
	public String addTicket(String fineID, String finetypeID, String userID, double totalfineprice, String finedetail, boolean settlestatus){
		String path="admin/check_success";
		Ticket ticket = new Ticket(fineID, finetypeID, userID, totalfineprice, finedetail, settlestatus);
		try {
			this.ticketService.addTicket(ticket);
		} catch (Exception e) {
			// TODO: handle exception
			path="admin/check_failure";
		}
		return path;
	}
	@RequestMapping(value="/admin/readyAddTicketAfterReturn.do")
	public String readyAddTicketAfterReturn(Model m,String userid,String bookID) throws ParseException{
		List<TicketType> ticketTypes = this.ticketTypeService.findall();
		m.addAttribute("ticketTypes", ticketTypes);
		
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
		m.addAttribute("totalfineprice",totalfineprice);
		m.addAttribute("userid",userid);
		return "admin/addTicket";
	}
}
