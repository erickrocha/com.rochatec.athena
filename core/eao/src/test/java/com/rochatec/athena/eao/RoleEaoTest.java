package com.rochatec.athena.eao;

import com.rochatec.athena.eao.impl.RoleEaoImpl;
import com.rochatec.athena.model.Role;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

/**
 * Created by erickpr on 24/11/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleEaoTest {

    private EntityManagerFactory emf;
    private EntityManager entityManager;

    @AfterClass
    public void tearDownClass() throws Exception {
        entityManager.close();
        emf.close(); //close at application end
    }

    @BeforeClass
    public void setUpClass() throws Exception {
        Properties properties = new Properties();
        properties.put("javax.persistence.jdbc.driver", "org.hsqldb.jdbcDriver");
        properties.put("javax.persistence.jdbc.url", "jdbc:hsqldb:mem:.");
        properties.put("javax.persistence.jdbc.user", "sa");
        properties.put("javax.persistence.jdbc.password", "");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.connection.shutdown", "true");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        emf = Persistence.createEntityManagerFactory("AthenaPersistenceTest", properties);
        entityManager = emf.createEntityManager();
    }

    @Test
    public void shouldPersistRole() {
        RoleEaoImpl roleEaoLocal = new RoleEaoImpl();
        roleEaoLocal.setEntityManager(entityManager);

        Role role = new Role();

    }
}
