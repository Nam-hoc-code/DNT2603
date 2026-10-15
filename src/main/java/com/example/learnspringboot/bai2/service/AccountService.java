package com.example.learnspringboot.bai2.service;

import com.example.learnspringboot.bai2.dto.AccountDTO;
import com.example.learnspringboot.bai2.entity.Account;
import com.example.learnspringboot.bai2.dto.request.AccoutnDTOResquest;


import com.example.learnspringboot.bai2.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;

    private final ModelMapper modelMapper;


    public AccountService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> getById(Integer id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> getByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account create(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            throw new RuntimeException("Username already exists: " + account.getUsername());
        }
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RuntimeException("Email already exists: " + account.getEmail());
        }
        return accountRepository.save(account);
    }

    public Account update(Integer id, Account updated) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found: " + id));
        account.setEmail(updated.getEmail());
        account.setFullName(updated.getFullName());
        account.setDepartment(updated.getDepartment());
        account.setPosition(updated.getPosition());
        account.setStatus(updated.getStatus());
        if (updated.getPassword() != null && !updated.getPassword().isEmpty()) {
            account.setPassword(updated.getPassword());
        }
        return accountRepository.save(account);
    }

    public void delete(Integer id) {
        accountRepository.deleteById(id);
    }

    public List<Account> getByDepartmentId(Integer deptId) {
        return accountRepository.findByDepartmentId(deptId);
    }

    public List<Account> getByPositionId(Integer posId) {
        return accountRepository.findByPositionId(posId);
    }


    public List<AccountDTO> findAllAccounts() {
        List<AccountDTO> accountDTOList = new ArrayList<>();
        ArrayList<Account> accountArrayList = (ArrayList<Account>) this.getAll();

        for (Account account : accountArrayList) {
//            Cách 1 : Không dùng ModelMapper
//            AccountDTO accountDTO = new AccountDTO();
//            accountDTO.setUsername(account.getUsername());
//            accountDTO.setEmail(account.getEmail());
//            accountDTOList.add(accountDTO);
//            Cách 2 : Dùng ModelMapper ( sẽ có những case không modelMapper được như :
//            tính tổng của hai thuộc tính trước đó, nối hai thuộc tính lại firstName + lastName
//            cơ chế thực hiện mapping theo tên -> ko phải khóa ngoại - map theo đúng tên
//                                              -> là khóa ngoại - vd : departmentName : -> department / Name
//                                              tách tên -> tìm class Department -> tìm trường có tên Name


            AccountDTO accountDTO = new AccountDTO();
            accountDTO = modelMapper.map(accountDTO, AccountDTO.class);


        }
        return accountDTOList;
    }

    public void create(AccoutnDTOResquest accoutnDTOResquest) {
        Account account = modelMapper.map(accoutnDTOResquest, Account.class);
        accountRepository.save(account);
    }

    public Boolean update(Integer id, AccoutnDTOResquest accoutnDTOResquest) {
        if (accountRepository.existsById(id)) {
          Account account =  accountRepository.findById(id).get();
          account.setEmail(   accoutnDTOResquest.getEmail());
        }
        return null;
    }
}
