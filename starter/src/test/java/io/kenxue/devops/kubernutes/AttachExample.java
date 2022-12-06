/*
Copyright 2020 The Kubernetes Authors.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package io.kenxue.devops.kubernutes;

import com.alibaba.fastjson.JSONObject;
import io.kubernetes.client.Attach;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;

import java.io.*;

/**
 * A simple example of how to use the Java API
 *
 * <p>Easiest way to run this: mvn exec:java
 * -Dexec.mainClass="io.kubernetes.client.examples.AttachExample"
 *
 * <p>From inside $REPO_DIR/examples
 */
public class AttachExample {
    public static void main(String[] args) throws IOException, ApiException, InterruptedException {
        String kubeConfigPath = "/Users/biaoyang/Downloads/config";
        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
        Configuration.setDefaultApiClient(client);

        CoreV1Api coreApi = new CoreV1Api(client);
        V1Pod v1Pod = coreApi.readNamespacedPod("minio-959c68959-cnqf4", "minio", null, null, null);
        String s = JSONObject.toJSONString(v1Pod);
        System.out.println(s);
        Attach attach = new Attach();
        final Attach.AttachResult result = attach.attach("minio", "minio-959c68959-cnqf4", true);

//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        OutputStream output = result.getStandardInputStream();
//
//        try {
//            while (true) {
//                String line = in.readLine();
//                output.write(line.getBytes());
//                output.write('\n');
//                output.flush();
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

        new Thread(
                () -> {
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    OutputStream output = result.getStandardInputStream();
                    try {
                        while (true) {
                            String line = in.readLine();
                            output.write(line.getBytes());
                            output.write('\n');
                            output.flush();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                })
                .start();

        new Thread(
                () -> {
                    try {
                        copy(result.getStandardOutputStream(), System.out);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                })
                .start();

        Thread.sleep(10 * 1000);
        result.close();
        System.exit(0);
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.flush();
    }
}
