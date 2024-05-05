package com.d2d.grh.grhBackend.service;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public class PhantombusterService {

//    @Autowired
//    private OkHttpClient client;
//
//    public void useApi() throws IOException {
//        FormBody body = new FormBody.Builder()
//                .add("output", "first-result-object")
//                .add("argument.search", "software engineer")
//                .add("argument.sessionCookie", "YOUR LINKEDIN SESSION COOKIE HERE")
//                .build();
//
//        Request request = new Request.Builder()
//                .url("https://api.phantombuster.com/v2/agents/AGENT_ID/launch")
//                .post(body)
//                .addHeader("X-Phantombuster-Key-1", "zG55dFNQa2n2AmdiFm0Pn1qZXkQOCtBNhgIOWFBVNK8")
//                .build();
//
//        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
//    }
}