package com.stackroute.review.seeddata;


import com.stackroute.review.domain.Review;
import com.stackroute.review.dto.RecommendationDTO;
import com.stackroute.review.dto.ReviewDTO;
import com.stackroute.review.repository.ReviewRepository;
import com.stackroute.review.service.ReviewService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;


@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        try {

            FileInputStream file = new FileInputStream(new File("reviews.xlsx"));

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

                    Review review=new Review();

                     for (int j = 0; j <= sheet.getLeftCol(); j++) {
                        int count =100;

review.setReviewerEmail(workbook.getSheetAt(0).getRow(i).getCell(j + 0).toString());
review.setReviewTitle(workbook.getSheetAt(0).getRow(i).getCell(j + 1).toString());
review.setReviewDescription(workbook.getSheetAt(0).getRow(i).getCell(j + 2).toString());
review.setProductName(workbook.getSheetAt(0).getRow(i).getCell(j + 3).toString());
review.setPrice(new Float(workbook.getSheetAt(0).getRow(i).getCell(j + 4).getRawValue()));
                         Date date=new Date();
                         long millies=date.getTime();
                         Timestamp timestamp=new Timestamp(millies);
                         review.setReviewedOn(timestamp);

review.setSubCategory(workbook.getSheetAt(0).getRow(i).getCell(j + 5).toString());
review.setCreditpoints(new Integer(workbook.getSheetAt(0).getRow(i).getCell(j + 6).getRawValue()));

                         reviewService.addReview(review);

//

                     }

                }
            }

            file.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
