package com.oracle.servlet.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.dao.Userdao;
import com.oracle.entity.Shop_User;
import com.oracle.service.UserService;

/**
 * Servlet implementation class UserShow
 */
@WebServlet("/manager/UserShow")
public class UserShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;utf-8");
		UserService us =new UserService();
		String date = request.getParameter("date");
		List<Shop_User> li =null;
		//��ǰҳ 
		int cpage=1; //��ǰҳ
		int count = 5; //ÿҳ��ʾ����
		//���ָ��ҳ��
		String cp =request.getParameter("cp");
		try {
			int arr[] =Userdao.totalPage();
			
			request.setAttribute("tsum",arr[0]);//��tiao��
			request.setAttribute("totalpages",arr[1]);//��ҳ��
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(cp!=null) {
			//�����Ϊ��ת 
			cpage=Integer.parseInt(cp);
		}
		try {
			li = us.Userall(cpage,count);
			request.setAttribute("cp",cpage);
			request.setAttribute("li", li);
			request.getRequestDispatcher("/manager/admin_user.jsp").forward(request,response);
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}
}
