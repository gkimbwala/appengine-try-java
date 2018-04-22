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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class AddChoreServlet extends HttpServlet {
    @Inject
    AllowanceService allowanceService;
    @Inject
    Moshi moshi;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (BufferedSource source = Okio.buffer(Okio.source(req.getInputStream()));
             BufferedSink sink = Okio.buffer(Okio.sink(resp.getOutputStream()))) {

            JsonAdapter<AddChoreRequest> requestAdapter = moshi.adapter(AddChoreRequest.class);
            AddChoreRequest request = requestAdapter.fromJson(source);

            Chore chore = allowanceService.addChore(new UserId(request.userId), request.name, request.description, request.rate);

            AddChoreResponse response = new AddChoreResponse();
            response.chore = chore;

            JsonAdapter<AddChoreResponse> responseAdapter = moshi.adapter(AddChoreResponse.class);
            responseAdapter.toJson(sink, response);
        }
    }


    static class AddChoreRequest {
        String userId;
        String name;
        String description;
        float rate;
    }

    static class AddChoreResponse {
        Chore chore;
    }
}
