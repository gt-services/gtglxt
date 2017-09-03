package kqb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kqb.Kqb;
import second.Second;
import second.SecondService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class KqbService {
	public static List<Kqb> importExcel(InputStream fis) {  
        List<Kqb> infos = new ArrayList<Kqb>();  
        Kqb info = null;  
        List<Second> lists = SecondService.getListSecond();
        try {  
            //打开文件  
            Workbook book = Workbook.getWorkbook(fis);  
            //得到第一个工作表对象  
            Sheet sheet = book.getSheet(0);  
            //得到第一个工作表中的总行数  
            int rowCount = sheet.getRows();  
            for (int i = 1; i < rowCount; i++) {  
                info = new Kqb();  
                Cell[] cells = sheet.getRow(i);
                info.setName(cells[1].getContents().toString());
                for(Second s :lists){
					if(cells[2].getContents().toString().equals(s.getName())){
						info.setScz(s.getSecondId()+"");
						break;
					}
				}
                info.setGw(cells[3].getContents().toString());
                info.setYear(cells[4].getContents().toString());
                info.setMonth(cells[5].getContents().toString());
                info.setDay1(cells[6].getContents().toString());
                info.setDay2(cells[7].getContents().toString());
                info.setDay3(cells[8].getContents().toString());
                info.setDay4(cells[9].getContents().toString());
                info.setDay5(cells[10].getContents().toString());
                info.setDay6(cells[11].getContents().toString());
                info.setDay7(cells[12].getContents().toString());
                info.setDay8(cells[13].getContents().toString());
                info.setDay9(cells[14].getContents().toString());
                info.setDay10(cells[15].getContents().toString());
                info.setDay11(cells[16].getContents().toString());
                info.setDay12(cells[17].getContents().toString());
                info.setDay13(cells[18].getContents().toString());
                info.setDay14(cells[19].getContents().toString());
                info.setDay15(cells[20].getContents().toString());
                info.setDay16(cells[21].getContents().toString());
                info.setDay17(cells[22].getContents().toString());
                info.setDay18(cells[23].getContents().toString());
                info.setDay19(cells[24].getContents().toString());
                info.setDay20(cells[25].getContents().toString());
                info.setDay21(cells[26].getContents().toString());
                info.setDay22(cells[27].getContents().toString());
                info.setDay23(cells[28].getContents().toString());
                info.setDay24(cells[29].getContents().toString());
                info.setDay25(cells[30].getContents().toString());
                info.setDay26(cells[31].getContents().toString());
                info.setDay27(cells[32].getContents().toString());
                info.setDay28(cells[33].getContents().toString());
                info.setDay29(cells[34].getContents().toString());
                info.setDay30(cells[35].getContents().toString());
                info.setDay31(cells[36].getContents().toString());              
                info.setRemark(cells[39].getContents().toString());
                
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
