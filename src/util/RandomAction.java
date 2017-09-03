package util;

import java.io.ByteArrayInputStream;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RandomAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private ByteArrayInputStream inputStream; 
	@Override
	public String execute() throws Exception {
		RandomNumUtil rdnu=RandomNumUtil.Instance();    
		this.setInputStream(rdnu.getImage());//取得带有随机字符串的图片    
		ActionContext.getContext().getSession().put("random", rdnu.getString());//取得随机字符串放入HttpSession    
		return SUCCESS;    
	}
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
