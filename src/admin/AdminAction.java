package admin;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basis.Hfsession;
import basis.ResultUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport{//用户名密码

	private static final long serialVersionUID = 1L;
	private Map result;
	private String username;
	private String password;
	private String rand;
	private Admin admin;
	
	@Override
	//创建会话，开启事务，对传入的数据进行后台处理的控制
	public String execute() throws Exception {
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		HttpServletRequest request = ServletActionContext.getRequest ();
		HttpSession httpSession = request.getSession();
		Map<String, Object> map = new HashMap<String, Object>();
		
		//验证码
		String arandom=(String)(ActionContext.getContext().getSession().get("random"));    
		//下面就是将session中保存验证码字符串与客户输入的验证码字符串对比了    
		if(!arandom.equals(rand)) {    
			map.put("status", 0);
			map.put("msg", "验证码错误！");
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			return ERROR; 
		}  
		//用户的登录   匹配用户名，密码
		try {
			//Query q = session.createQuery("from Admin where authority=(select MAX(authority) from Admin )");
			Query q = session.createQuery("from Admin where username =:username").setParameter("username", username);
			admin = (Admin)q.uniqueResult();
			if(admin == null){
				map.put("status", 0);
				map.put("msg", "用户不存在！");
				ResultUtils.toJson(ServletActionContext.getResponse(), map);
				return ERROR;
			}
			password = Hfsession.getResult(password);
			if(password.equals(admin.getPassword())){
				httpSession.setAttribute("admin",admin);
				httpSession.setAttribute("authority",admin.getAuthorityName()); //将登陆成功的角色名放入session中
			}else{
				map.put("status", 0);
				map.put("msg", "密码错误！");
				ResultUtils.toJson(ServletActionContext.getResponse(), map);//先留一下
				return ERROR;
			}
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//map.put("admin",admin);
		map.put("status", 1);
		ResultUtils.toJson(ServletActionContext.getResponse(), map);
		return SUCCESS;
	}
	//登出
	public String logout() throws Exception{
		ActionContext ac = ActionContext.getContext(); 
		Map session = ac.getSession();  
		session.remove("admin");
		session.remove("authority"); 
		return SUCCESS;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}
	
}
