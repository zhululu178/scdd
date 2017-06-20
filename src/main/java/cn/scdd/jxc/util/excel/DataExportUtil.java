package cn.scdd.jxc.util.excel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cn.scdd.jxc.entity.ScddOrderSearchPage;
import cn.scdd.jxc.util.MethodTool;


public class DataExportUtil{
	private static String DOWNLOAD_TEMPLATE = "template" + File.separator + "download";
    private static DataExportUtil tool = new DataExportUtil();
    
	/** 实体 **/
	private class Entity {
		/** 名称 **/
		String name;
		/** 文本 **/
		String text;
		/** 类型 **/
		String type;
		/** 宽度 **/
		Integer width;
	}
    
    /**
	 * 解析XML文件
	 * 
	 * @param path path
	 * @return List<Entity>
	 * @throws Exception Exception
	 */
	private List<Entity> parseXML(String path) throws Exception {
		List<Entity> list = new ArrayList<Entity>();
		Log log = LogFactory.getLog(getClass());
		log.info("=================" + path);
		Document doc = DocumentBuilderFactory
				.newInstance()
				.newDocumentBuilder()
				.parse(this.getClass().getClassLoader()
						.getResourceAsStream(path));
		NodeList nodeList = doc.getElementsByTagName("property");
		Element element;
		for (int i = 0; i < nodeList.getLength(); i++) {
			element = (Element) nodeList.item(i);
			Entity e = new Entity();
			e.name = element.getAttribute("name");
			e.text = element.getAttribute("text");
			e.type = element.getAttribute("type");
			if (element.getAttribute("width") != null
					&& element.getAttribute("width").length() > 0) {
				e.width = Integer.valueOf(element.getAttribute("width"));
			}
			list.add(e);
		}
		return list;
	}
    
    /**
     * 导出Excel 97(.xls)格式 ，少量数据
     * @param title 标题行 
     * @param headMap 属性-列名
     * @param jsonArray 数据集
     * @param datePattern 日期格式，null则用默认日期格式
     * @param colWidth 列宽 默认 至少17个字节
     * @param out 输出流
     * @throws Exception 
     */
    public static void exportExcel(List<?> list, OutputStream out) throws Exception {
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
         //表头样式
        XSSFCellStyle headerStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontName("仿宋_GB2312");
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        titleFont.setFontHeightInPoints((short) 10);
        headerStyle.setFont(titleFont);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setFillForegroundColor((short)13);
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框   
        // 单元格样式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont cellFont = workbook.createFont();
        cellStyle.setFont(cellFont);
        // 生成一个(带标题)表格
        XSSFSheet sheet = workbook.createSheet();
        
        Class<?> c = list.get(0).getClass();
		String className = c.getSimpleName();
		String path = DOWNLOAD_TEMPLATE + File.separator + className + ".xml";
		// 解析XML文件中的信息到一个列表中
		List<Entity> entryList = tool.parseXML(path);
		
		XSSFRow titleRow = sheet.createRow(0);
		for (int j=0;j<entryList.size();j++) {
			Entity entity = entryList.get(j);
            XSSFCell newCell = titleRow.createCell(j);
            newCell.setCellStyle(headerStyle);
            newCell.setCellValue(entity.text);
            sheet.setColumnWidth(j, entity.width * 256);
		}
		
		SimpleDateFormat spDay = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			XSSFRow dataRow = sheet.createRow(i + 1);
			for (int j=0;j<entryList.size();j++) {
				String methodName = MethodTool.returnGetMethodName(entryList.get(j).name);
				Object methodValue = MethodTool.excuteMethod(list.get(i), methodName);
				if (methodValue == null) {
					methodValue = "";
				}
                XSSFCell newCell = dataRow.createCell(j);
                
                String cellValue = ""; 
                if(methodValue instanceof Date) {
                	cellValue = spDay.format(methodValue);
                } else {
                	cellValue = methodValue.toString();
                }
                newCell.setCellValue(cellValue);
                newCell.setCellStyle(cellStyle);
			}
		}
		
        try {
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Web 导出excel
    public static void downloadExcelFile(String title, List<?> list, HttpServletResponse response){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            DataExportUtil.exportExcel(list, os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
//            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
//            response.setContentType("application/binary;charset=utf-8"); 
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((title + ".xlsx").getBytes(), "iso-8859-1"));
            response.setContentLength(content.length);
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);

            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static ScddOrderSearchPage getScddOrderSearchPage() {
    	ScddOrderSearchPage order = new ScddOrderSearchPage();
    	order.setUserName("aaa");
    	order.setMemberName("bbb");
    	return order;    	
    }
    
    public static void main(String[] args) throws Exception {
        OutputStream outXlsx = new FileOutputStream("d://tmp//b.xlsx");
        System.out.println("正在导出xlsx....");
        List<ScddOrderSearchPage> list = new ArrayList<ScddOrderSearchPage>(5);
        list.add(getScddOrderSearchPage());
        list.add(getScddOrderSearchPage());
        list.add(getScddOrderSearchPage());
        list.add(getScddOrderSearchPage());
        list.add(getScddOrderSearchPage());
        DataExportUtil.exportExcel(list,outXlsx);
        outXlsx.close();
    }
}
