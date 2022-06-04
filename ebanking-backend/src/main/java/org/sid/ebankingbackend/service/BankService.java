package org.sid.ebankingbackend.service;

import org.sid.ebankingbackend.entities.BankAccount;
import org.sid.ebankingbackend.entities.CurrentAccount;
import org.sid.ebankingbackend.entities.SavingAccount;
import org.sid.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public void consulter(){
        BankAccount bankAccount1 = bankAccountRepository.findById("0299a5b3-56c9-421c-b63c-08abc2ff7d68").orElse(null);
        if (bankAccount1!=null){
            System.out.println("*****************************");
            System.out.println(bankAccount1.getId());
            System.out.println(bankAccount1.getBalance());
            System.out.println(bankAccount1.getStatus());
            System.out.println(bankAccount1.getCreatedAt());
            System.out.println(bankAccount1.getCustomer().getName());
            if (bankAccount1 instanceof CurrentAccount){
                System.out.println("Over Draft "+((CurrentAccount)bankAccount1).getOverDraft());
            }else if (bankAccount1 instanceof SavingAccount){
                System.out.println("Rate "+((SavingAccount)bankAccount1).getInterestRate());
            }

            bankAccount1.getAccountOperations().forEach(operation -> {
                System.out.println("**************");
                System.out.println(operation.getType()+"\t"+ operation.getOperationDate()+"\t"+ operation.getAmount());
            });
        }
    }
}
