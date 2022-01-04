package com.ping;

import java.util.ArrayList;
public class text {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        ArrayList<Integer> arrayList = new ArrayList<>();
        int zero = 0;
        for (int value : nums) {
            if (value != 0) arrayList.add(value);
        }
        for (zero = nums.length - arrayList.size(); zero > 0; zero--) {
            arrayList.add(0);
        }
        for(int i = 0 ; i < arrayList.size() ; i++){
            nums[i] = arrayList.get(i);
        }
    }
}
