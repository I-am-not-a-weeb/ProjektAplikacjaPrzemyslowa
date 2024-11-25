package Services;

import Repos.AccountRepo;
import Repos.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    AdminRepo adminRepo;

    AdminService() {
        this.adminRepo = null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<String> getAllTables()
    {
        return adminRepo.getAllTables();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object getTableData(String tableName)
    {
        return adminRepo.getTableData(tableName);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Object getAllTablesData()
    {
        Map<String,Object> send = new java.util.HashMap<>();
        try {
            //BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt", true));

            List<String> tables = getAllTables();

            for(String table : tables)
            {
                send.put(table,getTableData(table));
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

    public void setAdminRepo(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }
}
