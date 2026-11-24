package helloboot;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(
				// 1. ServletContextInitializer은 함수형 인터페이스(메서드를 1개 가지는)이기에 메서드 구현말고 람다형식으로 구현 가능
				servletContext -> {
					servletContext.addServlet("hello", new HttpServlet() {
						// 2. service 메서드는 요청, 응답 값을 파라미터로 받아서 전달되서 이 두가지를 이용하면 웹 기능을 만들 수 있음
						@Override
						protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
							resp.setStatus(200);
							resp.setHeader("Content-Type","text/plain");
							resp.getWriter().println("Hello Servlet");
						}
					}).addMapping("/hello"); // 서블릿과 매핑할 경로
        });
		webServer.start();
	}

}

