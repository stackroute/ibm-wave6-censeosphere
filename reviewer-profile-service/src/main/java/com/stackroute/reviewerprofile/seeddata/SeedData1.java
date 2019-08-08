package com.stackroute.reviewerprofile.seeddata;

import  com.stackroute.reviewerprofile.domain.Reviewer;
import com.stackroute.reviewerprofile.domain.Reviewer;
import com.stackroute.reviewerprofile.dto.ReviewerDTO;
import com.stackroute.reviewerprofile.repository.ReviewerRepository;
import com.stackroute.reviewerprofile.service.ReviewerService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
@Component
public class SeedData1 implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    ReviewerRepository reviewerRepository;
    @Autowired
    ReviewerService reviewerService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            FileInputStream file = new FileInputStream(new File("reviewer-image.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            // Traversing over each row of XLSX file
//            rowIterator.next();//Skipping 1st line
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                for (int i = 1; cellIterator.hasNext(); i++) {
                    Reviewer reviewer=new Reviewer();
                    for (int j = 0; j <= sheet.getLeftCol(); j++) {
                        int count =100;
                        reviewer.setName(workbook.getSheetAt(0).getRow(i).getCell(j + 0).toString());
                        reviewer.setEmailId(workbook.getSheetAt(0).getRow(i).getCell(j + 1).toString());
                        reviewer.setReconfirmPassword(workbook.getSheetAt(0).getRow(i).getCell(j + 2).toString());
                        reviewer.setRole(workbook.getSheetAt(0).getRow(i).getCell(j + 3).toString());
                        reviewer.setImage(workbook.getSheetAt(0).getRow(i).getCell(j + 4).toString());
                        reviewerService.saveReviewers(reviewer);
                        System.out.println(reviewer);
                    }
                }
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}