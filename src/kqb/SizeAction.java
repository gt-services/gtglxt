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
import kqb.Size;
import second.Second;
import kqb.SizeService;
import second.SecondService;
import util.Page;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 生产部 岗位的管理
 * @author Administrator
 *
 */
public class SizeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Map result;
	private String uuid;
	private String scz;
	private Integer sczsearch;
	private String keyword;
	private Size size;
	private List<Size> list;
	private List<Second> lists;
	private Page page = new Page();
	private int currentPage=1;		

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getSizeList(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			String hql = "select count(*) from Size where 1=1 ";
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
			String sql = "from Size where 1=1 ";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			if(admin.getAuthority().equals("5")){
				sql +="and scz =:scz ";
			}
			if(sczsearch != null && !sczsearch.equals("")){
				sql += "and scz = :sczsearch ";
			}
			
				sql += "order by sizeid desc";
		
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
			setLists(q1.list());
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
	public String addSize(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(size.getUuid() == null || "".equals(size.getUuid())){
				size.setCreateDate(new Date());
				size.setSizeid(SizeService.getSizeid());
				session.save(size);
			}else{
				session.update(size);
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
	public String addSizeopen(){

		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			if(admin != null && admin.getAuthority().equals("5")){
				Integer secondId = admin.getSecond();
				Query q = session.createQuery("from Second where secondId=:secondId").setParameter("secondId", secondId);
				setLists(q.list());
			}
			else if(admin != null && admin.getAuthority().equals("1")){
				Query q = session.createQuery("from Second where 1=1");
				setLists(q.list());
			}
			
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String editSize(){
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
				setLists(q.list());
			}
			else if(admin != null && admin.getAuthority().equals("1")){
				Query q = session.createQuery("from Second where 1=1");
				setLists(q.list());
			}
			Query q1 = session.createQuery("from Size where uuid =:uuid").setParameter("uuid", uuid);
			size = (Size)q1.uniqueResult();
			tx.commit();
			Hfsession.close();
			if(size == null){
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String delSize(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = session.createQuery("from Size where uuid =:uuid").setParameter("uuid", uuid);
			size = (Size) q.uniqueResult();
			session.delete(size);
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

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public List<Size> getList() {
		return list;
	}

	public void setList(List<Size> list) {
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

	public List<Second> getLists() {
		return lists;
	}

	public void setLists(List<Second> lists) {
		this.lists = lists;
	}
	

}
