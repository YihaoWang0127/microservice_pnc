package com.account.AccountApplication.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.AccountApplication.entity.Account;
import com.account.AccountApplication.entity.JsendAccount;
import com.account.AccountApplication.entity.JsendUser;
import com.account.AccountApplication.service.AccountService;
import com.account.AccountApplication.service.UserService;

@RestController
@RequestMapping("/api")
public class AccountRestController {
	
	@Autowired
	private UserService userService;
	
	private AccountService accountService;
	
	@Autowired
	public AccountRestController(AccountService theAccountService) {
		accountService = theAccountService;
	}
	
	@GetMapping("/accounts")
	public List<JsendAccount> findAll(){
		
		List<Account> accounts = accountService.findAll();
		
		List<JsendAccount> jsendAccounts =new LinkedList<>();
		
		for(Account a:accounts) {
			
			List<Account> theAccount = new LinkedList<>();
			
			theAccount.add(a);
			
			JsendAccount ja = new JsendAccount("Success", "The Account is found", theAccount);
			
			jsendAccounts.add(ja);

		}
		
		return jsendAccounts;
		
	}

	@GetMapping("/account/{theUserId}")
	public JsendAccount getAccountByUserId(@PathVariable int theUserId){
		
		List<Account> theAccounts = accountService.findByUserId(theUserId);
		
		JsendAccount jsendAccount = new JsendAccount();
		
		if(theAccounts==null || theAccounts.size()==0) {
			//throw new AccountNotFoundException("Account with userId not found - " + theUserId);
			theAccounts = new LinkedList<>();
			jsendAccount.setStatus("Error");
			jsendAccount.setMessage("No account exists under the User with ID: " + theUserId);
			jsendAccount.setData(theAccounts);
			
		}else {
			
			jsendAccount = new JsendAccount("Success", "The accounts are found", theAccounts);
			
		}
		
		return jsendAccount;
	}
	
	@PostMapping("/accounts")
	public JsendAccount updateEmployee(@RequestBody Account theAccount) {
		
		JsendUser theJsendUser = userService.findById(theAccount.getUserId());
		
		JsendAccount jsendAccount = new JsendAccount();
		
		List<Account> theAccounts = new LinkedList<>();
		
		
		
		if(theJsendUser.getStatus().equals("Error")) {
			
			jsendAccount.setStatus("Error");
			jsendAccount.setMessage("The user that you want to add account to doesn't exsit");
			jsendAccount.setData(theAccounts);
			
		}else {
			
			theAccounts = accountService.findByUserId(theAccount.getUserId());
			
			theAccounts.add(theAccount);
			
			accountService.saveAccount(theAccount);
			
			jsendAccount.setStatus("Success");
			jsendAccount.setMessage("The account has been added successfully");
			jsendAccount.setData(theAccounts);
			
		}
		
		
		return jsendAccount;
		
	}
	
}
