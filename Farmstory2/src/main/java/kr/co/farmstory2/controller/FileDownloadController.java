package kr.co.farmstory2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/fileDownload.do")
public class FileDownloadController extends HttpServlet{
	private static final long serialVersionUID = 3164817008035875483L;
	private FileService fService = FileService.INSTANCE;
	private ArticleService aService = ArticleService.INSTANCE;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fNo = req.getParameter("fNo");
		FileDTO file = fService.selectFileBYfNO(fNo);
		aService.downloadFile(req, resp, file);
	}
}