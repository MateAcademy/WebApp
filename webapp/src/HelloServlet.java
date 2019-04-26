import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

@WebServlet(name = "HelloServlet", value = "/hello", loadOnStartup = 1) //при преходе на хелло срабатывает метод сервис
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistated userRegistated = new UserRegistated();

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");       //даем понять браузеру что ему приходит не картинка не видео не джейсон
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();  //это мы запрос печатаем на странице

        String firstName = req.getParameter("name");
        String login = req.getParameter("login");

        User alone = new User(firstName, login);


      //  String name = UserRegistated.nameLastUsera();
        boolean reg = UserRegistated.proverka(alone);
        HttpSession session = req.getSession();


        if (reg) {
            out.print("You already have account, just sign in");
        } else {
            session.setAttribute("isRegistreted", firstName);
            userRegistated.addNewUsers(alone);

            resp.setStatus(HttpServletResponse.SC_OK);

            if (req.getMethod().equals("GET")) {
                System.out.println("Мы не поддерживаем работу с методом GET");
                out.print("Мы не поддерживаем работу с методом GET");

            } else {


                String agree = req.getParameter("agree");
                if (agree == null) {
                    agree = "НЕТ";
                }





                out.print("Name " + firstName + "<br>");
                out.print("Пароль " + login + "<br>");
                out.print("Согласен ли ты с политикой обработки данных: " + agree + "<br>");
                out.println("Мой первый servlet, " + "метод: " + req.getMethod() + "<br>");
                out.println("session account: " + session.getAttribute("isRegistreted"));




                session.setMaxInactiveInterval(10);
                userRegistated.getList();
                System.out.println();
            }

        }


    }
}
