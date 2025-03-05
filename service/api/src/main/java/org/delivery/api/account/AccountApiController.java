package org.delivery.api.account;


import lombok.RequiredArgsConstructor;
import org.delivery.db.account.AccountEntity;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
자신과 동일한 패키지 안에 있는 것들만 빈으로 등록하기 때문에 autowired 되지 않음
->  패키지 이름을 똑같이 맞춰주거나, 다른 패키지에 있는 빈들을 따로 등록해주어야 함
-> 여기서는 configuration 사용 (config.jpa)s
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController  {

    private final AccountRepository accountRepository;

    @GetMapping("")
    public void save(){
        var account = AccountEntity.builder().build();
        accountRepository.save(account);
    }

}
