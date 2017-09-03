package cw;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cw.Cw;
import cw.Sbinfo;
import second.Second;
import second.SecondService;
import util.Page;
import util.StringHelp;
import com.opensymphony.xwork2.ActionSupport;

import basis.Hfsession;
import basis.ResultUtils;

public class CwAction extends ActionSupport{

	
	private static final long serialVersionUID = 1L;
	private Map result;
	private String uuid;
	private String keyword;
	private String scz;
	private String year;
	private String month;
	private Cw cw;
	private List<Cw> list;
	private Sbinfo sbinfo;

	private List<Sbinfo> listss;
	private List<Second> lists;
	private Page page = new Page();
	private int currentPage=1;		

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getCwList(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		Calendar now = Calendar.getInstance();  
        String year1 = now.get(Calendar.YEAR)+"";
        String month1 = (now.get(Calendar.MONTH) + 1)+"";
		try {
			String hql = "select count(*) from Cw where 1=1 ";
			lists = SecondService.getListSecond();
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
			Query query = session.createQuery(hql);
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
			Long count= (Long)query.uniqueResult();
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			String sql = "from Cw where 1=1 ";
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
			sql += "ORDER BY createDate DESC";
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
			q.setFirstResult((currentPage-1)*20); 
			q.setMaxResults(20);                                                                                                                                                                                   
			list = q.list();
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
	public String sbDetail(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		Calendar now = Calendar.getInstance();  
        String year1 = now.get(Calendar.YEAR)+"";
        String month1 = (now.get(Calendar.MONTH) + 1)+"";
		try {
			String hql = "select count(*) from Sbinfo where 1=1 ";
			
			if(keyword != null && !keyword.equals("")){ 
				hql += "and name like :name ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			Long count= (Long)query.uniqueResult();
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			String sql = "from Sbinfo  where 1=1 ";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			sql += "ORDER BY createDate DESC";
			Query q = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q.setParameter("name", "%"+keyword+"%");
			}
			q.setFirstResult((currentPage-1)*20); 
			q.setMaxResults(20);                                                                                                                                                                                   
			listss = q.list();
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
	public String addCw(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(cw.getUuid() == null || "".equals(cw.getUuid())){
				cw.setCreateDate(new Date());
				//cw.setSecondId(SecondService.getSecondId());
				session.save(cw);
			}else{
				session.update(cw);
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
	
	public String editCw(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		lists = SecondService.getListSecond();
		if(!StringHelp.isNotEmpty(uuid)){
			return SUCCESS;
		}
		try {
			Query q = session.createQuery("from Cw where uuid =:uuid").setParameter("uuid", uuid);
			cw = (Cw)q.uniqueResult();
			tx.commit();
			Hfsession.close();
			if(cw == null){
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String delCw(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = session.createQuery("from Cw where uuid =:uuid").setParameter("uuid", uuid);
			cw = (Cw) q.uniqueResult();
			session.delete(cw);
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
	
	public Cw getCw() {
		return cw;
	}

	public void setCw(Cw cw) {
		this.cw = cw;
	}

	public List<Cw> getList() {
		return list;
	}

	public void setList(List<Cw> list) {
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

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	
	public Sbinfo getSbinfo() {
		return sbinfo;
	}

	public void setSbinfo(Sbinfo sbinfo) {
		this.sbinfo = sbinfo;
	}

	public List<Sbinfo> getListss() {
		return listss;
	}

	public void setListss(List<Sbinfo> listss) {
		this.listss = listss;
	}

	
	
}
