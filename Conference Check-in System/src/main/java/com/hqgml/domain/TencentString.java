package com.hqgml.domain;

import com.tencentcloudapi.iai.v20180301.models.Result;
import lombok.Data;

@Data
public class TencentString {
   private String success;
   private String error;
   private Result[] Result;//人脸识别结果集
}
