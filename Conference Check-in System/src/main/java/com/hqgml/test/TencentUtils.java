package com.hqgml.test;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.iai.v20180301.IaiClient;

import com.tencentcloudapi.iai.v20180301.models.CompareFaceRequest;
import com.tencentcloudapi.iai.v20180301.models.CompareFaceResponse;

public class TencentUtils {
    public static void main(String[] args) {

        String image = null;
        String image2 = null;


        try {

            Credential cred = new Credential("AKIDi1GKF6bnY1MWdi5eeWEfDk6OUt74Beb6", "U0BC2mqtodKrqeOutbDgzDJix2wfNwY1");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);

            String params = "{\"ImageA\":\"" + image + "\",\"ImageB\":\"" + image2 + "\"}";
            CompareFaceRequest req = CompareFaceRequest.fromJsonString(params, CompareFaceRequest.class);

            CompareFaceResponse resp = client.CompareFace(req);

            System.out.println(CompareFaceRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}