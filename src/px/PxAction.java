package px;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import admin.Admin;
import basis.Excel;
import basis.Hfsession;
import basis.ResultUtils;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import util.Page;
import util.StringHelp;

import com.opensymphony.xwork2.ActionSupport;

public class PxAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Map result;
	private PxInfo pxInfo;
	private String uuid;
	private String keyword;
	private String trainType;
	private String yandm;
	private List<PxInfo> list;
	private File excelPath;
	private Integer type;
	private String orderField;
	private String orderDirection;
	private Page page = new Page();
	private int currentPage = 1;
	private String pageType;
	private String exportType;
	private String month;
	private String year;


	
	



	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin != null) {
			return SUCCESS;
		} else {
			return ERROR;
		}
	}

	public String getPxInfoList() {
		Session session = Hfsession.init();
		Map<String, Object> map = new HashMap<String, Object>();
		// List<Roster> list = new ArrayList<Roster>();
		// Page page = new Page();
		page.setEveryPage(15);
		page.setCurrentPage(1);
		Date dateNow = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dateNow);
		c.add(Calendar.MONTH, 1);
		Date nextDate = c.getTime();
		Calendar now = Calendar.getInstance();  
        String year1 = now.get(Calendar.YEAR)+"";
        Integer m = now.get(Calendar.MONTH) + 1;
        String month1 = (now.get(Calendar.MONTH) + 1)+"";        
        if(m<10){
        	month1="0"+m;
        }
        if(year==null && month==null){
			year=year1;
			month=month1;
			yandm =year1+"-"+month1;
		}else{
			if(Integer.parseInt(month)<10){
				month="0"+month;
			}
			yandm  = year+"-"+month;
		}
		
		try {

			String hql = "select count(*) from PxInfo where 1=1 ";
			if (keyword != null && !keyword.equals("")) {
				hql += "and name like :name ";
			}
			if (trainType != null && !trainType.equals("")) {
				hql += "and trainType like :trainType ";
			}


			if ("3".equals(pageType)) {
				hql += " and dueToDate >= :dateNow and dueToDate <= :nextDate ";

			}

			if ("4".equals(pageType)) {
				hql += " and licenseStatus = :licenseStatus ";

			}
			//未考人员
			if ("5".equals(pageType)) {
				/*hql += " and ( theoryResults< :theoryResults or actualResults< :actualResults) ";
				hql += " and (FirstRetesttheoryResults< :FirstRetesttheoryResults or FirstRetestactualResults< :FirstRetestactualResults) " ;
				hql += " and (SecondRetesttheoryResults< :SecondRetesttheoryResults or SecondRetestactualResults< :SecondRetestactualResults)";*/
				hql += " and status = 0";

			}
			//考试未通过人员
			if ("6".equals(pageType)) {
				/*hql += " and ( FirstRetesttheoryResults< :FirstRetesttheoryResults or FirstRetestactualResults< :FirstRetestactualResults) ";
				hql +=	"and ( SecondRetesttheoryResults< :SecondRetesttheoryResults or SecondRetestactualResults< :SecondRetestactualResults) ";
				hql +=	"and ( ThirdRetesttheoryResults< :ThirdRetesttheoryResults or ThirdRetestactualResults< :ThirdRetestactualResults) ";
				hql +=	"and ( theoryResults< :theoryResults or actualResults< :actualResults)";*/
				hql += " and status = 2";
			}
			hql += " and signupDate like :signupDate ";

			Query query = session.createQuery(hql);

			query.setParameter("signupDate", "%" + yandm + "%");

			if (keyword != null && !keyword.equals("")) {
				query.setParameter("name", "%" + keyword + "%");
			}
			if (trainType != null && !trainType.equals("")) {
				query.setParameter("trainType", "%" + trainType + "%");
			}


			if ("3".equals(pageType)) {
				query.setParameter("dateNow", dateNow);
				query.setParameter("nextDate", nextDate);
			}
			if ("4".equals(pageType)) {
				query.setParameter("licenseStatus", "未领证");

			}
			/*if ("5".equals(pageType)) {
				query.setParameter("theoryResults",60);
				query.setParameter("actualResults", 60);
				query.setParameter("FirstRetesttheoryResults", 60);
				query.setParameter("FirstRetestactualResults", 60);
				query.setParameter("SecondRetesttheoryResults", 60);
				query.setParameter("SecondRetestactualResults", 60);
			}
			if ("6".equals(pageType)) {
				query.setParameter("theoryResults",60);
				query.setParameter("actualResults", 60);
				query.setParameter("FirstRetesttheoryResults", 60);
				query.setParameter("FirstRetestactualResults", 60);
				query.setParameter("SecondRetesttheoryResults", 60);
				query.setParameter("SecondRetestactualResults", 60);
				query.setParameter("ThirdRetesttheoryResults", 60);
				query.setParameter("ThirdRetestactualResults", 60);
			}*/
			Long count = (Long) query.uniqueResult();
			//System.out.println("===========count================"+count);
			page.setTotalCount(Integer.parseInt(String.valueOf(count)));
			String sql = "from PxInfo where 1=1 ";
			if ("3".equals(pageType)) {
				sql += " and dueToDate >= :dateNow and dueToDate <= :nextDate ";

			}
			if ("4".equals(pageType)) {
				sql += " and licenseStatus = :licenseStatus ";

			}
			//需要补考人员
			if ("5".equals(pageType)) {
				/*sql += " and ( theoryResults< :theoryResults or actualResults< :actualResults) ";
				sql += " and (FirstRetesttheoryResults< :FirstRetesttheoryResults or FirstRetestactualResults< :FirstRetestactualResults) " ;
				sql += " and (SecondRetesttheoryResults< :SecondRetesttheoryResults or SecondRetestactualResults< :SecondRetestactualResults)";*/
				sql += " and status = 0";
			}
			//补考未通过人员s
			if ("6".equals(pageType)) {
				/*sql += " and ( FirstRetesttheoryResults< :FirstRetesttheoryResults or FirstRetestactualResults< :FirstRetestactualResults) ";
				sql +=	"and ( SecondRetesttheoryResults< :SecondRetesttheoryResults or SecondRetestactualResults< :SecondRetestactualResults) ";
				sql +=	"and ( ThirdRetesttheoryResults< :ThirdRetesttheoryResults or ThirdRetestactualResults< :ThirdRetestactualResults) ";
				sql +=	"and ( theoryResults< :theoryResults or actualResults< :actualResults)";*/
				sql += " and status = 2";
			}
			if (keyword != null && !keyword.equals("")) {
				sql += "and name like :name ";
			}
			if (trainType != null && !trainType.equals("")) {
				sql += "and trainType like :trainType ";
			}
			sql += "and signupDate like :signupDate ";
			Query q = session.createQuery(sql);
			q.setParameter("signupDate","%" + yandm + "%");
			if (keyword != null && !keyword.equals("")) {
				q.setParameter("name", "%" + keyword + "%");
			}
			if (trainType != null && !trainType.equals("")) {
				q.setParameter("trainType", "%" + trainType + "%");
			}
			if ("3".equals(pageType)) {
				q.setParameter("dateNow", dateNow);
				q.setParameter("nextDate", nextDate);

			}

			if ("4".equals(pageType)) {
				q.setParameter("licenseStatus", "未领证");

			}
			/*if ("5".equals(pageType)) {
				q.setParameter("theoryResults",60);
				q.setParameter("actualResults", 60);
				q.setParameter("FirstRetesttheoryResults", 60);
				q.setParameter("FirstRetestactualResults", 60);
				q.setParameter("SecondRetesttheoryResults", 60);
				q.setParameter("SecondRetestactualResults", 60);
			}
			if ("6".equals(pageType)) {
				q.setParameter("theoryResults",60);
				q.setParameter("actualResults", 60);
				q.setParameter("FirstRetesttheoryResults", 60);
				q.setParameter("FirstRetestactualResults", 60);
				q.setParameter("SecondRetesttheoryResults", 60);
				q.setParameter("SecondRetestactualResults", 60);
				q.setParameter("ThirdRetesttheoryResults", 60);
				q.setParameter("ThirdRetestactualResults", 60);
			}*/
			q.setFirstResult((currentPage - 1) * 20);
			q.setMaxResults(20);
			list = q.list();
			page.setTotalPage((int) Math.ceil(count / 20.0));
			page.setHasPrePage(currentPage > 1);
			page.setHasNextPage(currentPage < (int) (Math.ceil(count / 20.0)));
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (pageType) {
		case "1":
			return "PXALL";
		case "2":
			return "PXJFGK";
		case "3":
			return "PXJJDQZS";
		case "4":
			return "PXWLZ";
		case "5":
			return "PXneedtest";
		case "6":
			return "PXRetest";
		default:
			return ERROR;
		}
	}

	public String delPxInfo() {
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Query q = session.createQuery("from PxInfo where uuid =:uuid").setParameter("uuid", uuid);
			pxInfo = (PxInfo) q.uniqueResult();
			session.delete(pxInfo);
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Hfsession.close();
		}
		return SUCCESS;
	}
	public String batchdelPxInfo(){
		HttpServletRequest request = ServletActionContext.getRequest ();
		Map uuidarrs =request.getParameterMap();
		Object uuidarrObj = uuidarrs.get("uuidarr[]");
		String[] uuidarr =(String[])uuidarrObj;
		List uuidList = Arrays.asList(uuidarr);

		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Query q = session.createQuery("from PxInfo where uuid  in :uuidarr").setParameterList("uuidarr", uuidList);
			List<PxInfo> list   = q.list();
			for (PxInfo r:list
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

	public String addPxInfo() {
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (pxInfo.getUuid() == null || "".equals(pxInfo.getUuid())) {
				//System.out.println("=============pxInfo.getSignupDate()====================="+pxInfo.getSignupDate());
				//pxInfo.setSignupDate(sdf.format(date));
				session.save(pxInfo);
			} else {
				session.update(pxInfo);
			}
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}finally{
			Hfsession.close();
		}
		return SUCCESS;
	}

	/**
	 * 改变人员的状态 0 未考 1 通过 2 未通过
	 * @return
	 */
	public String changeStatus() {
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		HttpServletRequest request = ServletActionContext.getRequest ();
		String statusStr = request.getParameter("status");
		Integer status = Integer.parseInt(statusStr);
		Map uuidarrs =request.getParameterMap();
		Object uuidarrObj = uuidarrs.get("uuidarr[]");
		String[] uuidarr =(String[])uuidarrObj;
		List uuidList = Arrays.asList(uuidarr);

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Query q = session.createQuery("from PxInfo where uuid  in :uuidarr").setParameterList("uuidarr", uuidList);
			List<PxInfo> list = q.list();

			for (PxInfo p : list
				 ) {
				p.setStatus(status);
				session.update(p);
			}

			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}finally{
			Hfsession.close();
		}
		return SUCCESS;
	}

	public String inDialog() {
		return SUCCESS;
	}

	public String editPxInfo() {
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery("from PxInfo where uuid =:uuid").setParameter("uuid", uuid);
			pxInfo = (PxInfo) q.uniqueResult();
			//System.out.println("修改==+"+pxInfo.getName());
			tx.commit();
			if (pxInfo == null) {
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		finally{
			Hfsession.close();
		}
		return SUCCESS;
	}

	public String exportPxInfo() {
		List<Object> listObject = new ArrayList<>();
		List<PxInfo> listPxInfo = new ArrayList<>();
		HttpServletRequest request = ServletActionContext.getRequest();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String exptrainType =request.getParameter("exptrainType");
		String exyear =request.getParameter("year");
		String exmonth =request.getParameter("month");
//		try {
//			if(extrainType!=null && !"".equals(extrainType)){
//				exptrainType = new String(extrainType.getBytes("iso8859-1"),"utf-8");
//			}
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		Session session = Hfsession.init();
		//Transaction tx = session.beginTransaction();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Query q;
			Date dateNow = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(dateNow);
			c.add(Calendar.MONTH, 1);
			Date nextDate = c.getTime();
		
			if(Integer.parseInt(month)<10){
				month="0"+exmonth;
			}
			yandm  = year+"-"+month;
			//System.out.println("==================================yandm=================================="+yandm);
			if (exportType != null && "1".equals(exportType)) {
				String sql="from PxInfo where 1 = 1  and dueToDate >= :dateNow and dueToDate <= :nextDate ";
				if(exptrainType!=null && !"".equals(exptrainType)){
					sql+=" and trainType like :trainType ";
				}
				sql += " and signupDate like :signupDate ";
				q = session.createQuery(sql);
				q.setParameter("signupDate","%" + yandm + "%");
				if(exptrainType!=null && !"".equals(exptrainType)){
					q.setParameter("trainType", "%"+exptrainType+"%");
				}
				q.setParameter("dateNow", dateNow);
				q.setParameter("nextDate", nextDate);
			}else if(exportType != null && "2".equals(exportType)){
				String sql="from PxInfo where 1 = 1  and licenseStatus = :licenseStatus ";
				if(exptrainType!=null && !"".equals(exptrainType)){
					sql+=" and trainType like :trainType ";
				}
				sql += " and signupDate like :signupDate ";
				q = session.createQuery(sql);
				q.setParameter("signupDate","%" + yandm + "%");
				if(exptrainType!=null && !"".equals(exptrainType)){
					q.setParameter("trainType", "%"+exptrainType+"%");
				}
				q.setParameter("licenseStatus", "未领证");
			}else {
				String sql ="from PxInfo where 1 = 1";
				if(exptrainType!=null && !"".equals(exptrainType)){
					sql+=" and trainType like:trainType ";
				}
				sql += "and signupDate like :signupDate ";
				q = session.createQuery(sql);
				q.setParameter("signupDate","%" + yandm + "%");
				if(exptrainType!=null && !"".equals(exptrainType)){
					q.setParameter("trainType", "%"+exptrainType+"%");
				}
			}
			listPxInfo = q.list();
			//tx.commit();
			for (int i = 0; i < listPxInfo.size();) {
				PxInfo r = listPxInfo.get(i);
				r.setUuid(++i + "");
				listObject.add(r);
			}
			String[] str = new String[] { "序号", "培训日期", "姓名", "姓别", "身份证", "文化程度", "单位/个人", "联系电话", "单位联系人", "单位电话",
					"培训点", "类别", "考试情况", "标准金额", "优惠金额", "缴费情况","收据号", "到期日期","考试日期", "领证情况",
					"生效日期","证书编号","学员编号","第一次补考费用","第二次补考费用","第三次补考费用","第一次补考收据号","第二次补考收据号","第三次补考收据号","理论成绩",
					"实际操作成绩","第一次补考理论成绩","第二次补考理论成绩","第三次补考理论成绩","第一次补考实操成绩","第二次补考实操成绩","第三次补考实操成绩"

			};
			String title="国通企业培训人员花名册--"+sdf.format(new Date())+".xls";
			if(exptrainType!=null && !"".equals(exptrainType)){
				title="国通企业培训部("+exptrainType+")人员花名册--"+sdf.format(new Date())+".xls";
			}
			Excel.exportExcel(title, str, listObject);
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Hfsession.close();
		}
		return SUCCESS;
	}

	public String importExcel() throws IOException, ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Session session = Hfsession.init();
			Transaction tx = session.beginTransaction();
			InputStream fis = new FileInputStream(excelPath);
			list = new ArrayList<>();
			list = PxService.importExcel(fis);
			//System.out.println("=============LISTSIZE===================="+list.size());
			for (PxInfo info : list) {
				session.save(info);
			}
			tx.commit();
			map.put("statusCode", 200);
			ResultUtils.toJson(ServletActionContext.getResponse(), map);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return ERROR;
		}finally{
			Hfsession.close();
		}
		return SUCCESS;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getYandm() {
		return yandm;
	}

	public void setYandm(String yandm) {
		this.yandm = yandm;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public PxInfo getPxInfo() {
		return pxInfo;
	}

	public void setPxInfo(PxInfo pxInfo) {
		this.pxInfo = pxInfo;
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

	public List<PxInfo> getList() {
		return list;
	}

	public void setList(List<PxInfo> list) {
		this.list = list;
	}

	public File getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(File excelPath) {
		this.excelPath = excelPath;
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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String inrosters() {
		return SUCCESS;
	}

}
