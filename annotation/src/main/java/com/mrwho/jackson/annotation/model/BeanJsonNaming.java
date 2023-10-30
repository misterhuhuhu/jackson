package com.mrwho.jackson.annotation.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * <p>JsonNaming注解用于选择序列化中属性的命名策略，覆盖默认值。使用value元素，我们可以指定任何策略，包括自定义策略。</p>
 *
 * <p>除了默认的LOWER_CAMEL_CASE（例如lowerCamelCase）之外，Jackson 库还为我们提供了其他四种内置属性命名策略以方便使用：</p>
 *
 * KEBAB_CASE：名称元素由连字符分隔，例如kebab-case。
 * LOWER_CASE：所有字母均为小写，不带分隔符，例如lowercase。
 * SNAKE_CASE：所有字母均为小写，并用下划线作为名称元素之间的分隔符，例如Snake_case。
 * UPPER_CAMEL_CASE：所有名称元素（包括第一个元素）均以大写字母开头，后跟小写字母，并且没有分隔符，例如UpperCamelCase。
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BeanJsonNaming {
    private int id;
    private String beanName;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getBeanName() {
        return beanName;
    }
    
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
    
    public BeanJsonNaming(int id, String beanName) {
        this.id = id;
        this.beanName = beanName;
    }
}
