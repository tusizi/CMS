package cn.com.leadfar.cms.backend.view;

import org.flywaydb.core.Flyway;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created by tusizi on 2015/11/17.
 */
@WebServlet(name = "InitMigrationServlet")
public class InitMigrationServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
        flyway.setDataSource("jdbc:h2:~/cms", "sa", null);

        // Start the migration
        flyway.migrate();
    }
}
