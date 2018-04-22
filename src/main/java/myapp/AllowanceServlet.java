/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package myapp;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okhttp3.HttpUrl;

import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Singleton
public class AllowanceServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest reqest, HttpServletResponse response) throws IOException {
    HttpUrl url = HttpUrl.parse(reqest.getRequestURL().toString());
    String name = url.pathSegments().get(1);

    response.setContentType("text/plain");

    Moshi moshi = new Moshi.Builder().build();
    JsonAdapter<AllowanceRate> jsonAdapter = moshi.adapter(AllowanceRate.class);


    AllowanceRate allowanceRate = new AllowanceRate();
    allowanceRate.name = name.toUpperCase();
    allowanceRate.allowance = 500;

    // TODO: hook up Guice or dagger or something so we don't need this gross hack.
    List<String> chores = FakeAllowanceDb.INSTANCE.getAllChores(name);
    allowanceRate.chores = chores;
    String json = jsonAdapter.toJson(allowanceRate);

    response.getWriter().println(json);
  }

  static class AllowanceRate {
    String name;
    long allowance;
    List<String> chores;
  }
}
