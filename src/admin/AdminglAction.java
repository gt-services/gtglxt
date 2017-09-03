package admin;

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

import basis.Hfsession;
import basis.ResultUtils;
import second.Second;
import util.Page;
import com.opensymphony.xwork2.ActionSupport;

public class AdminglAction extends ActionSupport{ //用户的详细信息和修改操作
	private static final long serialVersionUID = 1L;
	private Map result;
	private Admin admin;
	private String uuid;
	private String realname;
	private String phone;
	private String email;
	private String keyword;
	private String password;
	private String pass;


	private List<Admin> list;
	private List<Second> lists;
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
	/**
	 *修改密码
	 * @return
	 */
	public String changpwd(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
	
		Map<String,Object> map = new HashMap<String,Object>();
		if(admin == null){
			return ERROR;
		}
		try {
			if(!admin.getPassword().equals(Hfsession.getResult(password))){
				map.put("status", 0);
				map.put("msg", "原密码错误！");
			}else{
				Session session = Hfsession.init();
				Transaction tx = session.beginTransaction();
				admin.setPassword(Hfsession.getResult(pass));
				session.update(admin);
				map.put("status", 200);
				map.put("msg", "修改成功！");
				httpSession.setAttribute("admin",admin);
				tx.commit();
				Hfsession.close();
			}
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改个人信息
	 * @return
	 */
	public String myinfo(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Admin admin = (Admin) httpSession.getAttribute("admin");
	
		Map<String,Object> map = new HashMap<String,Object>();
		if(admin == null){
			return ERROR;
		}
		try {
			Session session = Hfsession.init();
			Transaction tx = session.beginTransaction();
			admin.setRealname(realname);
			admin.setPhone(phone);
			admin.setEmail(email);
			session.update(admin);
			map.put("status", 200);
			map.put("msg", "修改成功！");
			httpSession.setAttribute("admin",admin);
			tx.commit();
			Hfsession.close();
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	//模糊查询和分页
	public String getAdminList(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			String hql = "select count(*) from Admin where 1=1 ";
			if(keyword != null && !keyword.equals("")){
				hql += "and username like :username ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("username", "%"+keyword+"%");
			}
			Long count= (Long)query.uniqueResult();
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			String sql = "from Admin where 1=1 ";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and username like :username ";
			}
			/*if(orderField != null){
				sql += "order by " + orderField +" "+ orderDirection;
			}else{*/
				sql += "order by createDate desc";
			//}
			Query q = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q.setParameter("username", "%"+keyword+"%");
			}
			q.setFirstResult((currentPage-1)*20); 
			q.setMaxResults(20); 
			//System.out.println("++++++qq++"+q);
			list = q.list();
			page.setTotalPage((int)Math.ceil(count/20.0));
			page.setHasPrePage(currentPage>1);
			page.setHasNextPage(currentPage<(int)(Math.ceil(count/20.0)));
			tx.commit();
			Hfsession.close();
			for(Admin admin: list){
				admin.setAuthority(admin.getAuthorityName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//修改用户信息
	public String editAdmin(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			Query q = null;
			if(uuid != null){
				q = session.createQuery("from Admin where uuid =:uuid").setParameter("uuid", uuid);
				admin = (Admin)q.uniqueResult();
			}
			q = session.createQuery("from Second order by createDate desc");
			lists = q.list();
			tx.commit();
			Hfsession.close();
			if(admin == null){
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	//修改生产部门上报权限
	public String editAdminqx(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		String status = request.getParameter("status");
		String uuid = request.getParameter("uuid");
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = null;
			if(uuid != null){
				q = session.createQuery("from Admin where uuid =:uuid").setParameter("uuid", uuid);
				Admin cadmin = (Admin)q.uniqueResult();
				cadmin.setStatus(status);
				session.update(cadmin);
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
	public String addAdmin(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(admin.getPassword() == null || "".equals(admin.getPassword())){
				admin.setPassword(Hfsession.getResult(admin.getPassword()));
			}
			if(!pass.equals("")){
				admin.setPassword(Hfsession.getResult(pass));
			}
			if(admin.getUuid() == null || "".equals(admin.getUuid())){
				admin.setCreateDate(new Date());
				session.save(admin);
			}else{
				session.update(admin);
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
	public String delAdmin(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String[] ids = null;   
			ids = uuid.split(",");
			for(String id :ids){
				Query q = session.createQuery("from Admin where uuid =:uuid").setParameter("uuid", id);
				admin = (Admin) q.uniqueResult();
				session.delete(admin);
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
	
	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public List<Admin> getList() {
		return list;
	}
	public void setList(List<Admin> list) {
		this.list = list;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Second> getLists() {
		return lists;
	}

	public void setLists(List<Second> lists) {
		this.lists = lists;
	}
	
}
