package net.rim99.demo.account.support.service.repository.help;

import net.rim99.demo.account.support.service.repository.help.impl.PersonRepositoryIml;
import org.mybatis.guice.XMLMyBatisModule;

public class Mybatis {
    public static XMLMyBatisModule getModule() {
        return new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId("development");
                setClassPathResource("mybatis/config.xml");

                bind(PersonRepository.class).to(PersonRepositoryIml.class);
            }
        };
    }
}
