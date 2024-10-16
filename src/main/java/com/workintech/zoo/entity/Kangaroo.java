package com.workintech.zoo.entity;

import lombok.*;

import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
@Getter
public class Kangaroo {
    private int id;
    private String name;
    private double height;
    private double weight;

    private String gender;
    private boolean isAggressive;



    public void setIsAggressive(boolean aggressive) {
        isAggressive = aggressive;
    }

    public boolean getIsAggressive() {
        return isAggressive;
    }
}
