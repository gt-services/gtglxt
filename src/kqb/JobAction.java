package kqb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import admin.Admin;
import basis.Hfsession;
import basis.ResultUtils;
import kqb.Job;
import second.Second;
import kqb.JobService;
import second.SecondService;
import util.Page;
import util.StringHelp;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 生产部 岗位的管理
 * @author Administrator
 *
 */
public class JobAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Map result;
	private String uuid;
	private String scz;
	private Integer sczsearch;
	private String keyword;
	private Job job;
	private Admin admin;
	private List<Job> list;
	private List<Second> lists;
	private Page page = new Page();
	private int currentPage=1;		

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getJobList(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			String hql = "select count(*) from Job where 1=1 ";
			
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			
			if(admin.getAuthority().equals("5")){
				hql +="and scz =:scz";
			}
			if(sczsearch != null && !sczsearch.equals("")){
				hql += "and scz = :sczsearch ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			if(admin.getAuthority().equals("5")){
				int scz = admin.getSecond();
				query.setParameter("scz", scz);
			}
			if(sczsearch != null && !sczsearch.equals("")){
				query.setParameter("sczsearch", sczsearch);
			}
			
			
			Long count= (Long)query.uniqueResult();
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			String sql = "from Job where 1=1 ";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			if(admin.getAuthority().equals("5")){
				sql +="and scz =:scz ";
			}
			if(sczsearch != null && !sczsearch.equals("")){
				sql += "and scz = :sczsearch ";
			}
			
				sql += "order by secondId desc";
			
			Query q = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q.setParameter("name", "%"+keyword+"%");
			}
			if(admin.getAuthority().equals("5")){
				int scz = admin.getSecond();
				q.setParameter("scz", scz);
			}
			if(sczsearch != null && !sczsearch.equals("")){
				q.setParameter("sczsearch", sczsearch);
			}
			q.setFirstResult((currentPage-1)*20); 
			q.setMaxResults(20);                                                                                                                                                                                   
			list = q.list();
			Query q1 = session.createQuery("from Second where 1=1");
			lists = q1.list();
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
	public String addJob(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(job.getUuid() == null || "".equals(job.getUuid())){
				job.setCreateDate(new Date());
				job.setSecondId(JobService.getSecondId());
				session.save(job);
			}else{
				session.update(job);
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
	public String addJobopen(){

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
		return SUCCESS;
	}
	
	public String editJob(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		scz = request.getParameter("scz");
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
			Query q1 = session.createQuery("from Job where uuid =:uuid").setParameter("uuid", uuid);
			job = (Job)q1.uniqueResult();
			tx.commit();
			Hfsession.close();
			if(job == null){
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String delJob(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = session.createQuery("from Job where uuid =:uuid").setParameter("uuid", uuid);
			job = (Job) q.uniqueResult();
			session.delete(job);
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<Job> getList() {
		return list;
	}

	public void setList(List<Job> list) {
		this.list = list;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Second> getLists() {
		return lists;
	}

	public void setLists(List<Second> lists) {
		this.lists = lists;
	}

	public String getScz() {
		return scz;
	}

	public void setScz(String scz) {
		this.scz = scz;
	}

	public Integer getSczsearch() {
		return sczsearch;
	}

	public void setSczsearch(Integer sczsearch) {
		this.sczsearch = sczsearch;
	}
	

}
