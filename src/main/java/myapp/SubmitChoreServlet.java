package myapp;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Singleton
public class SubmitChoreServlet extends HttpServlet {
    @Inject
    AllowanceService allowanceService;
    @Inject
    Moshi moshi;

    @Override
     public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try (BufferedSource source = Okio.buffer(Okio.source(req.getInputStream()));
        BufferedSink sink = Okio.buffer(Okio.sink(resp.getOutputStream()))){

            JsonAdapter<SubmitChoreRequest> requestAdapter = moshi.adapter(SubmitChoreServlet.SubmitChoreRequest.class);
            SubmitChoreServlet.SubmitChoreRequest request = requestAdapter.fromJson(source);

            Chore chore = allowanceService.submitChore(new UserId(request.userId), new ChoreId(request.choreId));

            SubmitChoreServlet.SubmitChoreResponse response = new SubmitChoreServlet.SubmitChoreResponse();
            response.chore = chore;

            JsonAdapter<SubmitChoreServlet.SubmitChoreResponse> responseAdapter = moshi.adapter(SubmitChoreServlet.SubmitChoreResponse.class);
            responseAdapter.toJson(sink, response);

        }
    }

    static class SubmitChoreRequest {
        String userId;
        String choreId;
    }

    static class SubmitChoreResponse {
        Chore chore;
    }
}
