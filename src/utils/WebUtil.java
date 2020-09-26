package utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	private WebUtil() {

	}

	public static void printSuccMsg(HttpServletResponse response, String message, String succURL) throws Exception {
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");

		out.println("window.alert('" + message + "');");
		out.println("window.location.href = '" + succURL + "';");
		out.println("</script>");
	}

	public static void printErrorMsg(HttpServletResponse response, String message) {
		printErrorMsg(response, message, null);
	}

	public static void printErrorMsg(HttpServletResponse response, String message, String errorURL) {
		try {
			PrintWriter out = response.getWriter();

			out.println("<script type='text/javascript'>");

			out.println("window.alert(\"" + message + "\");");
			if (errorURL == null || errorURL.equalsIgnoreCase("")) {
				out.println("window.history.back();");
			} else {
				out.println("window.location.href = '" + errorURL + "';");
			}
			out.println("</script>");
		} catch (Exception e) {
		}

	}
}
