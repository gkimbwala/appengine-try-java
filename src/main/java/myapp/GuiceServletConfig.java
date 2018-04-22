package myapp;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.squareup.moshi.Moshi;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {

            @Override
            protected void configureServlets() {
                serve("/SignUp").with(SignUpServlet.class);
                serve("/AddChild").with(AddChildServlet.class);
                serve("/AddChore").with(AddChoreServlet.class);
                serve("/SubmitChore").with(SubmitChoreServlet.class);
                serve("/ApproveChore").with(ApproveChoreServlet.class);
                serve("/RejectChore").with(RejectChoreServlet.class);
                bind(AllowanceService.class).to(GcdAllowanceService.class);
                bind(Moshi.class).toInstance(new Moshi.Builder().build());
            }
        });
    }

}
