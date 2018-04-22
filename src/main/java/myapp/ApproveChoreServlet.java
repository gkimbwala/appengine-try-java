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
public class ApproveChoreServlet extends HttpServlet{

        @Inject
        AllowanceService allowanceService;
        @Inject
        Moshi moshi;

        @Override
        public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            try (BufferedSource source = Okio.buffer(Okio.source(req.getInputStream()));
                 BufferedSink sink = Okio.buffer(Okio.sink(resp.getOutputStream()))){

                JsonAdapter<ApproveChoreRequest> requestAdapter = moshi.adapter(ApproveChoreServlet.ApproveChoreRequest.class);
                ApproveChoreServlet.ApproveChoreRequest request = requestAdapter.fromJson(source);

                Chore chore = allowanceService.approveChore(new UserId(request.userId),  new ChoreId(request.choreId));

                ApproveChoreServlet.ApproveChoreResponse response = new ApproveChoreServlet.ApproveChoreResponse();
                response.chore = chore;

                JsonAdapter<ApproveChoreServlet.ApproveChoreResponse> responseAdapter = moshi.adapter(ApproveChoreServlet.ApproveChoreResponse.class);
                responseAdapter.toJson(sink, response);

            }
        }

        static class ApproveChoreRequest {
            String userId;
            String choreId;
        }

        static class ApproveChoreResponse {
            Chore chore;
        }
}
