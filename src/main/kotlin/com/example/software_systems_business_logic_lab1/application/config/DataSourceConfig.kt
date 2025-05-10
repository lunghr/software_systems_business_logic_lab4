package com.example.software_systems_business_logic_lab1.application.config

import com.atomikos.jdbc.AtomikosDataSourceBean
import io.github.cdimascio.dotenv.Dotenv
import jakarta.persistence.EntityManagerFactory
import org.postgresql.xa.PGXADataSource
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    private var dotenv = Dotenv.load()
    private var dbUrl = dotenv.get("DB_LOCAL_URL")
//    private var dbUrl = dotenv.get("DB_DOCKER_URL")
    private var dbUser = dotenv.get("DB_USERNAME")
    private var dbPassword = dotenv.get("DB_PASSWORD")

    @Bean
    fun dataSource(): DataSource {
        val xaDataSource = PGXADataSource().apply {
            setURL(dbUrl)
            user = dbUser
            password = dbPassword
        }

        return AtomikosDataSourceBean().apply {
            uniqueResourceName = "postgres"
            this.xaDataSource = xaDataSource
            setPoolSize(10)
        }
    }


    @Bean
    fun entityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        val properties = hashMapOf<String, Any>(
            "jakarta.persistence.transactionType" to "JTA",
//            "hibernate.hbm2ddl.auto" to "update",
            "hibernate.hbm2ddl.auto" to "create-drop",
            "hibernate.dialect" to "org.hibernate.dialect.PostgreSQLDialect"
        )

        return builder
            .dataSource(dataSource())
            .packages(
                "com/example/software_systems_business_logic_lab1/application/models",
                "com/example/software_systems_business_logic_lab1/payment/bank/models",
                "com/example/software_systems_business_logic_lab1/payment/ozon_client/models"
            )
            .persistenceUnit("postgresUnit")
            .properties(properties)
            .build()
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}