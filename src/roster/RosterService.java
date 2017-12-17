package roster;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class RosterService {
	public static List<Roster> importExcel(InputStream fis) {
        List<Roster> infos = new ArrayList<Roster>();
        Roster info = null;

        try {
            //打开文件
            Workbook book = Workbook.getWorkbook(fis);
            //得到第一个工作表对象

            Sheet[] sheet = book.getSheets();
            //得到第一个工作表中的总行数
            int rowCount = sheet[0].getRows();
            //日期格式化
            DateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            //循环取出Excel中的内容
            Date date = new Date();
            for (int i = 1; i < rowCount; i++) {
            	Cell[] cells =sheet[0].getRow(i);
            	int cellslength=cells.length;
            	info = new Roster();
            	if(cellslength >=1){
            		info.setHtNumber(cells[0].getContents().toString());
                }
            	if(cellslength >=2){
            		info.setName(cells[1].getContents().toString());
                }
            	if(cellslength >=3){
            		info.setSex(cells[2].getContents().toString());
                }
            	if(cellslength >=4){
            		info.setIdentityId(cells[3].getContents().toString());
                }
            	if(cellslength >=5){
            		info.setBankCard(cells[4].getContents().toString());

                }
            	if(cellslength >=6){
            		info.setSbNumber(cells[5].getContents().toString());
                }
            	if(cellslength >=7){
            		info.setAddress(cells[6].getContents().toString());
                }
            	if(cellslength >=10){
            		info.setJobType(cells[9].getContents().toString());
                }
            	if(cellslength >=11){
            		info.setPhone(cells[10].getContents().toString());
                }
            	if(cellslength >=15){
            		info.setBxType(cells[14].getContents().toString());
                }
            	if(cellslength >=16){
            		info.setGsqk(cells[15].getContents().toString());
                }
            	if(cellslength >=19){
            		info.setSource(cells[18].getContents().toString());
                }
            	if(cellslength >=20){
            		info.setScz(cells[19].getContents().toString());
                }
            	if(cellslength >=21){
            		info.setBeizhu(cells[20].getContents().toString());
                }

                try {
                	if(cellslength >=8){
                		if(!cells[7].getContents().toString().equals("")){
                			info.setHtStart(ft.parse(cells[7].getContents().toString()));
                		}
                	}
                	if(cellslength >=9){
                		if(!cells[8].getContents().toString().equals("")){
                			info.setHtEnd(ft.parse(cells[8].getContents().toString()));
                		}
                	}
                	if(cellslength >=12){
                		if(!cells[11].getContents().toString().equals("")){
                			info.setLcTime(ft.parse(cells[11].getContents().toString()));
                		}
                	}
                	if(cellslength >=13){
                		if(!cells[12].getContents().toString().equals("")){
                			info.setSbStart(ft.parse(cells[12].getContents().toString()));
                		}
                	}
                	if(cellslength >=14){
                		if(!cells[13].getContents().toString().equals("")){
                			info.setSbEnd(ft.parse(cells[13].getContents().toString()));
                		}
                	}
                	if(cellslength >=17){
                		if(!cells[16].getContents().toString().equals("")){
                			info.setGsStart(ft.parse(cells[16].getContents().toString()));
                		}

                	}
                	if(cellslength >=18){
                		if(!cells[17].getContents().toString().equals("")){
                			info.setGsEnd(ft.parse(cells[17].getContents().toString()));
                		}

                	}
                } catch (ParseException e) {
					e.printStackTrace();
				}

                info.setAddinkq("未添加");
                info.setDel("1");
                info.setCreateDate(new Date());
                info.setCreateDate2(new Date());
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
