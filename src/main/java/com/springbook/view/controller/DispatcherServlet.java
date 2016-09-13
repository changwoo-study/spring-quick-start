package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.user.impl.UserDAO;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private HandlerMapping handlerMapping;
	private ViewResolver   viewResolver;

	@Override
	public void init() throws ServletException {

		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();

		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));

		Controller ctrl = handlerMapping.getController(path);

		String viewName = ctrl.handleRequest(request, response);

		String view = null;
		if( !viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
		} else {
			view = viewName;
		}

		response.sendRedirect(view);
	}
}