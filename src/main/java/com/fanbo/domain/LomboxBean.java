package com.fanbo.domain;

import lombok.*;

@Data
@ToString
public class LomboxBean {

    private Integer id;
    @NonNull  //不用在代码中进行null的检查
    private String name;
    @Setter@Getter  //自动拥有set和get方法，也可以写在类上面，代表所有属性都拥有set和get
    private String description;


}
