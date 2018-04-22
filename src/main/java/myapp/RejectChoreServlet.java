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
public class RejectChoreServlet extends HttpServlet{
    @Inject
    AllowanceService allowanceService;
    @Inject
    Moshi moshi;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try (BufferedSource source = Okio.buffer(Okio.source(req.getInputStream()));
             BufferedSink sink = Okio.buffer(Okio.sink(resp.getOutputStream()))){

            JsonAdapter<RejectChoreRequest> requestAdapter = moshi.adapter(RejectChoreServlet.RejectChoreRequest.class);
            RejectChoreServlet.RejectChoreRequest request = requestAdapter.fromJson(source);

            Chore chore = allowanceService.rejectChore(new UserId(request.userId), new ChoreId(request.choreId));

            RejectChoreServlet.RejectChoreResponse response = new RejectChoreServlet.RejectChoreResponse();
            response.chore = chore;

            JsonAdapter<RejectChoreServlet.RejectChoreResponse> responseAdapter = moshi.adapter(RejectChoreServlet.RejectChoreResponse.class);
            responseAdapter.toJson(sink, response);

        }
    }

    static class RejectChoreRequest {
        String userId;
        String choreId;
    }

    static class RejectChoreResponse {
        Chore chore;
    }
}

