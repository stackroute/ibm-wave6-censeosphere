package com.stackroute.productOwnerservice.seeddata;


import com.stackroute.productOwnerservice.domain.ProductOwner;
import com.stackroute.productOwnerservice.dto.ProductOwnerDTO;
import com.stackroute.productOwnerservice.repository.ProductOwnerRepository;
import com.stackroute.productOwnerservice.service.ProductOwnerService;
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
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ProductOwnerRepository productOwnerRepository;
    @Autowired
    ProductOwnerService productOwnerService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        try {

            FileInputStream file = new FileInputStream(new File("product-owner-profile.xlsx"));

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

                    ProductOwner productOwner=new ProductOwner();
                    for (int j = 0; j <= sheet.getLeftCol(); j++) {
                        int count =100;

                        productOwner.setName(workbook.getSheetAt(0).getRow(i).getCell(j + 0).toString());
                        productOwner.setEmailId(workbook.getSheetAt(0).getRow(i).getCell(j + 1).toString());
                        productOwner.setReconfirmPassword(workbook.getSheetAt(0).getRow(i).getCell(j + 2).toString());
                        productOwner.setRole(workbook.getSheetAt(0).getRow(i).getCell(j + 3).toString());
                        productOwnerService.saveDetails(productOwner);

//                        System.out.println(productOwner);
//                        ProductOwnerDTO productOwnerDTO = new ProductOwnerDTO();
//                        productOwnerDTO.setEmailId(workbook.getSheetAt(0).getRow(i).getCell(j + 1).toString());
//                        productOwnerDTO.setReconfirmPassword(workbook.getSheetAt(0).getRow(i).getCell(j + 2).toString());
//                        productOwnerDTO.setRole(workbook.getSheetAt(0).getRow(i).getCell(j + 3).toString());
//
//
//                        productOwnerService.sendproductOwnner(productOwnerDTO);
//                        System.out.println(productOwnerDTO);

                    }

                }
            }

            file.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
