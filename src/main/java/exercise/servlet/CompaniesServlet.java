package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter pw = response.getWriter();

        String searchValue = request.getParameter("search");
        if (searchValue == null || searchValue.equals("")) {
            printCompanies(getCompanies(), response);
        } else {
            List<String> names = getCompanies().stream().filter(name -> name.contains(searchValue)).toList();
            printCompanies(names, response);
        }
        // END
    }

    private void printCompanies(List<String> names, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        if (names.size() > 0) {
            names.forEach(pw::println);
        } else {
            pw.println("Companies not found");
        }
    }
}
