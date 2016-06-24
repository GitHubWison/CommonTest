package com.example.xuqiwei.commontestproject.retrofit;

import java.io.IOException;
import java.util.List;



/**
 * Created by xuqiwei on 16-6-19.
 */
public class SimpleService {
    public static final String API_URL = "https://api.github.com";

    public static class Contributor2 {
        public final String login;
        public final int contributions;

        public Contributor2(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

//    public interface GitHub {
//        @GET("/repos/{owner}/{repo}/contributors")
//        Call<List<Contributor2>> contributors(
//                @Path("owner") String owner,
//                @Path("repo") String repo);
//    }
//
//    public static void main(String... args) throws IOException {
//        // Create a very simple REST adapter which points the GitHub API.
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        // Create an instance of our GitHub API interface.
//        GitHub github = retrofit.create(GitHub.class);
//
//        // Create a call instance for looking up Retrofit contributors.
//        Call<List<Contributor2>> call = github.contributors("square", "retrofit");
//
//        // Fetch and print a list of the contributors to the library.
//        List<Contributor2> contributors = call.execute().body();
//        for (Contributor2 contributor : contributors) {
//            System.out.println(contributor.login + " (" + contributor.contributions + ")");
//        }
//    }
}
