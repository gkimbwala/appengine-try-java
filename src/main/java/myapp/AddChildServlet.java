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
public class AddChildServlet extends HttpServlet {
    @Inject
    AllowanceService allowanceService;
    @Inject
    Moshi moshi;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (BufferedSource source = Okio.buffer(Okio.source(req.getInputStream()));
             BufferedSink sink = Okio.buffer(Okio.sink(resp.getOutputStream()))) {

            JsonAdapter<AddChildRequest> requestAdapter = moshi.adapter(AddChildRequest.class);
            AddChildRequest request = requestAdapter.fromJson(source);

            ChildId childId = allowanceService.addChild(new UserId(request.userId), request.name);

            AddChildResponse response = new AddChildResponse();
            response.childId = childId.childId;

            JsonAdapter<AddChildResponse> responseAdapter = moshi.adapter(AddChildResponse.class);
            responseAdapter.toJson(sink, response);
        }
    }

    static class AddChildRequest {
        String userId;
        String name;
    }

    static class AddChildResponse {
        String childId;
    }
}
