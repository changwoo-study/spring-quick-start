<%@ page import="com.springbook.biz.board.impl.BoardDAO" %>
<%@ page import="com.springbook.biz.board.BoardVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%
 	request.setCharacterEncoding("utf-8");
 	String title = request.getParameter("title");
 	String writer = request.getParameter("writer");
 	String content = request.getParameter("content");
 	
 	BoardVO vo = new BoardVO();
 	
 	vo.setTitle(title);
 	vo.setWriter(writer);
 	vo.setContent(content);
 	
 	BoardDAO boardDAO = new BoardDAO();
 	boardDAO.insertBoard(vo);
 	
 	response.sendRedirect("getBoardList.jsp");	
 %>
 