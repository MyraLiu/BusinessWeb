package com.neuedu.ioc;

import com.neuedu.pojo.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // java容器注入 管理product类
    @Bean
    public Category category(){
        Category category = new Category();
        category.setName("戒指");
        return category;
    }

}
