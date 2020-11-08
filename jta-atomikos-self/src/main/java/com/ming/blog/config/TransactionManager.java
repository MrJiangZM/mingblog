package com.ming.blog.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

//@Configuration
//@EnableTransactionManagement
public class TransactionManager {

    @Bean("userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

//    @Bean(name = "userTransactionManager", initMethod = "init", destroyMethod = "close")
    @Bean(name = "userTransactionManager")
    public UserTransactionManager atomikosTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean("transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("userTransaction") UserTransaction userTransaction,
                                                         @Qualifier("userTransactionManager") UserTransactionManager transactionManager) {
        return new JtaTransactionManager(userTransaction, transactionManager);
    }

}
