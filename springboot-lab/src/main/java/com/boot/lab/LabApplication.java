package com.boot.lab;

import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by chenluo on 2017/2/8.
 */
@SpringBootApplication
@RestController
public class LabApplication {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String [] args){
        SpringApplication.run(LabApplication.class,args);
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }

    @RequestMapping("/excel")
    public void excel(HttpServletRequest request, HttpServletResponse response){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        createTitle(workbook, sheet);

        //设置日期格式
        HSSFCellStyle style=workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        for (int i=0;i<4;i++) {

            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(i+1);
            row.createCell(1).setCellValue(i+1);
            row.createCell(2).setCellValue(i+1);
            HSSFCell cell = row.createCell(3);
            cell.setCellValue(dateFormat.format(new Date()));
            cell.setCellStyle(style);
            rowNum++;
        }

        //拼装blobName
        String fileName = "测试数据统计表.xlsx";
        String dateTime = dateFormat.format(new Date());
        String blobName =  dateTime + "/" + UUID.randomUUID().toString().replaceAll("-", "") + "/" + fileName;

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=test.xls");




        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(response.getOutputStream());
//            response.setContentLengthLong(out.size());




        } catch (Exception e)
        {
            log.error("big error is : {}",e);
        }

    }

    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet)
    {
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(2, 12*256);
        sheet.setColumnWidth(3, 17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("序号");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("金额");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("描述");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("日期");
        cell.setCellStyle(style);
    }
}
