package kqb;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kqb.Kqb;
import second.Second;
import second.SecondService;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class KqbService {
	public static List<KqbExport> importExcel(InputStream fis) {
        List<KqbExport> infos = new ArrayList<KqbExport>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        KqbExport info = null;
        try {
            //打开文件  
            Workbook book = Workbook.getWorkbook(fis);  
            //得到第一个工作表对象  
            Sheet sheet = book.getSheet(0);  
            //得到第一个工作表中的总行数  
            int rowCount = sheet.getRows();  
            for (int i = 1; i < rowCount; i++) {  
                info = new KqbExport();
                Cell[] cells = sheet.getRow(i);
//                info.setKeid(Integer.parseInt(cells[0].getContents()));
                info.setUuid(cells[0].getContents().toString());
                info.setName(cells[1].getContents().toString());
                info.setBankCard(cells[2].getContents().toString());
                info.setScz(cells[3].getContents().toString());
//                info.setJid(cells[5].getContents().toString().equals("")?null:Integer.parseInt(cells[5].getContents()));
//                info.setSid(cells[6].getContents().toString().equals("")?null:Integer.parseInt(cells[6].getContents()));
                info.setJobOrSizeName(cells[4].getContents().toString());
                info.setYear(cells[5].getContents().toString());
                info.setMonth(cells[6].getContents().toString());

                info.setDay1(cells[7].getContents().toString());
                info.setDay2(cells[8].getContents().toString());
                info.setDay3(cells[9].getContents().toString());
                info.setDay4(cells[10].getContents().toString());
                info.setDay5(cells[11].getContents().toString());
                info.setDay6(cells[12].getContents().toString());
                info.setDay7(cells[13].getContents().toString());
                info.setDay8(cells[14].getContents().toString());
                info.setDay9(cells[15].getContents().toString());
                info.setDay10(cells[16].getContents().toString());
                info.setDay11(cells[17].getContents().toString());
                info.setDay12(cells[18].getContents().toString());
                info.setDay13(cells[19].getContents().toString());
                info.setDay14(cells[20].getContents().toString());
                info.setDay15(cells[21].getContents().toString());
                info.setDay16(cells[22].getContents().toString());
                info.setDay17(cells[23].getContents().toString());
                info.setDay18(cells[24].getContents().toString());
                info.setDay19(cells[25].getContents().toString());
                info.setDay20(cells[26].getContents().toString());
                info.setDay21(cells[27].getContents().toString());
                info.setDay22(cells[28].getContents().toString());
                info.setDay23(cells[29].getContents().toString());
                info.setDay24(cells[30].getContents().toString());
                info.setDay25(cells[31].getContents().toString());
                info.setDay26(cells[32].getContents().toString());
                info.setDay27(cells[33].getContents().toString());
                info.setDay28(cells[34].getContents().toString());
                info.setDay29(cells[35].getContents().toString());
                info.setDay30(cells[36].getContents().toString());
                info.setDay31(cells[37].getContents().toString());
                info.setSubmit_healcard(cells[38].getContents().toString());
                info.setCut_waterandele(cells[39].getContents().toString());
                info.setCut_forkcard(cells[40].getContents().toString());
                info.setRepay_forkcard(cells[41].getContents().toString());
                info.setRepay_staff(cells[42].getContents().toString());
                info.setBorrow_staff(cells[43].getContents().toString());
                info.setCut_stay(cells[44].getContents().toString());
                info.setCut_clothesandshoes(cells[45].getContents().toString());
                info.setRepay_clothesandshoes(cells[46].getContents().toString());
                info.setCanbu(cells[47].getContents().toString());
                info.setCut_else(cells[48].getContents().toString());
                info.setRemark(cells[49].getContents().toString());
                info.setCreateDate(new Date());
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
