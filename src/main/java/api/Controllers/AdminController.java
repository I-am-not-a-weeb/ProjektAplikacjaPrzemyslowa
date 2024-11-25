package api.Controllers;


import Repos.AdminRepo;
import Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public Object getAllTablesData()
    {
        return adminService.getAllTablesData();
    }

    @GetMapping("/accounts")
    public String getAccounts() {
        return "accounts";
    }
}
