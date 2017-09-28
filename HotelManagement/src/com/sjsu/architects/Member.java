package com.sjsu.architects;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Member extends Person implements IPromotionObserver {

    public Member(){

    }

    //*start observer pattern
    public Member(PromotionController promotion){

        promotion.subscribe(this);
    }

    // Use Constructor or a subscribe method to attach to Observe
    public void subscribeToPromotionCode(PromotionController promotion){

        promotion.subscribe(this);
    }


    @Override
    public void update() {
        System.out.println("Members "+super.getName()+ " received promotion code!!");
    }

    //*end observer pattern

}
