package second;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basis.Hfsession;
import basis.ResultUtils;
import second.Second;
import second.SecondService;
import util.Page;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 生产部 二级权限管理
 * @author Administrator
 *
 */
public class SecondAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Map result;
	private String uuid;
	private String keyword;
	private Second second;
	private List<Second> list;
	private Page page = new Page();
	private int currentPage=1;		

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getSecondList(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		page.setEveryPage(20);
		page.setCurrentPage(1);
		try {
			String hql = "select count(*) from Second where 1=1 ";
			if(keyword != null && !keyword.equals("")){
				hql += "and name like :name ";
			}
			Query query = session.createQuery(hql);
			if(keyword != null && !keyword.equals("")){
				query.setParameter("name", "%"+keyword+"%");
			}
			Long count= (Long)query.uniqueResult();
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			String sql = "from Second where 1=1 ";
			if(keyword != null && !keyword.equals("")){ 
				sql += "and name like :name ";
			}
			/*if(orderField != null){
				sql += "order by " + orderField +" "+ orderDirection;
			}else{*/
				sql += "order by secondId desc";
			//}
			Query q = session.createQuery(sql);
			if(keyword != null && !keyword.equals("")){
				q.setParameter("name", "%"+keyword+"%");
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
	public String addSecond(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			if(second.getUuid() == null || "".equals(second.getUuid())){
				second.setCreateDate(new Date());
				second.setSecondId(SecondService.getSecondId());
				session.save(second);
			}else{
				session.update(second);
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
	
	public String editSecond(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from Second where uuid =:uuid").setParameter("uuid", uuid);
			second = (Second)q.uniqueResult();
			tx.commit();
			Hfsession.close();
			if(second == null){
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String delSecond(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = session.createQuery("from Second where uuid =:uuid").setParameter("uuid", uuid);
			second = (Second) q.uniqueResult();
			session.delete(second);
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
	public Second getSecond() {
		return second;
	}
	public void setSecond(Second second) {
		this.second = second;
	}
	public List<Second> getList() {
		return list;
	}
	public void setList(List<Second> list) {
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

}
