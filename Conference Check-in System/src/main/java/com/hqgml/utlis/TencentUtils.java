package com.hqgml.utlis;

import com.hqgml.domain.TencentString;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.iai.v20180301.IaiClient;
import com.tencentcloudapi.iai.v20180301.models.*;

import java.io.UnsupportedEncodingException;

public class TencentUtils {
    //返回的都是腾讯云的数据对象 里面存放错误信息 或者成功信息

    /**
     * @param image  base64编码
     * @param image2 base64编码2
     * @return
     */
    public static TencentString CompareFace(String image, String image2) {
        TencentString tencentString = new TencentString();
        String secreid = "";
        String secretKey = "";
        try {
            Credential cred = new Credential(secreid, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);

            String params = "{\"ImageA\":\"" + image + "\",\"ImageB\":\"" + image2 + "\"}";
            CompareFaceRequest req = CompareFaceRequest.fromJsonString(params, CompareFaceRequest.class);

            CompareFaceResponse resp = client.CompareFace(req);
            System.out.println(CompareFaceRequest.toJsonString(resp));

            tencentString.setSuccess(CompareFaceRequest.toJsonString(resp));
            return tencentString;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            tencentString.setError(e.toString());
            return tencentString;
        }

    }

    /**
     * @param GroupName 新建的用户的会议组的名字（唯一）
     * @param GroupId   新建会议的id（唯一）
     * @return
     */
    public static TencentString CreateGroup(String GroupName, String GroupId) {
        TencentString tencentString = new TencentString();
        String secreid = "";
        String secretKey = "";
        try {
            Credential cred = new Credential(secreid, secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);

            String params = "{\"GroupName\":\"" + GroupName + "\",\"GroupId\":\"" + GroupId + "\"}";

            CreateGroupRequest req = CreateGroupRequest.fromJsonString(params, CreateGroupRequest.class);
            CreateGroupResponse resp = client.CreateGroup(req);

            System.out.println(CreateGroupRequest.toJsonString(resp));
            tencentString.setSuccess(CreateGroupRequest.toJsonString(resp));
            return tencentString;

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            tencentString.setError(e.toString());
            return tencentString;
        }

    }

    /**
     * 删除用户组 用于删除会议的
     *
     * @param GroupId
     * @return
     */
    public static TencentString DeleteGroup(String GroupId) {
        TencentString tencentString = new TencentString();
        String secreid = "";
        String secretKey = "";
        try {
            Credential cred = new Credential(secreid, secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);

            String params = "{\"GroupId\":\"" + GroupId + "\"}";
            DeleteGroupRequest req = DeleteGroupRequest.fromJsonString(params, DeleteGroupRequest.class);

            DeleteGroupResponse resp = client.DeleteGroup(req);

            System.out.println(CreateGroupRequest.toJsonString(resp));
            tencentString.setSuccess(CreateGroupRequest.toJsonString(resp));
            return tencentString;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            tencentString.setError(e.toString());
            return tencentString;
        }

    }

    /**
     * 新增用户脸
     */
    public static TencentString CreatePerson(String GroupId, String PersonName, String PersonId, String Image) {
        TencentString tencentString = new TencentString();
        String secreid = "";
        String secretKey = "";
        try {
            Credential cred = new Credential(secreid, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);
           //防止中文乱码 准换为url编码
            String PersonNameutf = UrlUtils.encode(PersonName, "utf-8");
            String params = "{\"GroupId\":\"" + GroupId + "\",\"PersonName\":\"" + PersonNameutf + "\",\"PersonId\":\"" + PersonId + "\",\"Image\":\"" + Image + "\"}";

            CreatePersonRequest req = CreatePersonRequest.fromJsonString(params, CreatePersonRequest.class);
            CreatePersonResponse resp = client.CreatePerson(req);
            tencentString.setSuccess(CreateGroupRequest.toJsonString(resp));
            return tencentString;
        } catch (TencentCloudSDKException | UnsupportedEncodingException e) {
            System.out.println("异常是" + e.toString());
            tencentString.setError(e.toString());
            return tencentString;
        }

    }

    /**
     * 搜寻用户通过用户组
     *
     * @param GroupId
     * @param Image
     * @return
     */
    public static TencentString SearchFaces(String GroupId, String Image) {
        TencentString tencentString = new TencentString();
        String secreid = "";
        String secretKey = "";
        String NeedPersonInfo = "1";//是否返回人员信息，1返回 0不返回
        try {
            Credential cred = new Credential(secreid, secretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("iai.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);

            String params = "{\"GroupIds\":[\"" + GroupId + "\"],\"Image\":\"" + Image + "\",\"NeedPersonInfo\":" + NeedPersonInfo + ",\"MaxPersonNum\":1}";
            SearchFacesRequest req = SearchFacesRequest.fromJsonString(params, SearchFacesRequest.class);

            SearchFacesResponse resp = client.SearchFaces(req);
            Result[] results = resp.getResults();
            tencentString.setResult(results);
//            String s = results.toString();
//            System.out.println("结果的数组为"+s);
//            System.out.println(CreateGroupRequest.toJsonString(resp));
            tencentString.setSuccess(CreateGroupRequest.toJsonString(resp));
            return tencentString;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            tencentString.setError(e.toString());
            return tencentString;

        }
    }

}
