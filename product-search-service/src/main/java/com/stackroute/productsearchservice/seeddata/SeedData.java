package com.stackroute.productsearchservice.seeddata;



import com.stackroute.productsearchservice.domain.ProductDetails;
import com.stackroute.productsearchservice.dto.ProductDTO;
import com.stackroute.productsearchservice.repository.ProductSearchRepository;
import com.stackroute.productsearchservice.service.ProductSearchService;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ProductSearchRepository productSearchRepository;

    @Autowired
    ProductSearchService productSearchService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        try {

            FileInputStream file = new FileInputStream(new File("product-search.xlsx"));

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

                    ProductDetails productDetails=new ProductDetails();


                    for (int j = 0; j <= sheet.getLeftCol(); j++) {
                        int count =100;

                        productDetails.setAddedby(workbook.getSheetAt(0).getRow(i).getCell(j + 0).toString());
                        productDetails.setCategory(workbook.getSheetAt(0).getRow(i).getCell(j + 1).toString());
                        productDetails.setSubCategory(workbook.getSheetAt(0).getRow(i).getCell(j + 2).toString());
                        productDetails.setProductName(workbook.getSheetAt(0).getRow(i).getCell(j + 3).toString());
                        productDetails.setProductFamily(workbook.getSheetAt(0).getRow(i).getCell(j + 4).toString());
                        productDetails.setImage(workbook.getSheetAt(0).getRow(i).getCell(j + 5).toString());
                        productDetails.setPrice(new Float(workbook.getSheetAt(0).getRow(i).getCell(j + 6).getRawValue()));
                        productDetails.setSpecifications(workbook.getSheetAt(0).getRow(i).getCell(j + 7).toString());
                        productDetails.setDescription(workbook.getSheetAt(0).getRow(i).getCell(j +8).toString());

                        Date date=new Date();
                        long millies=date.getTime();
                        Timestamp timestamp=new Timestamp(millies);
                        productDetails.setUploadedOn(timestamp);

                        productSearchService.saveProduct(productDetails);

//                        productSearchService.sendProduct(productDetails);
//                        productSearchService.sendToSearch(productDetails);
//
//
//                        ProductDTO productDTO=new ProductDTO();
//                        productDTO.setProductName(workbook.getSheetAt(0).getRow(i).getCell(j + 3).toString());
//                        productDTO.setRating(0);
//                        productDTO.setPrice(new Float(workbook.getSheetAt(0).getRow(i).getCell(j + 6).getRawValue()));
//                        productDTO.setProductFamily(workbook.getSheetAt(0).getRow(i).getCell(j + 4).toString());
//                        productDTO.setSubCategory(workbook.getSheetAt(0).getRow(i).getCell(j + 2).toString());
//                        productSearchService.sendToRecommendation(productDTO);

                        System.out.println(productDetails);



                    }

                }
            }

            file.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}




