package com.osf.test.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.osf.test.service.PBoardService;
import com.osf.test.service.impl.PBoardServiceImpl;

public class PBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PBoardService pbs = new PBoardServiceImpl();
	private static String savePath = "D:\\study\\workspace\\osf-jsp\\WebContent\\upload";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html);charset=utf-8");
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard/", "");
		PrintWriter pw = response.getWriter();
		request.setCharacterEncoding("utf-8");
		if ("list".equals(uri)) {
			List<Map<String, String>> selectList = pbs.selectPBoardList();
			RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board/list.jsp");
			request.setAttribute("pBoardList", selectList);
			rd.forward(request, response);
			return;
		} else {
			try {
				int pbNum = Integer.parseInt(uri);
				RequestDispatcher rd = request.getRequestDispatcher("/views/photo-board/select.jsp");
				request.setAttribute("pBoard", pbs.selectPBoard(pbNum));
				rd.forward(request, response);
				return; 
			} catch (NumberFormatException e) {
				throw new ServletException("상세조회는 번호조회만 가능합니다.");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.replace("/pboard/", "");
		request.setCharacterEncoding("utf-8");
		if ("insert".equals(uri)) {
			DiskFileItemFactory dfiFactory = new DiskFileItemFactory();
			String tmpPath = System.getProperty("java.io.tmpdir");
			File tmpFile = new File(tmpPath);
			dfiFactory.setRepository(tmpFile);
			dfiFactory.setSizeThreshold(10 * 1024 * 1024);
			ServletFileUpload sfu = new ServletFileUpload(dfiFactory);
			sfu.setSizeMax(20 * 1024 * 1024);
			sfu.setFileSizeMax(20 * 1024 * 1024);
			try {
				List<FileItem> fileList = sfu.parseRequest(request);
				Map<String, String> pBoard = new HashMap<>();
				for (int i = 0; i < fileList.size(); i++) {
					FileItem fi = fileList.get(i);
					if (fi.isFormField()) {
						pBoard.put(fi.getFieldName(), fi.getString("utf-8"));
					} else {
						String rFileName = fi.getName();
						String extName = rFileName.substring(rFileName.lastIndexOf(".") + 1);
						String fileName = System.currentTimeMillis() + "";
						File saveFile = new File(savePath + "\\" + fileName + "." + extName);
						pBoard.put("pb_real_path", "/upload/" + fileName + "." + extName);
						pBoard.put("pb_file_path", rFileName);
						fi.write(saveFile);
					}
				}
				if (pbs.insertPBoard(pBoard) == 1) {
					request.setAttribute("msg", "집중해라!");
					request.setAttribute("url", "/views/photo-board/insert.jsp");
					RequestDispatcher rd = request.getRequestDispatcher("/views/result.jsp");
					rd.forward(request, response);
					return;
				} else {

				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("update".equals(uri)) {

		} else if ("delete".equals(uri)) {

		} else {

		}

	}
}
