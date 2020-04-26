package com.agency.mapper;
import com.agency.dto.AccountDto;
import com.agency.entity.Account;
import com.agency.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class AccountMapper extends AbstractMapper<Account, AccountDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountMapper(ModelMapper mapper, AccountRepository accountRepository) {
        super(Account.class, AccountDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void setupMapper() {

        mapper.createTypeMap(Account.class, AccountDto.class)
                .addMappings(m -> m.skip(AccountDto::setRole)).setPostConverter(toDtoConverter());

    }

    @Override
    public void mapSpecificFields(Account source, AccountDto destination) {
        destination.setRole(getRole(source));
    }

    private String getRole(Account source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getRoleSet().iterator().next().getRole().name();
    }


    @Override
    void mapSpecificFields(AccountDto source, Account destination) {

    }
}

