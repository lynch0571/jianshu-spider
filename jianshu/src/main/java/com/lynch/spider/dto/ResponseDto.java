/**
 * 
 */
package com.lynch.spider.dto;

import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @author Lynch
 * @date 2017年3月9日
 */
@Component
public class ResponseDto {
    private String result;
    private double time;
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public double getTime() {
        return time;
    }
    public void setTime(double time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "ResponseDto [result=" + result + ", time=" + time + "]";
    }
    
}
