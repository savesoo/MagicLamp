package com.app.magiclamp.controller.administrator;

import com.app.magiclamp.model.administrator.AdminBookManageDTO;
import com.app.magiclamp.service.administrator.AdminBookManageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class AdminBookManageRestController {

    @Autowired
    private AdminBookManageService adminBookManageService;

    @PutMapping("/administrator/bookstock")
    public ResponseEntity<Integer> updateBookStock(
            @RequestBody AdminBookManageDTO adminBook
    ){
        return new ResponseEntity<>(adminBookManageService.updateBookStock(adminBook.getStock(), adminBook.getIsbn()), HttpStatus.OK);
    }

    @PutMapping("/administrator/milerate")
    public ResponseEntity<Integer> updateBookMilerate(
            @RequestBody AdminBookManageDTO adminBook
    ){
        return new ResponseEntity<>(adminBookManageService.updateBookMilerate(adminBook.getMileagerate(), adminBook.getIsbn()), HttpStatus.OK);
    }

}
