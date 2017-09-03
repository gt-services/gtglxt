package basis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import admin.Admin;

import com.opensymphony.xwork2.ActionSupport;

public class ExcelAction extends ActionSupport {
	private List<Object> list = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	
	private File excelPath;
	@Override
	public String execute() throws Exception {
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from Admin");
			list = q.list();
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] str = new String[2];
		str[0] = "姓名";
		str[1] = "密码";
		Excel.exportExcel("我嚓.xls", str,list);
		return SUCCESS;
	}
	
	public String importExcel(){
		try {
			Session session = Hfsession.init();
			Transaction tx = session.beginTransaction();
			InputStream fis = new FileInputStream(excelPath);
			List<Admin> admins = new ArrayList<>();
			admins = Excel.importExcel(fis);
			for(Admin info:admins) {  
	            session.save(info); 
	        } 
			tx.commit();
			Hfsession.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return SUCCESS;
	}

	public File getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(File excelPath) {
		this.excelPath = excelPath;
	}
	
}
