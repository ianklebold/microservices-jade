package com.microjade.accounts.controller;

import com.microjade.accounts.constants.AccountsConstants;
import com.microjade.accounts.dto.CustomerDto;
import com.microjade.accounts.dto.ResponseDto;
import com.microjade.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/account",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated //Aplica la validacion a todos los endpoints de este controlador
public class AccountsController {

    private IAccountsService iAccountsService;

    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam
                                       @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                       String mobileNumber){
       CustomerDto customerDto = iAccountsService.getAccount(mobileNumber);
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(customerDto);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountsService.updateAccount(customerDto);

        if (isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                       @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                       String mobileNumber){
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }
    }


}
