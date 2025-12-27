package com.zeeyeh.probsolve;

import com.alibaba.druid.pool.DruidDataSource;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisFlexGeneratorTests {

    void generate() {
        try (DruidDataSource dataSource = new DruidDataSource()) {
            dataSource.setUrl("jdbc:mysql://localhost:3306/probsolve?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&allowMultiQueries=true&allowPublicKeyRetrieval=true&useSSL=false&allowLoadLocalInfile=true");
            dataSource.setUsername("probsolve");
            dataSource.setPassword("probsolve123..");
            dataSource.setDriverClassName(Driver.class.getName());

            GlobalConfig globalConfig = new GlobalConfig();

            globalConfig.getPackageConfig()
                    .setBasePackage("com.zeeyeh.probsolve");

            globalConfig.getStrategyConfig()
                    .setTablePrefix("pb_")
                    .setGenerateTable(
                            "pb_announcements",
                            "pb_error_books",
                            "pb_exam_question_relation",
                            "pb_exams",
                            "pb_practice_records",
                            "pb_question_answers",
                            "pb_question_categories",
                            "pb_question_category_relation",
                            "pb_question_options",
                            "pb_question_tag_relation",
                            "pb_question_tags",
                            "pb_questions",
                            "pb_third_party_logins",
                            "pb_user_exam_records",
                            "pb_user_learning_stat",
                            "pb_user_profiles",
                            "pb_user_question_records",
                            "pb_users"
                    );

            globalConfig.enableEntity()
                    .setWithLombok(false)
                    .setJdkVersion(17);

            globalConfig.enableMapper()
                            .setClassSuffix("Mapper")
                            .setSuperClass(BaseMapper.class);

            globalConfig.enableService()
                            .setClassSuffix("Service")
                            .setSuperClass(IService.class);

            globalConfig.enableServiceImpl()
                            .setClassSuffix("ServiceImpl")
                                    .setSuperClass(ServiceImpl.class);

            globalConfig.enableMapperXml()
                            .setFileSuffix("Mapper");

            globalConfig.getJavadocConfig()
                    .setAuthor("Qinloren")
                    .setSince("1.0.0");

            Generator generator = new Generator(dataSource, globalConfig);
            generator.generate();
        }
    }
}
