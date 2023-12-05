package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import User.UserDTO;
import Book.BookDTO;

public final class BookDetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Book Details</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"CSS/BookDetailCSS.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        ");
UserDTO currentUser = (UserDTO) session.getAttribute("usersession");
            BookDTO book = null;
            book = (BookDTO) request.getAttribute("object");
        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <header style=\"display:flex\">\t\t\n");
      out.write("            <div>\n");
      out.write("                <a href=\"BookController\"><img class=\"logo\" src=\"Image/Logo.png\" alt=\"Logo\"></a>\n");
      out.write("                <form style=\"float: right;margin-right: 15px\" action=\"BookController\" method=\"get\">\n");
      out.write("                    <select name=\"genre\" style=\"font-size: 100%\"> \n");
      out.write("                        <option value=\"\">Select Genre</option>\n");
      out.write("                        <option>Fantasy</option>\n");
      out.write("                        <option>Thriller</option>\n");
      out.write("                        <option>Romance</option>\n");
      out.write("                        <option>Self-help</option>\n");
      out.write("                        <option>Science Fiction</option>\n");
      out.write("                    </select>\n");
      out.write("                    <input type=\"text\" name=\"searchbar\" placeholder=\"Search\" style=\"font-size: 90%\">\n");
      out.write("                    <input type=\"hidden\" name=\"action\" value=\"search\">\n");
      out.write("                    <input type=\"submit\" value=\"Search\" style=\"font-size: 90%\">      \n");
      out.write("                </form>            \n");
      out.write("            </div>\n");
      out.write("        </header>\n");
      out.write("        <div class=\"top\">\n");
      out.write("            <div style=\"display:inline-block;float: left; margin:20px 30px\">\n");
      out.write("                <img class=\"cover\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.cover}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" alt=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("            </div>\n");
      out.write("            <div class=\"information\">\n");
      out.write("                <strong>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </strong>\n");
      out.write("                <div class=\"score\">\n");
      out.write("                    <p>Score</p>\n");
      out.write("                    <span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.score}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\n");
      out.write("                </div>\n");
      out.write("                ");

                    if (currentUser != null) {
                
      out.write("\n");
      out.write("                <div><form style=\"display:inline\" action=\"BookController\">                       \n");
      out.write("                        <input type=\"hidden\" name=\"bookId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                        ");
String bookmarked;
                            bookmarked = (String) request.getAttribute("bookmarkDisabled");
                            if (bookmarked != null) {
                        
      out.write("\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"Unbookmark\">\n");
      out.write("                        <input type=\"submit\" value=\"Bookmarked\" class=\"bookmarked button1\">\n");
      out.write("                        ");
} else {
      out.write(" \n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"Bookmark\">\n");
      out.write("                        <input type=\"submit\" value=\"Bookmark\" class=\"bookmark button1\">\n");
      out.write("                        ");
}
      out.write("      \n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("                <div ><form style=\"display:inline\" action=\"ReviewController\">\n");
      out.write("                        <input type=\"hidden\" name=\"bookId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("                        <input type=\"submit\" value=\"Review\" class=\"review button1\">      \n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("                <br>\n");
      out.write("                <ul>\n");
      out.write("                    <li>Author: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.author}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</li>\n");
      out.write("                    <li>Genre: <a href=\"BookController?searchbar=&action=search&genre=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.genre}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write('"');
      out.write('>');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.genre}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"line\"></div>\n");
      out.write("            <div class=\"right\">\n");
      out.write("                <strong class=\"synopsis\">Synopsis</strong>\n");
      out.write("                ");
if (currentUser != null) {
                        if (book.getUploader_id() == currentUser.getUserID()) {
                
      out.write("<a href=\"BookController?action=editbook&id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">Edit</a>\n");
      out.write("                ");
}
                }
      out.write("\n");
      out.write("                <p class=\"summary\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.summary}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p></div>\n");
      out.write("        </div>\n");
      out.write("        <details>\n");
      out.write("            <summary class=\"showcontent\">Show content!</summary>\n");
      out.write("            <object data=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.object.content}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"\n");
      out.write("                    width=\"1100\"\n");
      out.write("                    height=\"700\">   \n");
      out.write("            </object>\n");
      out.write("        </details>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
