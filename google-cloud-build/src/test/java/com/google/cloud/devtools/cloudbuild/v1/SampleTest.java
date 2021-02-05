/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.devtools.cloudbuild.v1;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.devtools.cloudbuild.v1.CloudBuildClient.ListBuildTriggersPagedResponse;
import com.google.cloudbuild.v1.BuildTrigger;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class SampleTest {
  public CloudBuildSettings createADCSetting() throws Exception {
    return CloudBuildSettings.newBuilder().build();
  }

  public CloudBuildSettings createFixedCredsSetting() throws Exception {
    GoogleCredentials creds = ServiceAccountCredentials.getApplicationDefault();
    CredentialsProvider provider = FixedCredentialsProvider.create(creds);
    return CloudBuildSettings.newBuilder().setCredentialsProvider(provider).build();
  }

  public CloudBuildSettings createCustomScopeSetting() throws Exception {
    List<String> scopes = new ArrayList<>();
    scopes.add("https://www.googleapis.com/auth/cloud-platform");
    CredentialsProvider provider = GoogleCredentialsProvider.newBuilder().setScopesToApply(scopes).build();
    return CloudBuildSettings.newBuilder().setCredentialsProvider(provider).build();
  }

  @Test
  public void myTest() throws Exception {
    // CloudBuildSettings settings = createADCSetting();
    // CloudBuildSettings settings = createFixedCredsSetting();
    CloudBuildSettings settings = createCustomScopeSetting();

    CloudBuildClient client = CloudBuildClient.create(settings);
    String project = "dcatest-281318";

    ListBuildTriggersPagedResponse resp = client.listBuildTriggers(project);
    List<BuildTrigger> resources = Lists.newArrayList(resp.iterateAll());
    System.out.println(resources);
  }
}
