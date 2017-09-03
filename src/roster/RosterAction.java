package roster;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;                                                                                                                                                                                                                                                                                                                        
import org.json.JSONObject;

import admin.Admin;
import admin.AdminService;
import basis.Excel;
import basis.Hfsession;
import basis.ResultUtils;
import cw.Sbinfo;
import second.Second;
import second.SecondService;
import util.JDBCUtil;
import util.Page;
import util.StringHelp;

import com.opensymphony.xwork2.ActionSupport;

public class RosterAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final String Roster = null;
	private static final int BUFFER_SIZE = 16 * 1024;
	private File myFile1 ; 
	private File myFile2 ; 
    private List<File> myFile = new ArrayList<File>();    
    private List<String> contentType = new ArrayList<String>();  
    private List<String> fileName = new ArrayList<String>();    //文件名  
    private List<String> imageFileName = new ArrayList<String>();  
    private String caption;
	private Map result;
	private Roster roster;
	private String uuid;
	private String scz;
	private String keyword;
	private List<Roster> list = new ArrayList<>();
	private List piclist = new ArrayList<>();
	private List<Second> lists;
	private File excelPath;
	private Integer type;//详情 1；
	private Integer author;//详情 1；
	private String orderField;
	private String orderDirection;
	private Page page = new Page();
	private int currentPage=1;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String inrosters(){
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin != null) {
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	
	public String getRosterList (){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		lists = SecondService.getListSecond();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			//此处为查询数据库中的总条数，为分页做准备
			String hql = "select count(*) from Roster where del=1 ";
			if(admin.getAuthority().equals("5")){
				hql += "and scz ="+"'"+admin.getSczname().toString()+"'";
			}
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				hql += "and scz = :scz ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				query.setParameter("scz", scz);
			}
			Long count= (Long)query.uniqueResult();
			 
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			
			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=1 ";
			if(admin.getAuthority().equals("5")){
				sql += "and scz ="+"'"+admin.getSczname().toString()+"'";
			}
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				sql += "and scz = :scz ";
			}
			sql += "order by createDate asc";
			Query q1 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q1.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				q1.setParameter("scz", scz);
			}
			System.out.println("查询出数据库中的条数"+q1.list().size());
			q1.setFirstResult((currentPage-1)*20); 
			q1.setMaxResults(20);                                                                                                                                                                                   
			list = q1.list();
			page.setTotalPage((int)Math.ceil(count/20.0));// 
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<(int)(Math.ceil(count/20.0)));
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getOverAgeRosterList (){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		//Transaction tx = session.beginTransaction();
		lists = SecondService.getListSecond();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		
		try {
			//此处为查询数据库中的总条数，为分页做准备
			String hql = "select count(*) from Roster where del=1 ";
			if(admin.getAuthority().equals("5")){
				hql += "and scz ="+"'"+admin.getSczname().toString()+"'";
			}
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				hql += "and scz = :scz ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				query.setParameter("scz", scz);
			}
			Long count= (Long)query.uniqueResult();



			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=1 ";
			if(admin.getAuthority().equals("5")){
				sql += "and scz ="+"'"+admin.getSczname().toString()+"'";
			}
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				sql += "and scz = :scz ";
			}
			sql += "order by createDate desc";
			Query q1 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q1.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				q1.setParameter("scz", scz);
			}
			System.out.println("查询出数据库中的条数"+q1.list().size());
			q1.setFirstResult((currentPage-1)*20); 
			q1.setMaxResults(20);                                                                                                                                                                                   
			List<Roster> alllist = q1.list();
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.MONTH, 1);
			Date today = c.getTime();
			for(Roster r : alllist){
				String num =r.getIdentityId();
				if(num.length() == 18){
				int year = Integer.parseInt(num.substring(6, 10));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Date update = sdf.parse(String.valueOf(year + 65) + num.substring(10, 14));
				boolean bln = today.after(update);
				if(bln == false){
					System.out.println("未超龄");
				}else{
					System.out.println("超龄");
					list.add(r);
				}}
			}
			page.setTotalCount(list.size());
			page.setTotalPage((int)Math.ceil(count/20.0));
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<(int)(Math.ceil(count/20.0)));
			//tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			Hfsession.close();
		}
		return SUCCESS;
	}
	public String getLowAgeRosterList (){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		//Transaction tx = session.beginTransaction();
		lists = SecondService.getListSecond();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			//此处为查询数据库中的总条数，为分页做准备
			String hql = "select count(*) from Roster where del=1 ";
			if(admin.getAuthority().equals("5")){
				hql += "and scz ="+"'"+admin.getSczname().toString()+"'";
			}
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				hql += "and scz = :scz ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				query.setParameter("scz", scz);
			}
			Long count= (Long)query.uniqueResult();

			//page.setTotalCount(Integer.parseInt(String.valueOf(count)));

			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=1 ";
			if(admin.getAuthority().equals("5")){
				sql += "and scz ="+"'"+admin.getSczname().toString()+"'";
			}
			if(keyword != null && !keyword.equals("")){
				sql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				sql += "and scz = :scz ";
			}
			sql += "order by createDate desc";
			Query q1 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q1.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				q1.setParameter("scz", scz);
			}
			System.out.println("查询出数据库中的条数"+q1.list().size());
			q1.setFirstResult((currentPage-1)*20);
			q1.setMaxResults(20);
			List<Roster> alllist = q1.list();
			Date today = new Date();
			for(Roster r : alllist){
					String num = r.getIdentityId();
				if(num.length() == 18) {
					int year = Integer.parseInt(num.substring(6, 10));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					Date update = sdf.parse(String.valueOf(year + 18) + num.substring(10, 14));
					boolean bln = today.after(update);
					if (bln == false) {
						System.out.println("未超龄");

						list.add(r);
					} else {
						System.out.println("超龄");
						//list.add(r);
					}
				}


			}

			page.setTotalCount(list.size());
			//System.out.println("list====="+list);
			page.setTotalPage((int)Math.ceil(count/20.0));//
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<(int)(Math.ceil(count/20.0)));
			//tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getHtdqRosterList (){
		Session session = Hfsession.init();
		Map<String,Object> map = new HashMap<String,Object>();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			//此处为查询数据库中的总条数，为分页做准备
			String hql = "select count(*) from Roster where del=1 and DATEDIFF(htEnd, NOW())<=60 ";
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			Long count= (Long)query.uniqueResult();
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			
			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=1 and DATEDIFF(htEnd, NOW())<=60 ";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
				sql += "order by sbRemind desc";
		
			Query q2 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q2.setParameter("name", "%"+keyword+"%");
			}
			q2.setFirstResult((currentPage-1)*20); 
			q2.setMaxResults(20);                                                                                                                                                                                   
			list = q2.list();	
			page.setTotalPage((int)Math.ceil(count/20.0));// 
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<(int)(Math.ceil(count/20.0)));
		
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String exportHtdqRosterList (){
		Session session = Hfsession.init();
		Map<String,Object> map = new HashMap<String,Object>();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			//此处为查询数据库中的总条数，为分页做准备
			String hql = "select count(*) from Roster where del=1 and DATEDIFF(htEnd, NOW())<=60 ";
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			Long count= (Long)query.uniqueResult();
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));

			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=1 and DATEDIFF(htEnd, NOW())<=60 ";
			if(keyword != null && !keyword.equals("")){
				sql += "and name like :name ";
			}
			sql += "order by sbRemind desc";

			Query q2 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q2.setParameter("name", "%"+keyword+"%");
			}
			q2.setFirstResult((currentPage-1)*20);
			q2.setMaxResults(20);
			List<Object> listObject = new ArrayList<>();
			List<Object> listRoster = new ArrayList<>();
			listRoster = q2.list();

			String[] str = new String[]{"合同编号","姓名","姓别","身份证","银行卡号","社保编号","家庭地址","合同开始日期","合同结束日期","工种","电话号码","离厂日期","社保开始日期","社保结束日期","保险类型","工伤概况","工伤开始日期","工伤结束日期","员工来源","生产组","备注"};

			for(int i = 0;i<listRoster.size();i++){
				Roster r = (Roster) listRoster.get(i);
				RosterExp rosterExp = new RosterExp();
				rosterExp.setAddress(r.getAddress());
				rosterExp.setBankCard(r.getBankCard());
				rosterExp.setBeizhu(r.getBeizhu());
				rosterExp.setBxType(r.getBxType());
				rosterExp.setGsEnd(r.getGsEnd());
				rosterExp.setGsqk(r.getGsqk());
				rosterExp.setGsStart(r.getGsStart());
				rosterExp.setHtEnd(r.getHtEnd());
				rosterExp.setHtNumber(r.getHtNumber());
				rosterExp.setHtStart(r.getHtStart());
				rosterExp.setIdentityId(r.getIdentityId());
				rosterExp.setJobType(r.getJobType());
				rosterExp.setLcTime(r.getLcTime());
				rosterExp.setName(r.getName());
				rosterExp.setPhone(r.getPhone());
				rosterExp.setSbEnd(r.getSbEnd());
				rosterExp.setSbNumber(r.getSbNumber());
				rosterExp.setSbStart(r.getSbStart());
				rosterExp.setScz(r.getScz());
				rosterExp.setSex(r.getSex());
				rosterExp.setSource(r.getSource());
				listObject.add(rosterExp);
			}
			String title ="国通企业合同到期人员信息--"+sdf.format(new Date())+".xls";
			Excel.exportExcel(title, str, listObject);
			page.setTotalPage((int)Math.ceil(count/20.0));//
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<(int)(Math.ceil(count/20.0)));
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getTodayAddRosterList (){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date =sdf.format(new Date());
		lists = SecondService.getListSecond();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		Date endDate = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		cl.add(Calendar.DATE, -7);
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		String start = dd.format(startDate);
		String end = dd.format(endDate);

		try {
			//此处为查询数据库中的总条数，为分页做准备
			
			String hql = "select count(*) from Roster where del=1 and createDate>= ' " + start +"' and createDate <= '"+end+"'";
			
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				hql += "and scz = :scz ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				query.setParameter("scz", scz);
			}
			Long count= (Long)query.uniqueResult();
			 
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			
			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=1 and createDate>= ' " + start +"' and createDate <= '"+end+"'";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				sql += "and scz = :scz ";
			}
//			sql += "and createDate like :createDate";
			
			Query q1 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q1.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				q1.setParameter("scz", scz);
			}
//			q1.setParameter("createDate",date+"%");
			q1.setFirstResult((currentPage-1)*20); 
			q1.setMaxResults(20);                                                                                                                                                                                   
			list = q1.list();
			page.setTotalPage((int)Math.ceil(count/20.0));// 
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<(int)(Math.ceil(count/20.0)));		
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String getTodayDelRosterList (){
		
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		lists = SecondService.getListSecond();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		Date endDate = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		cl.add(Calendar.DATE, -7);
		Date startDate = cl.getTime();
		String start = sdf.format(startDate);
		String end = sdf.format(endDate);
		try {
			//此处为查询数据库中的总条数，为分页做准备

			String hql = "select count(*) from Roster where del=0 and createDate>= ' " + start +"' and createDate <= '"+end+"'";
			
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				hql += "and scz = :scz ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				query.setParameter("scz", scz);
			}
			Long count= (Long)query.uniqueResult();
			 
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			
			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=0 and createDate>= ' " + start +"' and createDate <= '"+end+"'";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				sql += "and scz = :scz ";
			}
			
			Query q1 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q1.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				q1.setParameter("scz", scz);
			}
			q1.setFirstResult((currentPage-1)*20); 
			q1.setMaxResults(20);                                                                                                                                                                                   
			list = q1.list();
			page.setTotalPage((int)Math.ceil(count/20.0));
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<(int)(Math.ceil(count/20.0)));		
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String editRoster(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		lists = SecondService.getListSecond();
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from Roster where uuid =:uuid").setParameter("uuid", uuid);
			roster = (Roster)q.uniqueResult();
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return admin.getAuthority()+"";
	}
	
	public String addRoster(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			roster.setDel("1");
			Date currentTime = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(roster.getUuid() == null || "".equals(roster.getUuid())){
				roster.setCreateDate(currentTime);
				roster.setCreateDate2(currentTime);
				session.save(roster);
				Sbinfo sbinfo =  new Sbinfo();
				sbinfo.setIdentityId(roster.getIdentityId());
				sbinfo.setName(roster.getName());
				sbinfo.setScz(roster.getScz());
				sbinfo.setUuid(0l);
				sbinfo.setCreateDate(roster.getCreateDate());
				if(roster.getSbStart()!=null && roster.getSbEnd()!=null ){
					String sbStart =sdf.format(roster.getSbStart()).toString();
					String sbEnd =sdf.format(roster.getSbEnd()).toString();
					String info = sbStart+"~"+sbEnd;
					sbinfo.setSbinfo(info);
					session.save(sbinfo);
				}
			}else{
				//修改之前做一次社保信息的记录录入

				Query q = session.createQuery("from Roster where uuid =:uuid").setParameter("uuid", roster.getUuid());
				Roster roster1 = (Roster)q.list().get(0);
				if(roster1 != null){
					Date sbStart = roster1.getSbStart();
					Date sbEnd = roster1.getSbEnd();
					String date1 ="";
					String date3 ="";
					String date2 ="";
					String date4 ="";
					if(sbStart!=null && !"".equals(sbStart) ){
						date1 =sdf.format(sbStart).toString();
					}
					if(sbEnd!=null && !"".equals(sbEnd)){
						date3 =sdf.format(sbEnd).toString();
					}
					if(roster.getSbStart()!=null && !"".equals(roster.getSbStart())){
						date2 =sdf.format(roster.getSbStart()).toString();
					}
					if(roster.getSbEnd()!=null && !"".equals(roster.getSbEnd())){
						date4 =sdf.format(roster.getSbEnd()).toString();
					}

					if(date1.equals(date2) && date3.equals(date4)){
						session.merge(roster);
					}else{
						//社保信息的记录通过身份证号来匹配
						String identityId = roster.getIdentityId();
						Sbinfo sbinfo =  new Sbinfo();
						//sbinfo.setIdentityId(identityId);
						Query qsb = session.createQuery("from Sbinfo where identityId =:identityId").setParameter("identityId",identityId );
						sbinfo=(Sbinfo) qsb.uniqueResult();
						String str =sbinfo.getSbinfo();
						str += "，"+date2+"~"+date4;
						sbinfo.setSbinfo(str);
						session.update(sbinfo);
						//记录一下
						session.merge(roster);
						//session.update(roster);


					}
				}

			}

			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String delRoster(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = session.createQuery("from Roster where uuid =:uuid").setParameter("uuid", uuid);
			roster = (Roster) q.uniqueResult();
			roster.setDel("0");
			roster.setCreateDate(sdf.format(new Date()));
			session.update(roster);
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String batchdelRoster(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		Map uuidarrs =request.getParameterMap();
		Object uuidarrObj = uuidarrs.get("uuidarr[]");
		String[] uuidarr =(String[])uuidarrObj;
		List uuidList = Arrays.asList(uuidarr);
		
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = session.createQuery("from Roster where uuid  in :uuidarr").setParameterList("uuidarr", uuidList);
			List<Roster> list   = q.list();
			for (Roster r:list
				 ) {
				session.delete(r);
			}
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
/*
 * 获取生产组列表，供新增选择
 */
	public String getSecondList(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			if(admin != null && admin.getAuthority().equals("5")){
				Integer secondId = admin.getSecond();
				Query q = session.createQuery("from Second where secondId=:secondId").setParameter("secondId", secondId);
				lists = q.list();
			}
			else if(admin != null && admin.getAuthority().equals("1")){
				Query q = session.createQuery("from Second where 1=1");
				lists = q.list();
			}
			
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return author+"";
	}
	public String addinkqRoster(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = session.createQuery("from Roster where uuid =:uuid").setParameter("uuid", uuid);
			roster = (Roster) q.uniqueResult();
			roster.setAddinkq("已添加");
			session.update(roster);
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;


	}
	
	public String inDialog(){
		return SUCCESS;
	}

	public String exportRoster(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		String sczname =request.getParameter("sczn");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Object> listObject = new ArrayList<>();
		List<Object> listRoster = new ArrayList<>();

		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String sql ="from Roster where del = '1'";
			if(sczname!=null && !"".equals(sczname)){
				sql+=" and scz =:scz";
			}
			Query q = session.createQuery(sql);
			if(sczname!=null && !"".equals(sczname)){
				q.setParameter("scz", sczname);
			}
			listRoster = q.list();

			String[] str = new String[]{"合同编号","姓名","姓别","身份证","银行卡号","社保编号","家庭地址","合同开始日期","合同结束日期","工种","电话号码","离厂日期","社保开始日期","社保结束日期","保险类型","工伤概况","工伤开始日期","工伤结束日期","员工来源","生产组","备注"};

			for(int i = 0;i<listRoster.size();i++){
				Roster r = (Roster) listRoster.get(i);
				RosterExp rosterExp = new RosterExp();
				rosterExp.setAddress(r.getAddress());
				rosterExp.setBankCard(r.getBankCard());
				rosterExp.setBeizhu(r.getBeizhu());
				rosterExp.setBxType(r.getBxType());
				rosterExp.setGsEnd(r.getGsEnd());
				rosterExp.setGsqk(r.getGsqk());
				rosterExp.setGsStart(r.getGsStart());
				rosterExp.setHtEnd(r.getHtEnd());
				rosterExp.setHtNumber(r.getHtNumber());
				rosterExp.setHtStart(r.getHtStart());
				rosterExp.setIdentityId(r.getIdentityId());
				rosterExp.setJobType(r.getJobType());
				rosterExp.setLcTime(r.getLcTime());
				rosterExp.setName(r.getName());
				rosterExp.setPhone(r.getPhone());
				rosterExp.setSbEnd(r.getSbEnd());
				rosterExp.setSbNumber(r.getSbNumber());
				rosterExp.setSbStart(r.getSbStart());
				rosterExp.setScz(r.getScz());
				rosterExp.setSex(r.getSex());
				rosterExp.setSource(r.getSource());
				listObject.add(rosterExp);
			}
			String title ="国通企业人事花名册--"+sdf.format(new Date())+".xls";
			if(sczname!=null && !"".equals(sczname)){
				title="国通企业生产部("+sczname+")人事花名册--"+sdf.format(new Date())+".xls";
			}
			Excel.exportExcel(title, str, listObject);
			tx.commit();
			Hfsession.close();
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String exportTodayaddRoster(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		String sczname =request.getParameter("sczn");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			sczname = new String(sczn.getBytes("iso8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		List<Object> listObject = new ArrayList<>();
		List<Object> listRoster = new ArrayList<>();
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		
		Date endDate = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		cl.add(Calendar.DATE, -7);
		Date startDate = cl.getTime();
		String start = sdf.format(startDate);
		String end = sdf.format(endDate);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=1 and createDate>= ' " + start +"' and createDate <= '"+end+"'";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			if(sczname!=null && !"".equals(sczname)){
				sql+=" and scz =:scz";
			}
			
			Query q1 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q1.setParameter("name", "%"+keyword+"%");
			}
	
			if(sczname!=null && !"".equals(sczname)){
				q1.setParameter("scz", sczname);
			}
			listRoster = q1.list();
			
			String[] str = new String[]{"合同编号","姓名","姓别","身份证","银行卡号","社保编号","家庭地址","合同开始日期","合同结束日期","工种","电话号码","离厂日期","社保开始日期","社保结束日期","保险类型","工伤概况","工伤开始日期","工伤结束日期","员工来源","生产组","备注"};

			for(int i = 0;i<listRoster.size();i++){
				Roster r = (Roster) listRoster.get(i);
				RosterExp rosterExp = new RosterExp();
				rosterExp.setAddress(r.getAddress());
				rosterExp.setBankCard(r.getBankCard());
				rosterExp.setBeizhu(r.getBeizhu());
				rosterExp.setBxType(r.getBxType());
				rosterExp.setGsEnd(r.getGsEnd());
				rosterExp.setGsqk(r.getGsqk());
				rosterExp.setGsStart(r.getGsStart());
				rosterExp.setHtEnd(r.getHtEnd());
				rosterExp.setHtNumber(r.getHtNumber());
				rosterExp.setHtStart(r.getHtStart());
				rosterExp.setIdentityId(r.getIdentityId());
				rosterExp.setJobType(r.getJobType());
				rosterExp.setLcTime(r.getLcTime());
				rosterExp.setName(r.getName());
				rosterExp.setPhone(r.getPhone());
				rosterExp.setSbEnd(r.getSbEnd());
				rosterExp.setSbNumber(r.getSbNumber());
				rosterExp.setSbStart(r.getSbStart());
				rosterExp.setScz(r.getScz());
				rosterExp.setSex(r.getSex());
				rosterExp.setSource(r.getSource());
				listObject.add(rosterExp);
			}
			String title ="国通企业人事本周新增花名册--"+sdf.format(new Date())+".xls";
			if(sczname!=null && !"".equals(sczname)){
				title="国通企业生产部("+sczname+")人事本周新增花名册--"+sdf.format(new Date())+".xls";
			}
			Excel.exportExcel(title, str, listObject);
			tx.commit();
			Hfsession.close();
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String exportTodaydelRoster(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		String sczname =request.getParameter("sczn");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Object> listObject = new ArrayList<>();
		List<Object> listRoster = new ArrayList<>();
		Session session = Hfsession.init();
		//Transaction tx = session.beginTransaction();
		
		Date endDate = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		cl.add(Calendar.DATE, -7);
		Date startDate = cl.getTime();
		String start = sdf.format(startDate);
		String end = sdf.format(endDate);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=0 and createDate>= ' " + start +"' and createDate <= '"+end+"'";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			if(sczname!=null && !"".equals(sczname)){
				sql+=" and scz =:scz";
			}
			
			Query q1 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q1.setParameter("name", "%"+keyword+"%");
			}
	
			if(sczname!=null && !"".equals(sczname)){
				q1.setParameter("scz", sczname);
			}
			listRoster = q1.list();
			
			String[] str = new String[]{"合同编号","姓名","姓别","身份证","银行卡号","社保编号","家庭地址","合同开始日期","合同结束日期","工种","电话号码","离厂日期","社保开始日期","社保结束日期","保险类型","工伤概况","工伤开始日期","工伤结束日期","员工来源","生产组","备注"};

			for(int i = 0;i<listRoster.size();i++){
				Roster r = (Roster) listRoster.get(i);
				RosterExp rosterExp = new RosterExp();
				rosterExp.setAddress(r.getAddress());
				rosterExp.setBankCard(r.getBankCard());
				rosterExp.setBeizhu(r.getBeizhu());
				rosterExp.setBxType(r.getBxType());
				rosterExp.setGsEnd(r.getGsEnd());
				rosterExp.setGsqk(r.getGsqk());
				rosterExp.setGsStart(r.getGsStart());
				rosterExp.setHtEnd(r.getHtEnd());
				rosterExp.setHtNumber(r.getHtNumber());
				rosterExp.setHtStart(r.getHtStart());
				rosterExp.setIdentityId(r.getIdentityId());
				rosterExp.setJobType(r.getJobType());
				rosterExp.setLcTime(r.getLcTime());
				rosterExp.setName(r.getName());
				rosterExp.setPhone(r.getPhone());
				rosterExp.setSbEnd(r.getSbEnd());
				rosterExp.setSbNumber(r.getSbNumber());
				rosterExp.setSbStart(r.getSbStart());
				rosterExp.setScz(r.getScz());
				rosterExp.setSex(r.getSex());
				rosterExp.setSource(r.getSource());
				listObject.add(rosterExp);
			}
			String title ="国通企业人事本周离职花名册--"+sdf.format(new Date())+".xls";
			if(sczname!=null && !"".equals(sczname)){
				title="国通企业生产部("+sczname+")人事本周离职花名册--"+sdf.format(new Date())+".xls";
			}
			Excel.exportExcel(title, str, listObject);
		//	tx.commit();
			Hfsession.close();
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	//导入功能模块
	public String importExcel() throws IOException, SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Session session = Hfsession.init();
			Transaction tx = session.beginTransaction();
			InputStream fis = new FileInputStream(excelPath);
			list = new ArrayList<>();
			list = RosterService.importExcel(fis);
			//导入添加人员
			for(int i =0; i<list.size();i++) {
				//如果身份证不为空给加
				//System.out.println("================================================================"+info.getIdentityId());
				if (list.get(i).getIdentityId()!=null && !"".equals(list.get(i).getIdentityId())) {
					session.save(list.get(i));
				}else{
					map.put("msg", "error");
					map.put("statusCode", 500);
					map.put("error",i);
					ResultUtils.toJson(ServletActionContext.getResponse(), map);
					break;
				}
	            
	        }
			tx.commit();
			JDBCUtil jbutil =new JDBCUtil();
			Connection con =jbutil.getConnection();
			String sql1 ="DELETE FROM  gt_roster "+
				"WHERE identityId  IN "+
					"(SELECT a.identityId FROM (SELECT  *  FROM gt_roster  GROUP  BY  identityId   HAVING  COUNT(identityId) > 1) a)"+ 
				"AND createdate2 NOT IN"+ 
					"(SELECT b.* FROM (SELECT MAX(createdate2) FROM  gt_roster  GROUP BY identityId  HAVING COUNT(identityId)>1) b)";
			PreparedStatement psta1 =con.prepareStatement(sql1);
			psta1.execute(sql1);
			jbutil.close();		
			map.put("msg", "success");
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			map.put("msg", "error");
			map.put("statusCode", 100);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		}finally{
			Hfsession.close();
		}
		return NONE;
	}

	public String getDepartureList () {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String, Object> map = new HashMap<String, Object>();
		lists = SecondService.getListSecond();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			//此处为查询数据库中的总条数，为分页做准备
			String hql = "select count(*) from Roster where del=0 ";
			if (admin.getAuthority().equals("5")) {
				hql += "and scz =" + "'" + admin.getSczname().toString() + "'";
			}
			if (keyword != null && !keyword.equals("")) {
				hql += "and name like :name ";
			}
			if (StringHelp.isNotEmpty(scz)) {
				hql += "and scz = :scz ";
			}
			Query query = session.createQuery(hql);
			if (keyword != null && !keyword.equals("")) {
				query.setParameter("name", "%" + keyword + "%");
			}
			if (StringHelp.isNotEmpty(scz)) {
				query.setParameter("scz", scz);
			}
			Long count = (Long) query.uniqueResult();

			page.setTotalCount(Integer.parseInt(String.valueOf(count)));

			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=0 ";
			if (admin.getAuthority().equals("5")) {
				sql += "and scz =" + "'" + admin.getSczname().toString() + "'";
			}
			if (keyword != null && !keyword.equals("")) {
				sql += "and name like :name ";
			}
			if (StringHelp.isNotEmpty(scz)) {
				sql += "and scz = :scz ";
			}
			sql += "order by createDate desc";
			Query q1 = session.createQuery(sql);
			if (keyword != null && !keyword.equals("")) {
				q1.setParameter("name", "%" + keyword + "%");
			}
			if (StringHelp.isNotEmpty(scz)) {
				q1.setParameter("scz", scz);
			}
			System.out.println("查询出数据库中的条数" + q1.list().size());
			q1.setFirstResult((currentPage - 1) * 20);
			q1.setMaxResults(20);
			list = q1.list();
			page.setTotalPage((int) Math.ceil(count / 20.0));//
			page.setHasPrePage(currentPage > 1);
			page.setHasNextPage(currentPage < (int) (Math.ceil(count / 20.0)));
//			map.put("page", page);
//			if(list.size()>0){
//				map.put("statusCode", 200);
//				map.put("list",list);
//				ResultUtils.toJson(ServletActionContext.getResponse(), map);
//			}else{
//				map.put("statusCode", 300);
//				ResultUtils.toJson(ServletActionContext.getResponse(), map);
//			}
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

		/**
         * 获取符合代缴社保预警的人员集合
         * @param date1
         * @param date2
         * @return
         */
    public  String getYujingList(){ //代缴社保预期 date1==当前日期，date2==社保结束日期 

    	Session session = Hfsession.init();
		//Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		//List<Roster> list = new ArrayList<Roster>();
		//Page page = new Page();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			//此处为查询数据库中的总条数，为分页做准备
			String hql = "select count(*) from Roster where del=1 and sbRemind>0 and sbRemind<30";
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			Long count= (Long)query.uniqueResult();
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			
			//此处才是作为查询数据中的数据
			String sql = "from Roster where del=1 and sbRemind>0 and sbRemind<30 ";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
				sql += "order by sbRemind desc";
		
			Query q2 = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q2.setParameter("name", "%"+keyword+"%");
			}
			q2.setFirstResult((currentPage-1)*20); 
			q2.setMaxResults(20);                                                                                                                                                                                   
			list = q2.list();	
			//System.out.println("q集合的大小====="+list.size());
			page.setTotalPage((int)Math.ceil(count/20.0));// 
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<(int)(Math.ceil(count/20.0)));
			/*map.put("page", page); 
			if(list.size()>0){
				map.put("statusCode", 200);
				map.put("list",list);
				ResultUtils.toJson(ServletActionContext.getResponse(), map);
			}else{
				map.put("statusCode", 300);
				ResultUtils.toJson(ServletActionContext.getResponse(), map);
			}*/
			//tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
   
    private static void copy(File src, File dst) {  
        try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(src),  
                        BUFFER_SIZE);  
                out = new BufferedOutputStream(new FileOutputStream(dst),  
                        BUFFER_SIZE);  
                byte[] buffer = new byte[BUFFER_SIZE];  
                while (in.read(buffer) > 0) {  
                    out.write(buffer);  
                }  
            } finally {  
                if (null != in) {  
                    in.close();  
                }  
                if (null != out) {  
                    out.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
    private static String getExtention(String fileName) {  
        int pos = fileName.lastIndexOf(".");  
        return fileName.substring(pos);  
    }  
  
    public String fileUpload() {  
    	System.out.println("==================这里是upload=============================");
    	System.out.println("==================这里是uploaduuid============================="+uuid);
    	/*if(myFile1!=null){
    		
    		myFile.add(myFile1);
    	}
    	if(myFile2!=null){
    		
    		myFile.add(myFile2);
    	}*/
    	System.out.println("==================myFile============================="+myFile1);
    	System.out.println("==================myFile============================="+myFile2);
    	Session session = Hfsession.init();
    	Transaction tx = session.beginTransaction();
    	Map<String,Object> map = new HashMap<String,Object>();
    	if (myFile == null) { 
            return INPUT;  
        }
        String str ="";
       // System.out.println("========================myfilesize==========================="+myFile.size());
        try {
	        for (int i = 0; i < myFile.size(); i++) {  
	        	System.out.println("========================myfile==========================="+myFile.get(i));
	            imageFileName.add(new Date().getTime()+ getExtention(this.getMyFileFileName().get(i))) ;  
	           
	            File imageFile = new File(ServletActionContext.getServletContext()  //得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)  
	      
	                    .getRealPath("UploadImages")     
	                    + "/" + imageFileName.get(i));  
	            if(!imageFile.getParentFile().exists()){     //如果Images文件夹不存在  
	            	imageFile.getParentFile().mkdirs();      //则创建新的多级文件夹  
	                  
	            }  
	            System.out.println("===========imageFile==================="+imageFile);
	            copy(myFile.get(i), imageFile);  //把图片写入到上面设置的路径里  
	            str+="UploadImages/"+imageFileName.get(i)+",";
	            
	        } 
	        System.out.println("=================str========================="+str);
	        Query q = session.createQuery("from Roster where uuid =:uuid").setParameter("uuid", uuid);
	        Roster ro = (Roster) q.uniqueResult();
	        ro.setImage(str);
	      
	        System.out.println("===================roster=============="+ro);
	        session.update(ro);
	        map.put("statusCode", 200);
	        ResultUtils.toJson(ServletActionContext.getResponse(), map);
	        tx.commit();
	        Hfsession.close();
		} catch (IOException e) {
			e.printStackTrace();
			return ERROR;
		}
        return SUCCESS;  
    }  
    
    public String picView(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from Roster where uuid =:uuid").setParameter("uuid", uuid);
			Roster roster = (Roster) q.uniqueResult();
			String str = roster.getImage();

		    String[] arrayStr =new String[]{};

		    arrayStr = str.split(",");
		    piclist= Arrays.asList(arrayStr);
			
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
    
    public String picUpload(){
		uuid=uuid;
		return SUCCESS;
    }

	public String editDeal(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		String deal = request.getParameter("deal");
		String uuid = request.getParameter("uuid");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = null;
			if(uuid != null){
				q = session.createQuery("from Roster where uuid =:uuid").setParameter("uuid", uuid);
				Roster dealroster = (Roster)q.uniqueResult();
				dealroster.setDeal(deal);
				session.update(dealroster);
				map.put("statusCode", 200);
				ResultUtils.toJson(ServletActionContext.getResponse(), map);
			}
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
    
    public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	public List<Roster> getList() {
		return list;
	}

	public void setList(List<Roster> list) {
		this.list = list;
	}

	public File getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(File excelPath) {
		this.excelPath = excelPath;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Roster getRoster() {
		return roster;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	
	public String getScz() {
		return scz;
	}

	public void setScz(String scz) {
		this.scz = scz;
	}
	public List<Second> getLists() {
		return lists;
	}

	public void setLists(List<Second> lists) {
		this.lists = lists;
	}

	public Integer getAuthor() {
		return author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}
	public List<File> getMyFile() {  
        return myFile;  
    }  
  
    public void setMyFile(List<File> myFile) {  
        this.myFile = myFile;  
    }  
  
    public List<String> getContentType() {  
        return contentType;  
    }  
  
    public void setContentType(List<String> contentType) {  
        this.contentType = contentType;  
    }  
  
  
    public List<String> getMyFileFileName() {  
        return fileName;  
    }  
  
    public void setMyFileFileName(List<String> fileName) {  
        this.fileName = fileName;  
    }  
  
  
    public List<String> getImageFileName() {  
        return imageFileName;  
    }  
  
    public void setImageFileName(List<String> imageFileName) {  
        this.imageFileName = imageFileName;  
    }  
  
    public String getCaption() {  
        return caption;  
    }  
  
    public void setCaption(String caption) {  
        this.caption = caption;  
    }  
  
    public static int getBufferSize() {  
        return BUFFER_SIZE;  
    }

	public List getPiclist() {
		return piclist;
	}

	public void setPiclist(List piclist) {
		this.piclist = piclist;
	}

	public File getMyFile1() {
		return myFile1;
	}

	public void setMyFile1(File myFile1) {
		this.myFile1 = myFile1;
	}

	public File getMyFile2() {
		return myFile2;
	}

	public void setMyFile2(File myFile2) {
		this.myFile2 = myFile2;
	}  
	
}
