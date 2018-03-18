package myapp;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    AllowanceService allowanceService;
    Moshi moshi;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (BufferedSource source = Okio.buffer(Okio.source(req.getInputStream()));
             BufferedSink sink = Okio.buffer(Okio.sink(resp.getOutputStream()))) {

            JsonAdapter<SignUpRequest> requestAdapter = moshi.adapter(SignUpRequest.class);
            SignUpRequest request = requestAdapter.fromJson(source);

            UserId userId = allowanceService.signUp(request.name);

            SignUpResponse response = new SignUpResponse();
            response.userId = userId.userId;

            JsonAdapter<SignUpResponse> responseAdapter = moshi.adapter(SignUpResponse.class);
            responseAdapter.toJson(sink, response);
        }
    }

    // this object is passed by the Android App to the server
    static class SignUpRequest {
        String name;
    }

    static class SignUpResponse {
        String userId;
    }

}

