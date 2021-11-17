package movie.servlet;

import movie.dal.*;
import movie.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String stringUserId = req.getParameter("userId");
        int userId = Integer.parseInt(stringUserId);
        if (stringUserId == null || stringUserId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserId.");
        } else {
        	try {
        		Users user = usersDao.getUserByUserId(userId);
        		if(user == null) {
        			messages.put("success", "UserId does not exist.");
        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String stringUserId = req.getParameter("userId");
        int userId = Integer.parseInt(stringUserId);
        if (stringUserId == null || stringUserId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserId.");
        } else {
        	try {
        		Users user = usersDao.getUserByUserId(userId);
        		if(user == null) {
        			messages.put("success", "UserId does not exist. No update to perform.");
        		} else {
        			String stringNewPhone = req.getParameter("phone");
        			int newPhone = Integer.parseInt(stringNewPhone);
        			if (stringNewPhone == null || stringNewPhone.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid phone.");
        	        } else {
        	        	user = usersDao.updatePhone(user, newPhone);
        	        	messages.put("success", "Successfully updated " + newPhone);
        	        }
        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
}
