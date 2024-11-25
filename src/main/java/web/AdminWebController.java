package web;

import Services.AdminService;
import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

@Controller
public class AdminWebController {

    @Autowired
    private AdminService adminService;

    /*AdminWebController() {
        try{
            ApplicationContext context =
                    new ClassPathXmlApplicationContext(new String[] {"META-INF/beans.xml"});
            this.adminService = (AdminService)context.getBean("service_admin");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }*/

    @GetMapping("/admin")
    public String showAdminPage(
            @AuthenticationPrincipal OAuth2User oauthUser,
            Model model
    ) {
        return "adminPage";
    }

    @GetMapping("/admin/getAllTables")
    @ResponseBody
    public Map<String,Object> showAllTables(
            @AuthenticationPrincipal OAuth2User oauthUser,
            Model model
    ) {
        Map<String,Object> send = new java.util.HashMap<>();
        try {
            //BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt", true));

            List<String> tables = adminService.getAllTables();

            for(String table : tables)
            {
                send.put(table,adminService.getTableData(table));
            }
            //writer.write(send);+
            return send;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    //@PostMapping("/admin/postAllData")
    //public String postAllData()

}
