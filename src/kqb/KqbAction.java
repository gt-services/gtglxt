package kqb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;

import basis.Excel;
import basis.Hfsession;
import basis.ResultUtils;
import kqb.Kqb;
import kqb.Kqbinfo;
import roster.Roster;
import second.Second;
import kqb.KqbService;
import second.SecondService;
import util.BeanRefUtil;
import util.JDBCUtil;
import util.Page;
import util.StringHelp;

import com.opensymphony.xwork2.ActionSupport;

import admin.Admin;
import admin.AdminService;

/**
 * 考勤表
 * @author Administrator
 *
 */
public class KqbAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Map result;
	private Integer type;//1，新增；2编辑；3考勤
	private String uuid;
	private String keyword;
	private String name;
	private String gw;
	private String banci;
	private String scz;
	private String sczid;
	private String year;
	private String month;
	private String day;
	private Kqb kqb;
	private Todaykq todaykq;
	private Kqbinfo kqbinfo;
	private File excelPath;
	private List<Kqb> list;
	private List<Kqbinfo> listss;
	private List<Second> lists;
	private List<Second> lista;
	private List<Job> joblist;
	private List<Size> sizelist;
	private Page page = new Page();
	private int currentPage=1;		
	private String status;
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public String getKqbList(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		status = admin.getStatus();
		if(admin == null){
			return SUCCESS;
		}
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		page.setEveryPage(30);
		page.setCurrentPage(1);
		
		Calendar now = Calendar.getInstance();  
        String year1 = now.get(Calendar.YEAR)+"";
        String month1 = (now.get(Calendar.MONTH) + 1)+"";
        String day1 = (now.get(Calendar.DATE))+"";
    	
		try {
			String hql = "select count(*) from Kqb where 1=1 ";
			if(admin.getAuthority().equals("5")){
				hql += "and scz ="+ admin.getSecond();
			}else{
				lists = SecondService.getListSecond();
			}
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				hql += "and scz = :scz ";
			}
			if(StringHelp.isNotEmpty(year)){
				hql += "and year = :year ";
			}else{
				hql += "and year =" +year1;
			}
			if(StringHelp.isNotEmpty(month)){
				hql += "and month = :month ";
			}else{
				hql += "and month =" +month1;
			}
			//如果该角色不是超级管理员则控制显示的人员考勤  权限：1. 超级管理员 ,2.人事，3.财务，4培训，5生产。
			Query query = session.createQuery(hql);
			if(StringHelp.isNotEmpty(day)){
				day=day;
			}else{
				day=day1;
			}
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				query.setParameter("scz", scz);
			}
			if(StringHelp.isNotEmpty(year)){
				query.setParameter("year", year);
			}
			if(StringHelp.isNotEmpty(month)){
				query.setParameter("month", month);
			}
			
			Long countA= (Long)query.uniqueResult();
			Integer count =countA.intValue();
			
			page.setTotalCount(count);
			
			String sql = "from Kqb where 1=1 ";
			if(keyword != null && !keyword.equals("")){
				sql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(scz)){
				sql += "and scz = :scz ";
			}
			if(StringHelp.isNotEmpty(year)){
				sql += "and year = :year ";
			}else{
				sql += "and year =" +year1;
			}
			if(StringHelp.isNotEmpty(month)){
				sql += "and month = :month ";
			}else{
				sql += "and month =" +month1;
			}
			if("5".equals(admin.getAuthority())){//生产管理员登录
				sql+= "and scz="+admin.getSecond();
			}

			sql += "ORDER BY name DESC ";
			Query q = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q.setParameter("name", "%"+keyword+"%");
			}
			if(StringHelp.isNotEmpty(scz)){
				q.setParameter("scz", scz);
			}
			if(StringHelp.isNotEmpty(year)){
				q.setParameter("year", year);
			}else{
				year = year1;
			}
			if(StringHelp.isNotEmpty(month)){
				q.setParameter("month", month);
			}else{
				month = month1;
			}
			q.setFirstResult((currentPage-1)*30);
			q.setMaxResults(30);
			list = q.list();//获取到符合条件的集合
			
			listss= new ArrayList<>();
			SecondService second = new SecondService();
			Map<String,String> secondMap =second.getSecondMap() ;
			
			if(list.size()>0){
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(int i=0;i<list.size();i++){
					Kqb kqb =list.get(i);
					kqbinfo = new Kqbinfo();
					kqbinfo.setUuid(kqb.getUuid());
					kqbinfo.setName(kqb.getName());
					kqbinfo.setGw(kqb.getGw());
					kqbinfo.setSczid(kqb.getScz());
					
					String sczname = secondMap.get(kqb.getScz()); 
					
					kqbinfo.setScz(sczname);
					switch (day){ 
						case 1+"" : kqbinfo.setTodaykq(kqb.getDay1()); break; 
						case 2+""  :kqbinfo.setTodaykq(kqb.getDay2()); break;
						case 3+""  : kqbinfo.setTodaykq(kqb.getDay3()); break;
						case 4+"" : kqbinfo.setTodaykq(kqb.getDay4()); break;
						case 5+""  :kqbinfo.setTodaykq(kqb.getDay5()); break;
						case 6+""  : kqbinfo.setTodaykq(kqb.getDay6()); break;
						case 7+""  : kqbinfo.setTodaykq(kqb.getDay7()); break;
						case 8+""  : kqbinfo.setTodaykq(kqb.getDay8()); break;
						case 9+""  :kqbinfo.setTodaykq(kqb.getDay9()); break;
						case 10+""  :kqbinfo.setTodaykq(kqb.getDay10()); break;
						case 11+""  :kqbinfo.setTodaykq(kqb.getDay11()); break;
						case 12+""  :kqbinfo.setTodaykq(kqb.getDay12()); break;
						case 13+""  :kqbinfo.setTodaykq(kqb.getDay13()); break;
						case 14+""  :kqbinfo.setTodaykq(kqb.getDay14()); break; 
						case 15+""  :kqbinfo.setTodaykq(kqb.getDay15()); break;
						case 16+""  :kqbinfo.setTodaykq(kqb.getDay16()); break;
						case 17+""  : kqbinfo.setTodaykq(kqb.getDay17()); break;
						case 18+""  : kqbinfo.setTodaykq(kqb.getDay18()); break;
						case 19+""  : kqbinfo.setTodaykq(kqb.getDay19()); break;
						case 20 +"" : kqbinfo.setTodaykq(kqb.getDay20()); break;
						case 21+""  : kqbinfo.setTodaykq(kqb.getDay21()); break;
						case 22+""  : kqbinfo.setTodaykq(kqb.getDay22()); break;
						case 23+"" : kqbinfo.setTodaykq(kqb.getDay23()); break;
						case 24+""  : kqbinfo.setTodaykq(kqb.getDay24()); break;
						case 25 +"" : kqbinfo.setTodaykq(kqb.getDay25()); break;
						case 26+""  : kqbinfo.setTodaykq(kqb.getDay26()); break;
						case 27 +"" : kqbinfo.setTodaykq(kqb.getDay27()); break;
						case 28+"" : kqbinfo.setTodaykq(kqb.getDay28()); break;
						case 29+""  : kqbinfo.setTodaykq(kqb.getDay29()); break;
						case 30+""  : kqbinfo.setTodaykq(kqb.getDay30()); break;
						case 31+""  : kqbinfo.setTodaykq(kqb.getDay31()); break;
						default: return ERROR;
					} 
					
					listss.add(i, kqbinfo);
				}
			//	System.out.println("=============secondMap=================="+secondMap);
		
			}
			page.setTotalCount(count);
			page.setTotalPage(count/30);
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<count/30);
			//tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	public String addKqb(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		Kqb k1 = new Kqb();
		try {
			k1.setName(name);k1.setGw(gw);k1.setYear(year);k1.setMonth(month);
			if(admin.getAuthority().equals("1")){
				k1.setScz(scz);
			}else{
				k1.setScz(admin.getSecond()+"");
			}
			session.save(k1);
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
	//添加到导出表格中的数据
	public void addKqbExport(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		scz= request.getParameter("scz");
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		Calendar now = Calendar.getInstance(); 
		Integer cmonth =Integer.parseInt(month);
		Integer cday =Integer.parseInt(day);
		int day1 = now.get(Calendar.DATE);
		int year1 = now.get(Calendar.YEAR);
		int sczid =Integer.parseInt(request.getParameter("sczid"));

		try {
			//从前台传过来的数组，一个人的数据
			String[] job =request.getParameterValues("jobid");
			String[] hour =request.getParameterValues("hours");
			String[] size =request.getParameterValues("sizeid");
			String[] numbers =request.getParameterValues("number");
			//查询是否存在此人本月中的是否存在此job或size的考勤信息 无-添加 有-修改

			//先查出来这个人这个月份在数据库中的记录条数
			Query q = session.createQuery("from KqbExport where uuid =:uuid and month =:month and year =:year");
			q.setParameter("uuid",uuid );
			q.setParameter("month",month );
			q.setParameter("year",year1+"" );
			List<KqbExport> kqbExportList =q.list();
			//新建一个数据库已存在joborsize的集合
			List<Integer> jobExist = new ArrayList<>();
			List<Integer> sizeExist = new ArrayList<>();
			if(kqbExportList!=null && kqbExportList.size()>0){
				for(KqbExport  kqbExp : kqbExportList){
					/**/
					if(kqbExp.getJid()!= null && !"".equals(kqbExp.getJid())){
						jobExist.add(kqbExp.getJid());
					}
					if(kqbExp.getSid()!= null && !"".equals(kqbExp.getSid())){
						sizeExist.add(kqbExp.getSid());
					}
				}
			}
			//前台传过来的jid与数据库中存在的jid做对比
			if(job!=null && job.length>0){
				for (int i = 0;i<job.length;i++) {
					String jidstr = job[i];
					Integer jidint = Integer.parseInt(jidstr);
					//如果数据库存在 -- 修改
					if(jobExist.contains(jidint)){
						Query q2 = session.createQuery("from KqbExport where uuid =:uuid and month =:month and year =:year and jid =:jid");
						q2.setParameter("uuid",uuid );
						q2.setParameter("month",month );
						q2.setParameter("year",year1+"" );
						q2.setParameter("jid",jidint);
						List<KqbExport> kqbExport =q2.list();
						KqbExport export = kqbExport.get(0);
						Map<String,String> m = new HashMap<String, String>();
						//动态添加属性字段-----使用工具类
						m.put("day"+cday,hour[i]);
						BeanRefUtil.setFieldValue(export, m);
						session.update(export);
					} else {
						KqbExport export = new KqbExport();
						Query q3 = session.createQuery("from Kqb where uuid =:uuid").setParameter("uuid",uuid);
						Kqb kqb =(Kqb) q3.uniqueResult();
						Query q7 = session.createQuery("from Roster where uuid =:uuid").setParameter("uuid",uuid);
						Roster roster=(Roster) q7.uniqueResult();
						List<Job> jobl =JobService.getListJob(sczid);
						JSONObject jsonObject = new JSONObject();
						for(int l =0;l<jobl.size();l++){
							String secondid =jobl.get(l).getSecondId()+"";
							String name =jobl.get(l).getName();
							jsonObject.put(secondid, name);

						}
						/*将规格放入json对象中*/
						List<Size> sizel =SizeService.getListSize(sczid);
						JSONObject sizejsonObject = new JSONObject();
						for(int l =0;l<sizel.size();l++){
							String sizeid =sizel.get(l).getSizeid()+"";
							String name =sizel.get(l).getName();
							sizejsonObject.put(sizeid, name);

						}
						Map<String,String> m = new HashMap<String, String>();
						//动态添加属性字段-----使用工具类
						m.put("day"+cday,hour[i]);
						BeanRefUtil.setFieldValue(export, m);

						Map<String,String> secondMap = SecondService.getSecondMap();
						String sczname = secondMap.get(sczid+"");
						//1.uuid
						export.setUuid(uuid);
						//2.username
						export.setName(kqb.getName());
						//新增的银行卡
						export.setBankCard(roster.getBankCard());
						//3.生产组
						export.setScz(sczname);
						//4jobid
						export.setJid(jidint);
						//job名称
						export.setJobOrSizeName(jsonObject.getString(jidstr));
						//年
						export.setYear(year1+"");
						//月
						export.setMonth(month);
						export.setCreateDate(new Date());
						session.save(export);
					}
				}
			}

			if(size!=null && size.length>0){
				for (int i = 0;i<size.length;i++) {
					String sidstr = size[i];
					Integer sidint = Integer.parseInt(sidstr);
					//如果数据库存在 -- 修改
					if(sizeExist.contains(sidint)){
						Query q4 = session.createQuery("from KqbExport where uuid =:uuid and month =:month and year =:year and sid =:sid");
						q4.setParameter("uuid",uuid );
						q4.setParameter("month",month );
						q4.setParameter("year",year1+"" );
						q4.setParameter("sid",sidint);
						List<KqbExport> kqbExport =q4.list();
						KqbExport export = kqbExport.get(0);
						Map<String,String> m = new HashMap<String, String>();
						//动态添加属性字段-----使用工具类
						m.put("day"+cday,numbers[i]);
						BeanRefUtil.setFieldValue(export, m);
						session.update(export);
					} else {
						KqbExport export = new KqbExport();
						Query q5 = session.createQuery("from Kqb where uuid =:uuid").setParameter("uuid",uuid);
						Kqb kqb =(Kqb) q5.uniqueResult();
						Query q6 = session.createQuery("from Roster where uuid =:uuid").setParameter("uuid",uuid);
						Roster roster=(Roster) q6.uniqueResult();
						List<Job> jobl =JobService.getListJob(sczid);
						JSONObject jsonObject = new JSONObject();
						for(int l =0;l<jobl.size();l++){
							String secondid =jobl.get(l).getSecondId()+"";
							String name =jobl.get(l).getName();
							jsonObject.put(secondid, name);

						}
						/*将规格放入json对象中*/
						List<Size> sizel =SizeService.getListSize(sczid);
						JSONObject sizejsonObject = new JSONObject();
						for(int l =0;l<sizel.size();l++){
							String sizeid =sizel.get(l).getSizeid()+"";
							String name =sizel.get(l).getName();
							sizejsonObject.put(sizeid, name);

						}
						Map<String,String> m = new HashMap<String, String>();
						//动态添加属性字段-----使用工具类
						m.put("day"+cday,numbers[i]);
						BeanRefUtil.setFieldValue(export, m);

						Map<String,String> secondMap = SecondService.getSecondMap();
						String sczname = secondMap.get(sczid+"");
						//1.uuid
						export.setUuid(uuid);
						//2.username
						export.setName(kqb.getName());
						//新增的银行卡
						export.setBankCard(roster.getBankCard());
						//3.生产组
						export.setScz(sczname);
						//4jobid
						export.setSid(sidint);
						//job名称
						export.setJobOrSizeName(sizejsonObject.getString(sidstr));
						//年
						export.setYear(year1+"");
						//月
						export.setMonth(month);
						export.setCreateDate(new Date());
						session.save(export);
					}
				}
			}

			tx.commit();
			Hfsession.close();
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (Exception e) {
			e.printStackTrace();
			//return ERROR;
		}
		//return SUCCESS;
	}
	//添加到详表的考勤数据
	public String addTodayKqb(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		scz= request.getParameter("scz");
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		Calendar now = Calendar.getInstance();
		Integer cmonth =Integer.parseInt(month);
		Integer cday =Integer.parseInt(day);
		int day1 = now.get(Calendar.DATE);
		try {
			//从前台传过来的数组，一个人的数据
			String[] job =request.getParameterValues("jobid");
			String[] hour =request.getParameterValues("hours");
			String[] size =request.getParameterValues("sizeid");
			String[] numbers =request.getParameterValues("number");
			//查询是否存在此人当天的考勤信息
			Query q = session.createQuery("from Todaykq where userid =:userid and month =:month and day =:day");
			q.setParameter("userid",uuid );
			q.setParameter("month",cmonth );
			q.setParameter("day",cday );
			List<Todaykq> dellist =q.list();
			AdminService ads = new AdminService();

			if(dellist!=null && dellist.size() > 0){//修改此条信息
				/*	//Integer hourcount =t.getHours()+hours; 此处原统计用
						t.setHours(hours);
						t.setMonth(cmonth);
						t.setDay(cday);
						session.update(t);
				 */
				//如果有数据库中删除此对象
				for(Todaykq t : dellist){
					session.delete(t);
				}
			}
			if(job!=null){
				for(int i=0;i<job.length;i++){
					Integer jobid=Integer.parseInt(job[i]);
					Integer hours=Integer.parseInt(hour[i]);
					String sczname =ads.getSecondName(Integer.parseInt(sczid));

						todaykq= new Todaykq();
						todaykq.setSczname(sczname);
						todaykq.setJobid(jobid);
						todaykq.setHours(hours);
						todaykq.setUserid(uuid);
						todaykq.setMonth(cmonth);
						todaykq.setDay(cday);
						todaykq.setCreateDate(sdf.format(new Date()));
						session.save(todaykq);

				}
			}

			if(size!=null){
				for(int j=0;j<size.length;j++){
					Integer sizeid=Integer.parseInt(size[j]);
					Integer number=Integer.parseInt(numbers[j]);
					String sczname =ads.getSecondName(Integer.parseInt(sczid));

						todaykq= new Todaykq();
						todaykq.setSczname(sczname);
						todaykq.setSizeid(sizeid);
						todaykq.setNumber(number);
						todaykq.setUserid(uuid);
						todaykq.setMonth(cmonth);
						todaykq.setDay(cday);
						todaykq.setCreateDate(sdf.format(new Date()));
						session.save(todaykq);


				}
			}


			tx.commit();
			Hfsession.close();
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	public String batchaddintoKqb(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map requestArr =request.getParameterMap();
		Object uuidarrObj = requestArr.get("uuidArrs[]");
		String[] uuidarr =(String[])uuidarrObj;
		List uuidList = Arrays.asList(uuidarr);
		Object sczarrObj = requestArr.get("sczArrs[]");
		String[] sczarr =(String[])sczarrObj;
		List sczList = Arrays.asList(sczarr);
		Object namearrObj = requestArr.get("nameArrs[]");
		String[] namearr =(String[])namearrObj;
		List nameList = Arrays.asList(namearr);
		Calendar now = Calendar.getInstance();  
        String year1 = now.get(Calendar.YEAR)+"";
        String month1 = (now.get(Calendar.MONTH) + 1)+"";
        year=year1;
        month=month1;
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> sczMap = SecondService.getSecondNameToIdMap();
		List<Kqb> list = new ArrayList<>();
		try {
			for(int i = 0 ; i < uuidList.size() ; i++){
				Kqb k1 = new Kqb();
				k1.setUuid(uuidarr[i]);
				k1.setName(namearr[i]);
				k1.setScz(sczMap.get(sczarr[i]));
				k1.setYear(year);
				k1.setMonth(month);
				session.save(k1);
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

	public String addintoKqb(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Calendar now = Calendar.getInstance();
		String year1 = now.get(Calendar.YEAR)+"";
		String month1 = (now.get(Calendar.MONTH) + 1)+"";
		year=year1;
		month=month1;
		Map<String,Object> map = new HashMap<String,Object>();
		Kqb k1 = new Kqb();
		try {
			Query q = session.createQuery("from Second where name =:name").setParameter("name", scz);
			lista = q.list();
			String scz = lista.get(0).getSecondId().toString();
			k1.setUuid(uuid);
			k1.setName(name);
			k1.setScz(scz);
			k1.setYear(year);
			k1.setMonth(month);
			session.save(k1);
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
	public String editKqb(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		sczid= request.getParameter("sczidd");
		month= request.getParameter("month");
		day= request.getParameter("day");
		if(sczid!=null){			
			Integer sczidd = Integer.parseInt(sczid);
			joblist=JobService.getListJob(sczidd);
			sizelist = SizeService.getListSize(sczidd);
		}
		lists = SecondService.getListSecond();
	
		if(!StringHelp.isNotEmpty(uuid)){
			return "1";
		}
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from Kqb where uuid =:uuid").setParameter("uuid", uuid);
			kqb = (Kqb)q.uniqueResult();
			
			tx.commit();
			Hfsession.close();
			if(kqb == null){
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "2";
	}
	
	
	//	添加考勤信息到kqb---------------此处有放开权限操作----加入原始统计考勤总表
	public String updateKqb(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		int sczid =Integer.parseInt(request.getParameter("sczid"));
		Map<String,Object> map = new HashMap<String,Object>();
		Integer cmonth =Integer.parseInt(month);
		Integer cday =Integer.parseInt(day);
		try {
			/*将所有的job放入json对象中*/
			List<Job> jobl =JobService.getListJob(sczid);			
			JSONObject jsonObject = new JSONObject();
			for(int l =0;l<jobl.size();l++){
				String secondid =jobl.get(l).getSecondId()+"";
				String name =jobl.get(l).getName();				
				jsonObject.put(secondid, name);
			
			}
			/*将规格放入json对象中*/
			List<Size> sizel =SizeService.getListSize(sczid);			
			JSONObject sizejsonObject = new JSONObject();
			for(int l =0;l<sizel.size();l++){
				String sizeid =sizel.get(l).getSizeid()+"";
				String name =sizel.get(l).getName();				
				sizejsonObject.put(sizeid, name);
			
			}
			String[] job =request.getParameterValues("jobid");
			String[] hour =request.getParameterValues("hours");
			
			String[] size =request.getParameterValues("sizeid");
			String[] number =request.getParameterValues("number");
			//循环取出数据重新解析生成新的字符串进行kqb的存储
			String str="";
			if(job!=null && !"".equals(job)){				
				for(int i =0;i<job.length;i++){
					String  j = job[i];	
					String s ="";
					s =jsonObject.getString(j);
					String h =hour[i];
					str += s+":"+h+"; ";
				}
			}
			if(size!=null && !"".equals(size)){				
				for(int i =0;i<size.length;i++){
					String  s = size[i];	
					String ss ="";
					ss =sizejsonObject.getString(s);
					String num =number[i];
					str += ss+":"+num+"; ";
				}
			}
			str+="    更新日期："+sdf.format(new Date());
			Query q = session.createQuery("from Kqb where uuid =:uuid").setParameter("uuid", uuid);
			Kqb k =(Kqb) q.uniqueResult();
			Map<String,String> m = new HashMap<String, String>();  
			//动态添加属性字段-----使用工具类
			m.put("day"+cday,str);  			
			BeanRefUtil.setFieldValue(k, m); 
			session.update(k);
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
			Hfsession.close();

		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		//执行个添加导出表数据
		addKqbExport();
		return SUCCESS; 
	}

	public String exportKqb(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		List<Object> listObject = new ArrayList<>();
		List<Kqb> listKqb = new ArrayList<>();
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String hql = "from Kqb where 1=1 ";
			if(admin.getAuthority().equals("1") || admin.getAuthority().equals("3")){
				lists = SecondService.getListSecond();
			}else{
				hql += "and scz ="+ admin.getSecond();
			}
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			if(StringHelp.isNotEmpty(year)){
				hql += "and year = :year ";
			}
			if(StringHelp.isNotEmpty(month)){
				hql += "and month = :month ";
			}
			
			hql += "ORDER BY name DESC ";
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
//			if(StringHelp.isNotEmpty(scz)){
//				query.setParameter("scz", scz);
//			}
			if(StringHelp.isNotEmpty(year)){
				query.setParameter("year", year);
			}
			if(StringHelp.isNotEmpty(month)){
				query.setParameter("month", month);
			}
			
			listKqb = query.list();
			lists = SecondService.getListSecond();

			int i = 0;
			for(Kqb k :listKqb){
				for(Second s :lists){
					if(k.getScz().equals(s.getSecondId()+"")){
						k.setScz(s.getName());
						break;
					}
				}
				k.setUuid(++i+"");
				listObject.add(k);
			}
			

			String[] str = new String[]{"序号","姓名","生产组","岗位","年份","月份","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","本月考勤统计","健康证报销","水电费扣款","叉车证扣款","叉车证还款","员工还款","员工借款","住宿扣款","工作服和鞋扣款","返还工作服和鞋款","餐补","其他扣款","备注"};
			String title= year+"年"+month+"月考勤表.xls";
//			if(StringHelp.isNotEmpty(scz)){
//				for(Second s :lists){
//					if(scz.equals(s.getSecondId()+"")){
//						title = s.getName()+"生产组"+year+"年"+month+"月考勤表.xls";
//						break;
//					}
//				}
//			}else{
//				title = "国通企业"+year+"年"+month+"月考勤表.xls";
//			}
			Excel.exportExcel(title, str, listObject);
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String exportKqbExport(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		List<Object> listObject = new ArrayList<>();
		List<Kqb> listKqb = new ArrayList<>();
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String hql = "from KqbExport where 1=1 ";
			if(admin.getAuthority().equals("1") || admin.getAuthority().equals("3")){
				lists = SecondService.getListSecond();
			}else{
				hql += "and scz ="+ admin.getSecond();
			}
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
//			if(StringHelp.isNotEmpty(scz)){
//				hql += "and scz = :scz ";
//			}
			if(StringHelp.isNotEmpty(year)){
				hql += "and year = :year ";
			}
			if(StringHelp.isNotEmpty(month)){
				hql += "and month = :month ";
			}

			hql += "ORDER BY name DESC ";
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
//			if(StringHelp.isNotEmpty(scz)){
//				query.setParameter("scz", scz);
//			}
			if(StringHelp.isNotEmpty(year)){
				query.setParameter("year", year);
			}
			if(StringHelp.isNotEmpty(month)){
				query.setParameter("month", month);
			}

			List<KqbExport> listExp = query.list();
			lists = SecondService.getListSecond();

			int i = 0;
			for(KqbExport k :listExp){
				KqbExportOutDemo kd = new KqbExportOutDemo();
				kd.setBankCard(k.getBankCard());
				kd.setJobOrSizeName(k.getJobOrSizeName());
				kd.setMonth(k.getMonth());
				kd.setName(k.getName());
				kd.setScz(k.getScz());
				kd.setYear(k.getYear());
				kd.setDay1(k.getDay1());
				kd.setDay2(k.getDay2());
				kd.setDay3(k.getDay3());
				kd.setDay4(k.getDay4());
				kd.setDay5(k.getDay5());
				kd.setDay6(k.getDay6());
				kd.setDay7(k.getDay7());
				kd.setDay8(k.getDay8());
				kd.setDay9(k.getDay9());
				kd.setDay10(k.getDay10());
				kd.setDay11(k.getDay11());
				kd.setDay12(k.getDay12());
				kd.setDay13(k.getDay13());
				kd.setDay14(k.getDay14());
				kd.setDay15(k.getDay15());
				kd.setDay16(k.getDay16());
				kd.setDay17(k.getDay17());
				kd.setDay18(k.getDay18());
				kd.setDay19(k.getDay19());
				kd.setDay20(k.getDay20());
				kd.setDay21(k.getDay21());
				kd.setDay22(k.getDay22());
				kd.setDay23(k.getDay23());
				kd.setDay24(k.getDay24());
				kd.setDay25(k.getDay25());
				kd.setDay26(k.getDay26());
				kd.setDay27(k.getDay27());
				kd.setDay28(k.getDay28());
				kd.setDay29(k.getDay29());
				kd.setDay30(k.getDay30());
				kd.setDay31(k.getDay31());
				listObject.add(kd);
			}


			String[] str = new String[]{"姓名","银行卡","生产组","岗位名称","年份","月份","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
			String title= year+"年"+month+"月考勤表.xls";
//			if(StringHelp.isNotEmpty(scz)){
//				for(Second s :lists){
//					if(scz.equals(s.getSecondId()+"")){
//						title = s.getName()+"生产组"+year+"年"+month+"月考勤表.xls";
//						break;
//					}
//				}
//			}else{
//				title = "国通企业"+year+"年"+month+"月考勤表.xls";
//			}
			Excel.exportExcel(title, str, listObject);
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String exportKqbDetail(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		
		List<Object> listObject = new ArrayList<>();
		List<Kqb> listKqb = new ArrayList<>();
		Session session = Hfsession.init();
		Integer month1=Integer.parseInt(month);
		String sczname=request.getParameter("scz");
		
//		try {
//			if(scz !=null && !"".equals(scz)){
//				sczname = new String(scz.getBytes("iso8859-1"),"utf-8");
//			}
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String hql = "from Todaykq where 1=1 ";
			if(StringHelp.isNotEmpty(month)){
				hql += "and month = :month ";
			}
			if(sczname!=null && !sczname.equals("") && !sczname.equals("请选择")){
				hql += "and sczname = :sczname ";
			}

			hql += "ORDER BY jobid,sizeid DESC ";
			Query query = session.createQuery(hql);
			if(StringHelp.isNotEmpty(month)){
				query.setParameter("month", month1);
			}
			if(sczname!=null && !sczname.equals("") && !sczname.equals("请选择")){
				query.setParameter("sczname", sczname);
			}
			List<Todaykq> listTodaykq = query.list();
			Map<String,String> listjob= SecondService.getListJob();
			Map<String,String>  listsize= SecondService.getListSize();
			for(Todaykq k :listTodaykq){
				Query q = session.createQuery("from Kqb where uuid =:uuid").setParameter("uuid", k.getUserid());
				Kqb kqb =(Kqb) q.uniqueResult();
				KqbExpDetail ked =null;
				if(k.getJobid()!=null){
					ked = new KqbExpDetail();
					ked.setJobname(listjob.get(k.getJobid()+""));
					ked.setHour(k.getHours());
					ked.setSczname(k.getSczname());
					ked.setMonth(k.getMonth());
					ked.setDay(k.getDay());
					ked.setCreateDate(k.getCreateDate());
					ked.setName(kqb.getName());
				}
				if(k.getSizeid()!=null){
					ked = new KqbExpDetail();
					ked.setSizename(listsize.get(k.getSizeid()+""));
					ked.setNumber(k.getNumber());
					ked.setSczname(k.getSczname());
					ked.setMonth(k.getMonth());
					ked.setDay(k.getDay());
					ked.setCreateDate(k.getCreateDate());
					ked.setName(kqb.getName());
				}

				listObject.add(ked);
			}

			String[] str = new String[]{"姓名","生产组","计时","时长","计件","数量","月","日","录入日期"};
			String title= null;
		
			title = "国通企业"+month1+"月考勤详细总表.xls";

			Excel.exportExcel(title, str, listObject);
			Hfsession.close();
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String importKqb() throws IOException{
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Session session = Hfsession.init();
			Transaction tx = session.beginTransaction();
			InputStream fis = new FileInputStream(excelPath);
			list = new ArrayList<>();
			list = KqbService.importExcel(fis);
			for(Kqb info:list) {
	            session.save(info); 
	        } 
			tx.commit();
			Hfsession.close();
			map.put("msg", "success");
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			map.put("statusCode", 300);
			return SUCCESS;
		} 
		return SUCCESS;
	}
	//供月考勤信息查看
	public String viewKqb(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		int sczid =Integer.parseInt(request.getParameter("sczid"));
		String vmonth =request.getParameter("month");
		String vuuid =request.getParameter("uuid");
		if(admin != null && admin.getAuthority().equals("1")){
			lists = SecondService.getListSecond();
			joblist=JobService.getListJob(sczid);
			sizelist=SizeService.getListSize(sczid);
		}		
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from Kqb where uuid =:uuid").setParameter("uuid", uuid);
			kqb = (Kqb)q.uniqueResult();
			JDBCUtil jbutil =new JDBCUtil();
			Connection con =jbutil.getConnection();
			String sql1 ="SELECT b.`name`,a.number,a.userid  FROM (SELECT * FROM gt_kqb_todaykq WHERE userid=? and MONTH =?) a,gt_kqb_size b WHERE a.sizeid=b.sizeid";
			PreparedStatement psta1 =con.prepareStatement(sql1);
			psta1.setString(1, vuuid);
			psta1.setString(2, vmonth);	
			ResultSet rs1 =psta1.executeQuery();
			//存储jobname的集合
			List list1= new ArrayList<>();
			//存储jobhour的集合
			List list2= new ArrayList<>();
			String str ="";
			while(rs1.next()){
				list1.add(rs1.getString("name"));
				list2.add(rs1.getString("number"));
			}
			
			int j=0;
			List size1= new ArrayList<>();
			List num1= new ArrayList<>();
			for(int i =0;i<list1.size();i++){
				//取出sizename进行匹配
				if(j==0){
					size1.add(j, list1.get(i));
					num1.add(j, list2.get(i));
					j++;
				}else{
					int flag =0;
					for(int m=0;m<size1.size();m++){
						if(list1.get(i).equals(size1.get(m))){
							Integer num = Integer.parseInt(list2.get(i).toString())+Integer.parseInt(num1.get(m).toString());
							num1.set(m,num);
							flag=1;
							break;
						}
						
					}
					if(flag==0){
						size1.add(j, list1.get(i));
						num1.add(j, list2.get(i));
						j++;
					}
				}
				
			}
			if(size1!=null && !"".equals(num1)){
				for(int n =0;n<size1.size();n++){
					str+=size1.get(n).toString()+":"+num1.get(n).toString()+";  ";
				}
			}	
			
			String sql2 ="SELECT b.`name`,a.hours,a.userid  FROM (SELECT * FROM gt_kqb_todaykq WHERE userid=? and MONTH =?) a,gt_kqb_job b WHERE a.jobid=b.`secondId`";
			PreparedStatement psta2 =con.prepareStatement(sql2);
			psta2.setString(1, vuuid);
			psta2.setString(2, vmonth);	
			ResultSet rs2 =psta2.executeQuery();
			
			List list3= new ArrayList<>();
			List list4= new ArrayList<>();

			while(rs2.next()){
				/*str +=rs2.getString("hours")+":"+rs2.getString("name")+"; ";*/
				list3.add(rs2.getString("name"));
				list4.add(rs2.getString("hours"));
			}
			int k=0;
			List job1= new ArrayList<>();
			List hour1= new ArrayList<>();
			for(int i =0;i<list3.size();i++){
				//取出sizename进行匹配
				if(k==0){
					job1.add(k, list3.get(i));
					hour1.add(k, list4.get(i));
					k++;
				}else{
					int flag1 =0;
					for(int m1=0;m1<job1.size();m1++){
						if(list3.get(i).equals(job1.get(m1))){
							Integer num = Integer.parseInt(list4.get(i).toString())+Integer.parseInt(hour1.get(m1).toString());
							hour1.set(m1,num);
							flag1=1;
							break;
						}
						
					}
					if(flag1==0){
						job1.add(k, list3.get(i));
						hour1.add(k, list4.get(i));
						k++;
					}
				}
				
			}
			if(job1!=null && !"".equals(hour1)){
				for(int n =0;n<job1.size();n++){
					str+=job1.get(n).toString()+":"+hour1.get(n).toString()+";  ";
				}
			}	
			kqb.setMonthcount(str);
			jbutil.close();
			session.update(kqb);
			scz=request.getParameter("scz");
//			scz = new String(scz.getBytes("iso8859-1"),"utf-8");
			tx.commit();
			Hfsession.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return type+"";
	}
	public String cutEdit(){		
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = session.createQuery("from Kqb where uuid =:uuid").setParameter("uuid", uuid);
			Kqb kqb1 = (Kqb)q.uniqueResult();
			kqb1.setSubmit_healcard(kqb.getSubmit_healcard());
			kqb1.setCut_clothesandshoes(kqb.getCut_clothesandshoes());
			kqb1.setBorrow_staff(kqb.getBorrow_staff());
			kqb1.setCut_else(kqb.getCut_else());
			kqb1.setCut_forkcard(kqb.getCut_forkcard());
			kqb1.setCut_stay(kqb.getCut_stay());
			kqb1.setCut_waterandele(kqb.getCut_waterandele());
			kqb1.setRepay_clothesandshoes(kqb.getRepay_clothesandshoes());
			kqb1.setRepay_forkcard(kqb.getRepay_forkcard());
			kqb1.setRepay_staff(kqb.getRepay_staff());
			
			session.update(kqb1);
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
			Hfsession.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	public String getSczid() {
		return sczid;
	}

	public void setSczid(String sczid) {
		this.sczid = sczid;
	}
	public Kqbinfo getKqbinfo() {
		return kqbinfo;
	}

	public void setKqbinfo(Kqbinfo kqbinfo) {
		this.kqbinfo = kqbinfo;
	}

	public List<Kqbinfo> getListss() {
		return listss;
	}

	public void setListss(List<Kqbinfo> listss) {
		this.listss = listss;
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
	
	public Kqb getKqb() {
		return kqb;
	}

	public void setKqb(Kqb kqb) {
		this.kqb = kqb;
	}

	public List<Kqb> getList() {
		return list;
	}

	public void setList(List<Kqb> list) {
		this.list = list;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGw() {
		return gw;
	}

	public void setGw(String gw) {
		this.gw = gw;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getBanci() {
		return banci;
	}

	public void setBanci(String banci) {
		this.banci = banci;
	}

	public String getScz() {
		return scz;
	}

	public void setScz(String scz) {
		this.scz = scz;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<Second> getLists() {
		return lists;
	}

	public void setLists(List<Second> lists) {
		this.lists = lists;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public File getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(File excelPath) {
		this.excelPath = excelPath;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<Job> getJoblist() {
		return joblist;
	}

	public void setJoblist(List<Job> joblist) {
		this.joblist = joblist;
	}

	public List<Size> getSizelist() {
		return sizelist;
	}

	public void setSizelist(List<Size> sizelist) {
		this.sizelist = sizelist;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
