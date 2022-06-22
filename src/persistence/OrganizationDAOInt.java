
package persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import model.*;

public interface OrganizationDAOInt {
    public List<Organization> listOrganization() throws FileNotFoundException, IOException, ClassNotFoundException;
    public String addOrganization(Organization organization) throws FileNotFoundException, IOException;
    public void deleteOrganization(Organization organization);
    public void updateOrganization(Organization organization);
}
