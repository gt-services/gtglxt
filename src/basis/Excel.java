package basis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import admin.Admin;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel {
	/**
	 * 导出excel
	 * @param fileName
	 * @param Title
	 * @param listContent
	 * @return
	 */
	 public static String exportExcel(String fileName,String[] Title, List<Object> listContent) {  
		  String result="系统提示：Excel文件导出成功！";    
		  // 以下开始输出到EXCEL  
		  try {      
		   //定义输出流，以便打开保存对话框______________________begin  
		   HttpServletResponse response=ServletActionContext.getResponse();  
		   OutputStream os = response.getOutputStream();// 取得输出流        
		   response.reset();// 清空输出流        
		   response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));  
		// 设定输出文件头        
		   response.setContentType("application/msexcel");// 定义输出类型      
		   //定义输出流，以便打开保存对话框_______________________end  
		  
		   /** **********创建工作簿************ */  
		   WritableWorkbook workbook = Workbook.createWorkbook(os);  
		  
		   /** **********创建工作表************ */  
		  
		   WritableSheet sheet = workbook.createSheet("Sheet1", 0);  
		  
		   /** **********设置纵横打印（默认为纵打）、打印纸***************** */  
		   jxl.SheetSettings sheetset = sheet.getSettings();  
		   sheetset.setProtected(false);  
		  
		  
		   /** ************设置单元格字体************** */  
		   WritableFont NormalFont = new WritableFont(WritableFont.ARIAL, 10);  
		   WritableFont BoldFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.BOLD);  
		  
		   /** ************以下设置三种单元格样式，灵活备用************ */  
		   // 用于标题居中  
		   WritableCellFormat wcf_center = new WritableCellFormat(BoldFont);  
		   wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条  
		   wcf_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
		   wcf_center.setAlignment(Alignment.CENTRE); // 文字水平对齐  
		   wcf_center.setWrap(false); // 文字是否换行  
		     
		   // 用于正文居左  
		   WritableCellFormat wcf_left = new WritableCellFormat(NormalFont);  
		   wcf_left.setBorder(Border.NONE, BorderLineStyle.THIN); // 线条  
		   wcf_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 文字垂直对齐  
		   wcf_left.setAlignment(Alignment.LEFT); // 文字水平对齐  
		   wcf_left.setWrap(false); // 文字是否换行     
		   
		  
		   /** ***************以下是EXCEL开头大标题，暂时省略********************* */  
		   //sheet.mergeCells(0, 0, colWidth, 0);  
		   //sheet.addCell(new Label(0, 0, "XX报表", wcf_center));  
		   /** ***************以下是EXCEL第一行列标题********************* */  
		   for (int i = 0; i < Title.length; i++) {  
		    sheet.addCell(new Label(i, 0,Title[i],wcf_center));  
		   }     
		   /** ***************以下是EXCEL正文数据********************* */  
		   Field[] fields=null;  
		   int i=1;  
		   for(Object obj:listContent){  
		       fields=obj.getClass().getDeclaredFields();  
		       int j=0;  
		       for(Field v:fields){  
		           v.setAccessible(true);  
		           Object va=v.get(obj);  
		           if (va instanceof Long) {
		        	   continue;
		           }
		           if(va==null){  
		               va="";  
		           }  
		           sheet.addCell(new Label(j, i,va.toString(),wcf_left));
		           j++;  
		       }  
		       i++;  
		   }  
		   /** **********将以上缓存中的内容写到EXCEL文件中******** */  
		   workbook.write();  
		   /** *********关闭文件************* */  
		   workbook.close();     
		  
		  } catch (Exception e) {  
		   result="系统提示：Excel文件导出失败，原因："+ e.toString();  
		   System.out.println(result);   
		   e.printStackTrace();  
		  }  
		  return result;  
	}  
	 /**
	  * 导入excel
	  * @param fis
	  * @return
	  */
    public static List<Admin> importExcel(InputStream fis) {  
          
        List<Admin> infos = new ArrayList<Admin>();  
        Admin info = null;  
          
        try {  
            //打开文件  
            Workbook book = Workbook.getWorkbook(fis);  
            //得到第一个工作表对象  
            Sheet sheet = book.getSheet(0);  
            //得到第一个工作表中的总行数  
            int rowCount = sheet.getRows();  
            //日期格式化  
            DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");  
            //循环取出Excel中的内容  
            for (int i = 1; i < rowCount; i++) {  
                info = new Admin();  
                Cell[] cells = sheet.getRow(i);
                //info.setUsername(Long.parseLong(cells[0].getContents()));  
                info.setUsername(cells[0].getContents().toString());  
                info.setPassword(cells[1].getContents().toString());
                /* info.setFullName(cells[2].getContents().toString());  
                info.setSex(cells[3].getContents().toString());  
                info.setDateOfBirth(new Date());  
                info.setTownOfBirth(cells[5].getContents().toString());  
                info.setNationalIdentifier(cells[6].getContents().toString());  */
                infos.add(info);  
            }  
              
        } catch (BiffException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return infos;  
    }  
	 
	 
}
